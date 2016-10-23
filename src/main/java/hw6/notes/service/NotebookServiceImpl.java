package hw6.notes.service;

import hw6.notes.dao.NotebookDao;
import hw6.notes.domain.Notebook;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;

import java.util.Date;
import java.util.List;

/**
 * Created by s_okhoda on 09.02.2016.
 */
public class NotebookServiceImpl implements NotebookService{
    NotebookDao noteDao;

    public NotebookServiceImpl(NotebookDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public Long add(Notebook notebook) throws HibernateException{
        return noteDao.create(notebook);
    }

    @Override
    public List findAll() {
        return noteDao.findAll();
    }

    @Override
    public boolean changePrice(Long id, double price) {
        return noteDao.changePrice(id, price);
    }

    @Override
    public boolean changeSerialVendor(Long id, String serial, String vendor) {
        return noteDao.changeSerialVendor(id, serial, vendor);
    }

    @Override
    public boolean delete(Long id) {
        return noteDao.delete(id);
    }

    @Override
    public boolean deleteByModel(String model) {
        return noteDao.deleteByModel(model);
    }

    @Override
    public List findByVendor(String vendor) {
        return noteDao.findByVendor(vendor);
    }

    @Override
    public List findByPriceManufDate(Double price, Date date) {
        return noteDao.findByPriceManufDate(price, date);
    }

    @Override
    public List findBetweenPriceLtDateByVendor(Double priceFrom, Double priceTo, Date date, String vendor) {
        return noteDao.findBetweenPriceLtDateByVendor(priceFrom, priceTo,
                date, vendor);
    }
}
