package springnotes.domain;

import springnotes.domain.Sales;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by s_okhoda on 16.02.2016.
 */
@Entity
@Table (name = "STORES")
public class Store {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "STORE_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    @ManyToOne
    @JoinColumn (name = "NOTETYPE_ID")
    private Notebook notebk;

    @Column (name = "QUANTITY")
    private int quantity;

    @Column (name = "PRICE")
    private Double price;

    @OneToMany (mappedBy = "store")
    private Set<Sales> sales;


    public Store(Notebook notebk, int quantity, Double price) {
        this.notebk = notebk;
        this.quantity = quantity;
        this.price = price;
    }

    public Store() {
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", " + notebk +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Notebook getNotebk() {
        return notebk;
    }

    public void setNotebk(Notebook notebk) {
        this.notebk = notebk;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
