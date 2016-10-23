package springnotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springnotes.exception.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import springnotes.dao.*;
import springnotes.domain.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static springnotes.view.Servlet.String2Integer;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@Transactional
@Service
public class NotebookServiceImpl implements NotebookService {
    private static Logger log = Logger.getLogger(NotebookService.class);

    @Autowired
    private SessionFactory factory;

    @Autowired
    private NotebookDao noteDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private SalesDao salesDao;

    @Autowired
    private VendorDao vendorDao;

    @Autowired
    private CPUDao cpuDao;

    @Autowired
    private MemoryDao memoryDao;

    public NotebookServiceImpl() {
        Locale.setDefault(Locale.ENGLISH);
    }

//    public NotebookServiceImpl() {
//        factory = getSessionFactory();
//        this.noteDao = new NotebookDaoImpl(factory);
//        this.storeDao = new StoreDaoImpl(factory);
//        this.salesDao = new SalesDaoImpl(factory);
//        this.vendorDao = new VendorDaoImpl(factory);
//        this.cpuDao = new CPUDaoImpl(factory);
//        this.memoryDao = new MemoryDaoImpl(factory);
//
//    }

    @Override
    public Long receive(Long noteId, int amount, double price) throws StoreException {
        if (noteId == null){
            throw new StoreException("NotebookId is null.");
        }
        Notebook notebook = noteDao.read(noteId);
        if (notebook == null){
            throw new StoreException("Notebook is null.");
        }
        return storeDao.create(new Store(notebook, amount, price));

    }

    @Override
    public Long sale(Long storeId, int amount) throws StoreException{
        if (storeId == null){
            throw new StoreException("StoreId is null.");
        }
        Store store = storeDao.read(storeId);
        int existingQuantity = store.getQuantity();
        if (existingQuantity < amount){
            throw new StoreException("Existing amount in the Store '" +
                    store + "' is less than required amount.");
        }
        else {
            store.setQuantity(existingQuantity - amount);
            return (storeDao.update(store) ? storeId : null);
        }
    }

    @Override
    public boolean updateCPU(CPU cpu) throws CPUException{
        if (cpuDao.checkExistExceptId(cpu, cpu.getId())) {
            throw new CPUException("CPU '" + cpu + "' already exists in DB.");
        }
        else {
            return cpuDao.update(cpu);
        }
    }

    @Override
    public boolean updateMemory(Memory memory) {
        return memoryDao.update(memory);
    }

    @Override
    public boolean updateVendor(Vendor vendor) {
        return vendorDao.update(vendor);
    }

    @Override
    public boolean updateNotebook(Notebook notebook) {
        return noteDao.update(notebook);
    }

    @Override
    public boolean removeFromStore(Store store, int amount) throws StoreException{
        int existingQuant;

        if ((existingQuant = store.getQuantity()) >= amount){
            store.setQuantity(existingQuant - amount);
            return storeDao.update(store);
        }
        throw new StoreException("Amount of existing items on store less than" +
                "  required.");
    }

    @Override
    public boolean deleteNotebookType(Long noteId) throws NotebookException{
        Notebook notebook = noteDao.read(noteId);
        if (notebook == null){
            throw new NotebookException("Notebook type not found: id=" + noteId);
        }
        return noteDao.delete(notebook);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getNotebookTypesTotPages(int size) {
        List notebook = (List<Notebook>)noteDao.findAll();
        return  (notebook.size() == 0 ? 1 :(int) Math.ceil
                (notebook.size() / (double)size));
    }

    @Override
    public Integer getCPUTotPages(int size) {
        List cpu = (List<CPU>)cpuDao.findAll();
        return  (cpu.size() == 0 ? 1 :(int) Math.ceil
                (cpu.size() / (double)size));
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getNotebookInStoreTotPages(int size) throws HibernateException {
        Session session = factory.getCurrentSession();
            Query query = getNotebookInStoreQuery(session);
            List list = query.list();
            return  (list.size() == 0 ? 1 :(int) Math.ceil
                    (list.size() / (double)size));
    }

    @Override
    @Transactional(readOnly = true)
    public List getNotebookTypesByPortion(int size, int cnt) throws
            PortionException, HibernateException{
        if (size == 0) {
            throw new PortionException("Portion size can not be ZERO.");
        }
        return noteDao.getNotebookTypesByPortion(size, cnt);
    }

    @Override
    public List getCPUByPortion(int size, int cnt) throws PortionException, HibernateException {
        if (size == 0) {
            throw new PortionException("Portion size can not be ZERO.");
        }
        return cpuDao.getCPUByPortion(size, cnt);
    }

    @Transactional(readOnly = true)
    private Query getNotebookInStoreQuery(Session session){
        return session.createQuery("select n.id, v.name, n.model, " +
                "to_char(n.manDate, 'dd.mm.yyyy'), " +
                " cpuv.name, c.model, c.freq, " +
                " memv.name, m.sizze,   sum(s.quantity) as qua from Notebook n " +
                " join n.stores s " +
                " join n.vendor v "+
                " join n.cpu c "+
                " join c.vendor cpuv "+
                " join n.memory m "+
                " join m.vendor memv "+
                " group by n.id, v.name, n.model, n.manDate, cpuv.name, c.model, c.freq, memv.name, m.sizze" +
                " ORDER BY  qua desc");
    }

    @Override
    @Transactional(readOnly = true)
    public List getNotebooksByPortion(int size, int cnt) throws PortionException,
            HibernateException {
        if (size == 0) {
            throw new PortionException("Portion size can not be ZERO.");
        }
        Session session = factory.getCurrentSession();
        Query query = getNotebookInStoreQuery(session);
        query.setFirstResult((cnt - 1) * size);
        query.setMaxResults(size);
        return query.list();
    }

    @Override
    public List listCPUByPortion(int size, int cnt) throws PortionException{
        List cpu = (List<CPU>)cpuDao.findAll();
        if (size == 0) {
            throw new PortionException("Portion size can not be ZERO.");
        }
        return (List<CPU>)cpuDao.getCPUByPortion(size, cnt);
    }

    @Override
    @Transactional(readOnly = true)
    public List getNotebooksGtAmount(int amount) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List getNotebooksByCpuVendor(Vendor cpuVendor) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List getNotebooksFromStore() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List getNotebooksStorePresent() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Map getSalesByDays() {
        return null;
    }

    public NotebookDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NotebookDao noteDao) {
        this.noteDao = noteDao;
    }

    public StoreDao getStoreDao() {
        return storeDao;
    }

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public SalesDao getSalesDao() {
        return salesDao;
    }

    public void setSalesDao(SalesDao salesDao) {
        this.salesDao = salesDao;
    }

    public VendorDao getVendorDao() {
        return vendorDao;
    }

    public void setVendorDao(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public CPUDao getCpuDao() {
        return cpuDao;
    }

    public void setCpuDao(CPUDao cpuDao) {
        this.cpuDao = cpuDao;
    }

    public MemoryDao getMemoryDao() {
        return memoryDao;
    }

    public void setMemoryDao(MemoryDao memoryDao) {
        this.memoryDao = memoryDao;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }


}
