package hw6.notes.domain;

import javax.persistence.*;
import java.util.GregorianCalendar;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@Entity
@Table(name = "NOTES")
public class Notebook {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "KHO_NOTES_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "ID")
    private long id;

    @Column(name="SERIAL")
    private String serial;

    @Column(name="VENDOR")
    private String vendor;

    @Column(name="MODEL")
    private String model;

    @Column(name="MANDATE")
    private GregorianCalendar manDate;

    @Column(name="PRICE")
    private double price;

    public Notebook(){

    }

    public Notebook(long id, String serial, String vendor, String model,
                    GregorianCalendar manDate, double price) {
        this.id = id;
        this.serial = serial;
        this.vendor = vendor;
        this.model = model;
        this.manDate = manDate;
        this.price = price;
    }

    public Notebook(String serial, String vendor, String model, GregorianCalendar manDate, double price) {
        this(0, serial, vendor, model, manDate, price);
    }

//    public Notebook(String serial, String vendor, String model, String
//            manDate, String price) {
//        this(0, serial, vendor, model, manDate, price);
//    }

    public Notebook(String vendor, double price) {
        this(0, "<none>", vendor, "<none>", new GregorianCalendar(), price);
    }

    public Notebook(String vendor, String model, double price) {
        this(0, "<none>", vendor, model, new GregorianCalendar(), price);
    }

    @Override
    public String toString(){
        return "id=" + id + ", serial=" + serial + ", " + vendor + ", " +
                model + ", " + manDate + ", price=" + price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public GregorianCalendar getManDate() {
        return manDate;
    }

    public void setManDate(GregorianCalendar manDate) {
        this.manDate = manDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
