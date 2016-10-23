package hw8.taxi.domain;

import javax.xml.soap.SAAJResult;
import java.util.GregorianCalendar;

/**
 * Created by s_okhoda on 23.01.2016.
 */
public class Order {
    //дата, клиент, сумма, адрес подачи, адрес назначения)
    private int id;
    private GregorianCalendar date;
    private int clientID;
    private String OrderAmount;
    private String addressPickup;
    private String addressGetOut;

    public Order(int id, GregorianCalendar date, int clientID, String
            orderAmount, String addressPickup, String addressGetOut) {
        this.id = id;
        this.date = date;
        this.clientID = clientID;
        OrderAmount = orderAmount;
        this.addressPickup = addressPickup;
        this.addressGetOut = addressGetOut;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        OrderAmount = orderAmount;
    }

    public String getAddressPickup() {
        return addressPickup;
    }

    public void setAddressPickup(String addressPickup) {
        this.addressPickup = addressPickup;
    }

    public String getAddressGetOut() {
        return addressGetOut;
    }

    public void setAddressGetOut(String addressGetOut) {
        this.addressGetOut = addressGetOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
