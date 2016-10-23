package hw7.notes.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by s_okhoda on 09.02.2016.
 */
@Entity
@Table(name = "NOTES7")
public class Notebook {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "NOTES7_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;

    @Column(name="MODEL")
    private String model;

    @Column(name="MANDATE")
    private Date manDate;

    @ManyToOne
    @JoinColumn (name = "CPU_ID")
    private CPU cpu;

    @ManyToOne
    @JoinColumn (name = "MEMORY_ID")
    private Memory memory;

    @OneToMany (mappedBy = "notebk", fetch = FetchType.EAGER)
    private Set<Store> stores;

    @Transient
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public Notebook(){
    }

    public Notebook(Vendor vendor, String model, Date manDate, CPU cpu,
                    Memory memory) {
        this.vendor = vendor;
        this.model = model;
        this.manDate = manDate;
        this.cpu = cpu;
        this.memory = memory;
    }

    public Notebook(Vendor vendor, String model, Date manDate) {
        this(vendor, model, manDate, null, null);
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + id +
                ", vendor=" + vendor +
                ", model='" + model + '\'' +
                ", manDate=" + df.format(manDate) +
                ", " + cpu +
                ", " + memory +
                '}';
    }


    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getManDate() {
        return manDate;
    }

    public void setManDate(Date manDate) {
        this.manDate = manDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
