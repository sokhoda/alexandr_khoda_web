package ses17.domain;


import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 14.02.16
 */
@Entity
@Table(name = "DEPARTMENTS")
@SequenceGenerator(name = "sequence", sequenceName = "DEPARTMENTS_SEQ")
public class Department {
    @Id
    @Column(name = "DEPARTMENT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    @Column(name = "DEPARTMENT_NAME", length = 30)
    private String name;

    @Column(name = "MANAGER_ID", length = 6)
    private Long managerId;

    @Column(name = "MANAGER_ID", length = 4, insertable = false, updatable = false)
    private Long locationId;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    public Department() {
    }

    public Department(String name, Long managerId, Long locationId) {
        this.name = name;
        this.managerId = managerId;
        this.locationId = locationId;
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerId=" + managerId +
                ", locationId=" + locationId +
                '}';
    }
}
