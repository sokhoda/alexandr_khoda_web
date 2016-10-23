package springnotes.domain;

import springnotes.domain.Notebook;
import springnotes.domain.Vendor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by s_okhoda on 16.02.2016.
 */
@Entity
@Table (name = "CPUS")
public class CPU {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "CPU_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;

    @Column (name = "FREQUENCY")
    private Double freq;

    @Column (name = "MODEL")
    private String model;

    @OneToMany (mappedBy = "cpu")
    private Set<Notebook> notebks;


    public CPU(Vendor vendor, Double freq, String model) {
        this.vendor = vendor;
        this.freq = freq;
        this.model = model;
    }

    public CPU(Vendor vendor) {
        this(vendor, null, "N/A");
    }

    public CPU() {
    }

    @Override
    public String toString() {
        return "CPU{" +
                "id=" + id +
                ", vendor=" + vendor +
                ", freq=" + freq +
                ", model='" + model + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Double getFreq() {
        return freq;
    }

    public void setFreq(Double freq) {
        this.freq = freq;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
