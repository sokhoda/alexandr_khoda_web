package session14.domain;

import session14.MoneyConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by s_okhoda on 13.02.2016.
 */
@Entity
@Table(name = "Companies")
public class Company {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "COMPANY_SEQ",
            allocationSize = 5, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    private String name;

    @Convert(converter = MoneyConverter.class)
    private Double balance;

    @OneToMany (mappedBy = "comp", cascade = {CascadeType.MERGE, CascadeType
            .PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
//    @JoinColumn (name = "COMPANY_ID")
    private Set<Employee> employee = new HashSet<>();

    public Company(Long id, String name, Double balance, Set<Employee> employee) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.employee = employee;
    }
    public Company(Long id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public Company(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public Company(String name, Double balance, Set<Employee> employee) {
        this(0L, name, balance, employee);
    }

    public Company() {
    }


    @Override
    public String toString(){
        return "id=" + getId() + ", name=" + getName() + ", balance=" +
                getBalance() + ", EmployeeSet=" + getEmployee().toString();
    }

    public String toString1(){
        return "id=" + getId() + ", name=" + getName() + ", balance=" +
                getBalance();
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }
}
