package hw8.taxi.service;

import hw8.taxi.domain.Client;
import hw8.taxi.domain.Order;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class ClientServiceImpl {
    private ClientService clientService = new ClientService();
    private OrderService orderService = new OrderService();
    private Random rand = new Random();

    private String[][] sirNames = { { "Arzhajeva", "0" }, { "Barabash", "10" },
            { "Bilkevych", "10" }, { "Bondarenko", "10" },
            { "Bonchyk", "10" }, { "Bortnik", "10" }, { "Borcova", "0" },
            { "Voronova", "0" }, { "Skorostenska", "0" },
            { "Kalinichenko", "10" }, { "Kizilov", "1" },
            { "Kobyleckyj", "1" }, { "Kolpak", "10" }, { "Lytvyn", "10" },
            { "Mykytenko", "10" }, { "Novak", "10" }, { "Redkina", "0" },
            { "Ruban", "10" }, { "Rjabchenko", "10" }, { "Samsonjyk", "10" },
            { "Sokolova", "0" }, { "Stepchuk", "10" }, { "Sushyckyj", "1" },
            { "Trygub", "0" }, { "Fecak", "10" }, { "CHyslovska", "0" },
            { "SHvec", "10" }, { "SHkapenko", "10" }, { "SHlapak", "10" },
            { "JAkovenko", "10" }, };

    private String[][] names = { { "Alisa", "0" }, { "Anna", "0" },
            { "Valerija", "0" }, { "Viktorija", "0" }, { "Vitalij", "1" },
            { "Galyna", "0" }, { "Danylo", "1" }, { "JEvgenija", "0" },
            { "JElyzaveta", "0" }, { "Josyp", "1" }, { "Ivan", "1" },
            { "Igor", "1" }, { "Kateryna", "0" }, { "Krystyna", "0" },
            { "Maksym", "1" }, { "Margaryta", "0" }, { "Maryna", "0" },
            { "Marija", "0" }, { "Mykola", "1" }, { "Natalija", "0" },
            { "Oksana", "0" }, { "Oleksandr", "1" }, { "Oleksandra", "0" },
            { "Oleksij", "1" }, { "Olena", "0" }, { "Pavlo", "1" },
            { "Petro", "1" }, { "Sergij", "1" }, { "Solomija", "0" },
            { "Tetjana", "0" } };

    private String[][] patronymicName = { { "Anatolijivna", "0" },
            { "Anatolijovych", "1" }, { "Andrijivna", "0" },
            { "Andrijovych", "1" }, { "Borysivna", "0" },
            { "Borysovych", "1" }, { "Vasylivna", "0" },
            { "Vasylovych", "1" }, { "Viktorivna", "0" },
            { "Viktorovych", "1" }, { "Volodymyrivna", "0" },
            { "Volodymyrovych", "1" }, { "Vjacheslavivna", "0" },
            { "Vjacheslavovych", "1" }, { "Denysivna", "0" },
            { "Denysovych", "1" }, { "Igorivna", "0" }, { "Igorevych", "1" },
            { "Kostjantynivna", "0" }, { "Kostjantynovych", "1" },
            { "Olegivna", "0" }, { "Olegovych", "1" },
            { "Oleksandrivna", "0" }, { "Oleksandrovych", "1" },
            { "Pavlivna", "0" }, { "Pavlovych", "1" }, { "Romanivna", "0" },
            { "Romanovych", "1" }, { "Sergijivna", "0" },
            { "Sergijovych", "1" }, { "JYrijivna", "0" }, { "JYrijovych", "1" }, };

    private final int TotalOrderSum = 10000;

    public ClientServiceImpl(){

    }

    public void fillClientsRandomly(int quantity){
        if (quantity <= 0) return;

        for (int i = 0; i < quantity; i++) {
            String[] human = getRandHumanName();
            clientService.getClients().add(new Client(i,human[0], human[1],
                    getRandPhone(),
                    "ADDRESS" + rand.nextInt(1000), rand.nextInt
                    (TotalOrderSum), getRandDate(4, 2013, 12, 1, 28, 1)));
        }
    }
    public GregorianCalendar getRandDate(int kYear, int minYear, int kMonth,
                                         int minMonth, int kDay, int minDay) {

        int year = rand.nextInt(kYear) + minYear;
        int month = rand.nextInt(kMonth) + minMonth;
        int dayOfMonth = rand.nextInt(kDay) + minDay;

        GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth);
        while (date.compareTo(new GregorianCalendar()) > 0){
            year = rand.nextInt(kYear) + minYear;
            month = rand.nextInt(kMonth) + minMonth;
            dayOfMonth = rand.nextInt(kDay) + minDay;

            date = new GregorianCalendar(year, month, dayOfMonth);
        }
        return date;
    }
    public String getRandPhone(){
        String[] code = {"095", "050", "097", "067", "066", "099"};
        String phoneNum = code[rand.nextInt(code.length)];
        for (int i = 0; i < 8 ; i++) {
            if (i == 0 || i == 3 || i == 5) phoneNum += " ";
            phoneNum += rand.nextInt(10);
        }
        return phoneNum;
    }

    public String[] getRandHumanName() {
        if (sirNames.length == 0 || names.length == 0
                || patronymicName.length == 0) return null;

        int sex = rand.nextInt(2);
        int inx1 = 0;

        if (sex == 1) { // male
            inx1 = rand.nextInt(sirNames.length);
            while (Integer.parseInt(sirNames[inx1][1]) < sex) {
                inx1 = rand.nextInt(sirNames.length);
            }
        }
        else { // female
            inx1 = inx1 = rand.nextInt(sirNames.length);
            while (Integer.parseInt(sirNames[inx1][1]) > sex) {
                inx1 = inx1 = rand.nextInt(sirNames.length);
            }
        }

        int inx2 = rand.nextInt(names.length);
        while (Integer.parseInt(names[inx2][1]) != sex) {
            inx2 = rand.nextInt(names.length);
        }

        int inx3 = rand.nextInt(patronymicName.length);
        while (Integer.parseInt(patronymicName[inx3][1]) != sex) {
            inx3 = rand.nextInt(patronymicName.length);
        }

        return new String[] { sirNames[inx1][0], names[inx2][0],
                patronymicName[inx3][0], Integer.toString(sex) };

    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public String[][] getSirNames() {
        return sirNames;
    }

    public void setSirNames(String[][] sirNames) {
        this.sirNames = sirNames;
    }

    public String[][] getNames() {
        return names;
    }

    public void setNames(String[][] names) {
        this.names = names;
    }

    public String[][] getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String[][] patronymicName) {
        this.patronymicName = patronymicName;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
