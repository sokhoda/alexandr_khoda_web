package hw8.taxi.domain;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class Client {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private int totalOrderAmount;
    private GregorianCalendar lastOrderDate;

    public Client(int id, String name, String surname, String phone, String
            address) {
        this(id, name, surname, phone, address, 0, null);
    }

    public Client(int id, String name, String surname, String phone, String
            address, int totalOrderAmount, GregorianCalendar lastOrderDate) {
       this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.totalOrderAmount = totalOrderAmount;
        this.lastOrderDate = lastOrderDate;
    }

    public Client(){

    }

    @Override
    public String toString(){
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        return getName() + " " + getSurname() + ", " + getTotalOrderAmount()
                + ", " + format1.format(getLastOrderDate().getTime()) + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTotalOrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public void setLastOrderDate(GregorianCalendar lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public GregorianCalendar getLastOrderDate() {
        return lastOrderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
