package hw7.notes.service;

import hw7.notes.dao.*;
import hw7.notes.domain.*;
import hw7.notes.exception.CPUException;
import hw7.notes.exception.NotebookException;
import hw7.notes.exception.PortionException;
import hw7.notes.exception.StoreException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by s_okhoda on 09.02.2016.
 */
public class NotebookServiceImpl implements NotebookService {
    private SessionFactory factory;

    private static Logger log = Logger.getLogger(NotebookServiceImpl.class);
    private NotebookDao noteDao;
    private StoreDao storeDao;
    private SalesDao salesDao;
    private VendorDao vendorDao;
    private CPUDao cpuDao;
    private MemoryDao memoryDao;


    public NotebookServiceImpl() {
        factory = getSessionFactory();
        this.noteDao = new NotebookDaoImpl(factory);
        this.storeDao = new StoreDaoImpl(factory);
        this.salesDao = new SalesDaoImpl(factory);
        this.vendorDao = new VendorDaoImpl(factory);
        this.cpuDao = new CPUDaoImpl(factory);
        this.memoryDao = new MemoryDaoImpl(factory);

    }

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
            if (storeDao.update(store)) {
                return storeId;
            }
            else {
                return null;
            }
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
    public Integer getNotebookTypesTotPages(int size) {
        List notebook = (List<Notebook>)noteDao.findAll();
        return  (notebook.size() == 0 ? 1 :(int) Math.ceil
                (notebook.size() / (double)size));
    }

    @Override
    public Integer getNotebookInStoreTotPages(int size) throws HibernateException {
        Session session = factory.openSession();
        try{
            Query query = getNotebookInStoreQuery(session);
            List list = query.list();
            return  (list.size() == 0 ? 1 :(int) Math.ceil
                    (list.size() / (double)size));
        }
        catch (HibernateException e){
            log.error("Transaction failed", e);
            throw new HibernateException(e.getMessage());
        }
        finally {
            session.close();
        }
    }

    @Override
    public List getNotebookTypesByPortion(int size, int cnt) throws
            PortionException, HibernateException{
        if (size == 0) {
            throw new PortionException("Portion size can not be ZERO.");
        }
       return noteDao.getNotebookTypesByPortion(size, cnt);
    }

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
    public List getNotebooksByPortion(int size, int cnt) throws PortionException,
            HibernateException {
        if (size == 0) {
            throw new PortionException("Portion size can not be ZERO.");
        }
        Session session = factory.openSession();
        try{
            Query query = getNotebookInStoreQuery(session);
            query.setFirstResult((cnt - 1) * size);
            query.setMaxResults(size);
            return query.list();
        }
        catch (HibernateException e){
            log.error("Transaction failed", e);
            throw new HibernateException(e.getMessage());
        }
        finally {
            session.close();
        }
    }

    @Override
    public List getNotebooksGtAmount(int amount) {
        return null;
    }

    @Override
    public List getNotebooksByCpuVendor(Vendor cpuVendor) {
        return null;
    }

    @Override
    public List getNotebooksFromStore() {
        return null;
    }

    @Override
    public List getNotebooksStorePresent() {
        return null;
    }

    @Override
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

    //    @Override
//    public Long add(Notebook notebook) throws HibernateException{
//        return noteDao.create(notebook);
//    }
//
//    @Override
//    public List findAll() {
//        return noteDao.findAll();
//    }
//
//    @Override
//    public boolean changePrice(Long id, double price) {
//        return noteDao.changePrice(id, price);
//    }
//
//    @Override
//    public boolean changeSerialVendor(Long id, String serial, String vendor) {
//        return noteDao.changeSerialVendor(id, serial, vendor);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        return noteDao.delete(id);
//    }
//
//    @Override
//    public boolean deleteByModel(String model) {
//        return noteDao.deleteByModel(model);
//    }
//
//    @Override
//    public List findByVendor(String vendor) {
//        return noteDao.findByVendor(vendor);
//    }
//
//    @Override
//    public List findByPriceManufDate(Double price, Date date) {
//        return noteDao.findByPriceManufDate(price, date);
//    }
//
//    @Override
//    public List findBetweenPriceLtDateByVendor(Double priceFrom, Double priceTo, Date date, String vendor) {
//        return noteDao.findBetweenPriceLtDateByVendor(priceFrom, priceTo,
//                date, vendor);
//    }
//
    public SessionFactory getSessionFactory() {
        Locale.setDefault(Locale.ENGLISH);
        Configuration cfg =
                new Configuration().configure("hw7.notes/hibernate.cfg.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();

        return cfg.buildSessionFactory(standardServiceRegistry);
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        NotebookServiceImpl.log = log;
    }

}
