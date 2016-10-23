package hw8.taxi.service;

import hw8.taxi.domain.Client;
import hw8.taxi.domain.Order;
import hw8.taxi.exception.OrderException;
import org.aspectj.weaver.ast.Or;

import java.util.*;

/**
 * Created by s_okhoda on 23.01.2016.
 */
public class OrderService {
    private int minOrderSum = 50;
    private int maxOrderSum = 250;
    private int orderQuantity = 15;

    List<Order> orders = new ArrayList<Order>();
    private Random rand = new Random();
    private final int maxOrderID = 1000000;

    public OrderService() {
    }

    public boolean createOrder( Client client, String amount, String
            addressFrom, String addressTo)
            throws OrderException {
        if (client == null || amount == null || addressFrom == null || addressTo == null){
            throw  new OrderException("Order Data can not be NULL");
        }
        if (addressFrom.trim().length() == 0 || addressTo.trim().length() == 0 ||
                amount.trim().length() == 0){
            throw new OrderException("Order Data can not have ZERO LENGTH");
        }
        int randID = rand.nextInt(maxOrderID);
        sortByID(0);
        while(getByID(randID) != null){
            randID = rand.nextInt(maxOrderID);
        }

        orders.add(new Order(randID, new GregorianCalendar(), client.getId(),
                amount, addressFrom, addressTo));
        return true;
    }

    public int binarySearchByID(int id){
        if (orders == null) return -1;
        int lBound = 0;
        int rBound = orders.size() - 1;

        boolean cond = (orders.get(lBound).getId() == id)
                || (orders.get(rBound).getId() == id);

        while ((!cond) && (rBound - lBound) > 1) {
            int center = (rBound + lBound) / 2;

            if (id < orders.get(center).getId()) rBound = center;
            else lBound = center;
            cond = (orders.get(lBound).getId() == id)
                    || (orders.get(rBound).getId() == id);
        }
        if (cond) {
            if (orders.get(lBound).getId() == id) return lBound;
            else return rBound;
        }
        else return -1;
    }
    public Order getByID(int id){
        int inx = binarySearchByID((id));
        if (inx >= 0) {
            return orders.get(inx);
        }
        else{
            return null;
        }
    }

    public void sortByID(int direction){ // >=0 asc
        orders.sort(new Comparator<Order>() {
            @Override
            public int compare(Order client1, Order client2) {
                int dif = client1.getId() - client2.getId();
                if (direction >= 0 ){
                    return dif;
                }
                else{
                    return -dif;
                }

            }
        });
    }
    public int getMinOrderSum() {
        return minOrderSum;
    }

    public void setMinOrderSum(int minOrderSum) {
        this.minOrderSum = minOrderSum;
    }

    public int getMaxOrderSum() {
        return maxOrderSum;
    }

    public void setMaxOrderSum(int maxOrderSum) {
        this.maxOrderSum = maxOrderSum;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
