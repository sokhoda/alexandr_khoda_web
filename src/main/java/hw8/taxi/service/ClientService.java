package hw8.taxi.service;

import hw8.taxi.domain.Client;
import hw8.taxi.exception.ClientException;

import java.util.*;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class ClientService {
    private List<Client> clients = new ArrayList<Client>();
    private final int daysNumBeforeToday = 30;
    private int lastPrintedClientInx = -1;
    private int clientQuantity = 7;
    private int clientOrderSum = 100;


    private final String footer = " All rights reserved, Alexandr " +
            "Khodakovskyi, Kyiv 2016";
    private final int numOfClients = 20;
    private final int maxClientID = 10000;
    private Random rand = new Random();
    public ClientService() {
    }
    public int binarySearchByID(int id){
        if (clients == null) return -1;
        int lBound = 0;
        int rBound = clients.size() - 1;

        boolean cond = (clients.get(lBound).getId() == id)
                || (clients.get(rBound).getId() == id);

        while ((!cond) && (rBound - lBound) > 1) {
            int center = (rBound + lBound) / 2;

            if (id < clients.get(center).getId()) rBound = center;
            else lBound = center;
            cond = (clients.get(lBound).getId() == id)
                    || (clients.get(rBound).getId() == id);
        }
        if (cond) {
            if (clients.get(lBound).getId() == id) return lBound;
            else return rBound;
        }
        else return -1;
    }
    public Client getByID(int id){
        int inx = binarySearchByID((id));
        if (inx >= 0) {
            return clients.get(inx);
        }
        else{
            return null;
        }
    }

    public void sortByID(int direction){ // >=0 asc
        clients.sort(new Comparator<Client>() {
            @Override
            public int compare(Client client1, Client client2) {
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

    public boolean createClient(String name, String surname, String phone,
                               String address) throws ClientException{
        if (name == null || surname == null || phone == null || address == null){
            throw  new ClientException("Client Data can not be NULL");
        }
        if (name.trim().length() == 0 || surname.trim().length() == 0 ||
                phone.trim().length() == 0){
            throw new ClientException("Client Data can not have ZERO LENGTH");
        }
        int randID = rand.nextInt(maxClientID);
        sortByID(0);
        while(getByID(randID) != null){
            randID = rand.nextInt(maxClientID);
        }
        clients.add(new Client(randID, name, surname, phone, address));
        return true;
    }

    public List showClientsByPortion(int portionSize){
        List<Client> list = new ArrayList<Client> ();
        if (portionSize == 0) {
            return null;
        }
        int i = lastPrintedClientInx + 1;
        while (i < clients.size() && i < lastPrintedClientInx + 1 +portionSize){
            list.add(clients.get(i++));
        }
        lastPrintedClientInx = i - 1;
        return list;
    }

    public List showClientsGtSum(int sum){
        List<Client> list = new ArrayList<Client> ();
        for (Client client: clients) {
            if(client.getTotalOrderAmount() >= sum){
                list.add(client);
            }
        }
        return list;
    }
    public List showClientsLastMonth(){
        List<Client> list = new ArrayList<Client> ();
        GregorianCalendar curGregDate = new GregorianCalendar();

        for (Client client: clients) {
            GregorianCalendar clientLastOrdDate = client.getLastOrderDate();
            if (clientLastOrdDate != null) {
                if (daysBetween2Dates(clientLastOrdDate, curGregDate)
                        >= -daysNumBeforeToday &&
                        client.getLastOrderDate().compareTo(curGregDate) <= 0) {
                    list.add(client);
                }
            }
        }
        return list;
    }

    public int daysBetween2Dates(GregorianCalendar gc1, GregorianCalendar gc2){
        return (int)((gc1.getTimeInMillis() - gc2.getTimeInMillis()) /(
                1000 * 60 * 60 * 24));
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public int getLastPrintedClientInx() {
        return lastPrintedClientInx;
    }

    public void setLastPrintedClientInx(int lastPrintedClientInx) {
        this.lastPrintedClientInx = lastPrintedClientInx;
    }

    public int getClientQuantity() {
        return clientQuantity;
    }

    public void setClientQuantity(int clientQuantity) {
        this.clientQuantity = clientQuantity;
    }

    public int getClientOrderSum() {
        return clientOrderSum;
    }

    public void setClientOrderSum(int clientOrderSum) {
        this.clientOrderSum = clientOrderSum;
    }

    public String getFooter() {
        return footer;
    }

    public int getNumOfClients() {
        return numOfClients;
    }


}
