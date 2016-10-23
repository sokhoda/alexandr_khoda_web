package springnotes.domain;

import springnotes.domain.Notebook;
import springnotes.domain.Vendor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by s_okhoda on 16.02.2016.
 */
@Entity
@Table (name = "MEMORIES")
public class Memory {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "MEM_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;

    @Column(name = "SIZZE")
    private String sizze;

    @OneToMany (mappedBy = "memory")
    private Set<Notebook> notebks;

    public Memory() {
    }

    public Memory(String sizze, Vendor vendor) {
        this.sizze = sizze;
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "sizze='" + sizze + '\'' +
                ", vendor=" + vendor +
                ", id=" + id +
                '}';
    }

    public String getSizze() {
        return sizze;
    }

    public void setSizze(String sizze) {
        this.sizze = sizze;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
