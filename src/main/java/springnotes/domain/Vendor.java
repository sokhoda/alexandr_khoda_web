package springnotes.domain;

import springnotes.domain.Memory;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by s_okhoda on 16.02.2016.
 */
@Entity
@Table (name = "VENDORS")
public class Vendor {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "VENDOR_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "ID")
    private Long id;

    private String name;

    @OneToMany (mappedBy = "vendor")
    private Set<CPU> cpus;

    @OneToMany (mappedBy = "vendor")
    private Set<Memory> memories;

    @OneToMany (mappedBy = "vendor")
    private Set<Notebook> notebks;

    public Vendor(String name) {
        this.name = name;
    }
    public Vendor(){

    }

    @Override
    public String toString(){
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
