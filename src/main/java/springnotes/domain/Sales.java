package springnotes.domain;

import springnotes.domain.Store;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by s_okhoda on 16.02.2016.
 */
@Entity
@Table(name = "SALES")
public class Sales {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "SALE_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    @ManyToOne
    @JoinColumn (name = "STORE_ID")
    private Store store;

    @Column (name = "SALE_DATE")
    private Date saleDate;

    @Column (name = "QUANTITY")
    private int quantity;

    @Transient
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public Sales(Store store, Date saleDate, int quantity) {
        this.store = store;
        this.saleDate = saleDate;
        this.quantity = quantity;
    }

    public Sales() {
    }

    @Override
    public String toString() {
        return "Sales{" +
                "quantity=" + quantity +
                ", saleDate=" + saleDate +
                ", store=" + store +
                ", id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
