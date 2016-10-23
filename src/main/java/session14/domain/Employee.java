package session14.domain;

import javax.persistence.*;

/**
 * Created by s_okhoda on 13.02.2016.
 */
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "EMPLOYEE_SEQ",
            allocationSize = 5, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @ManyToOne
    private Company comp;

    private String firstName;

    private String lastName;

    public Employee(Long id, Company comp, String firstName, String lastName) {
        this.id = id;
        this.comp = comp;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public Employee(Company comp, String firstName, String lastName){
        this.comp = comp;

        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Employee() {
    }

    @Override
    public String toString(){
        return "\nid=" + getId() + ", " + getFirstName() + ", " + getLastName();
    }

    public String toString1(){
        return "\nid=" + getId() + ", " + getFirstName() + ", " + getLastName
                () + ", company=[" + getComp().toString1()+"]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getComp() {
        return comp;
    }

    public void setComp(Company comp) {
        this.comp = comp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
