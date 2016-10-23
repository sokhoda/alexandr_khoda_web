package hw7.notes.service;


import hw7.notes.domain.*;
import hw7.notes.exception.CPUException;
import hw7.notes.exception.NotebookException;
import hw7.notes.exception.PortionException;
import hw7.notes.exception.StoreException;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

/**
 * Created by s_okhoda on 09.02.2016.
 */
public interface NotebookService {

    Long receive(Long noteId, int amount, double price)throws StoreException;
    Long sale(Long storeId, int amount)throws StoreException;
    boolean updateCPU(CPU cpu) throws CPUException;
    boolean updateMemory(Memory memory);
    boolean updateVendor(Vendor vendor);
    boolean updateNotebook(Notebook notebook);
    boolean removeFromStore(Store store, int amount)  throws StoreException;

    boolean deleteNotebookType(Long noteId) throws NotebookException;
    Integer getNotebookTypesTotPages(int size);
    Integer getNotebookInStoreTotPages(int size) throws HibernateException;

    List getNotebookTypesByPortion(int size, int cnt) throws
            PortionException, HibernateException;
    List getNotebooksByPortion(int size, int cnt) throws PortionException,
            HibernateException;
    List getNotebooksGtAmount(int amount);
    List getNotebooksByCpuVendor(Vendor cpuVendor);
    List getNotebooksFromStore();
    List getNotebooksStorePresent();
    Map getSalesByDays();

//    Long add(Notebook notebook);
//    List findAll();
//    boolean changePrice(Long id, double price);
//    boolean changeSerialVendor(Long id, String serial, String vendor);
//    boolean delete(Long id);
//
//    boolean deleteByModel(String model);
//    List findByVendor(String vendor);
//    List findByPriceManufDate(Double price, Date date);
//    List findBetweenPriceLtDateByVendor(Double priceFrom, Double priceTo,
//                                        Date date, String vendor);
}
