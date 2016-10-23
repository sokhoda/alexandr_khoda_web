package hw6.notes.service;

import hw6.notes.domain.Notebook;

import java.util.Date;
import java.util.List;

/**
 * Created by s_okhoda on 09.02.2016.
 */
public interface NotebookService {
    Long add(Notebook notebook);
    List findAll();
    boolean changePrice(Long id, double price);
    boolean changeSerialVendor(Long id, String serial, String vendor);
    boolean delete(Long id);

    boolean deleteByModel(String model);
    List findByVendor(String vendor);
    List findByPriceManufDate(Double price, Date date);
    List findBetweenPriceLtDateByVendor(Double priceFrom, Double priceTo,
                                        Date date, String vendor);
}
