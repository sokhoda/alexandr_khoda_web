<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="hw8.taxi.domain.Client" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="hw8.taxi.service.ClientServiceImpl" %>
<%@ page import="java.util.GregorianCalendar" %><%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 20.01.2016
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requested List of Clients</title>
    <style>
        <%@include file='/hw8.taxi/css/clients.css' %>
    </style>
</head>
<body>

<form action="/taxi" method="post">
    <button type="submit" name="back">Назад в Главное меню</button>
    <a href="/hw8.taxi/pages/registerClient.jsp"><button type="submit">Новый клиент</button></a>

    <table border="2">
        <thead>
        <%  if (request.getAttribute("quantity") != null) {
                quantity = (int) request.getAttribute("quantity");
                header = "Список всех клиентов блоками по " + quantity +
                        " человек";
            }
            if (request.getAttribute("showClientsGtSum") != null) {
                orderSum = (int) request.getAttribute("showClientsGtSum");
                header = "Список всех клиентов, наездивших на сумму больше "
                        + orderSum + " грн.";
            }
            if (request.getAttribute("showClientsLastMonth") != null) {
                header = "Список всех клиентов, делавших заказы за последний месяц";
            }
        %>
            <tr>
               <th colspan="100%"><h1><%=header%></h1></th>
            </tr>
            <tr>
                <th><h3>Пор.№</h3></th>
                <th><h3>Имя</h3></th>
                <th><h3>Фамилия</h3></th>
                <th><h3>Телефон</h3></th>
                <th><h3>Адрес</h3></th>
                <th><h3>Общая сумма заказов, грн.</h3></th>
                <th><h3>Дата последнего заказа</h3></th>
            </tr>
        </thead>

        <tbody>
            <%!
                int lastOutInx = 0;
                int counter;
                List<Client> list;
                ClientServiceImpl clientServiceImpl;
                String header="";
                int quantity;
                int orderSum;

                private String checkDate(GregorianCalendar gc){
                    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
                    if (gc == null) {
                        return "null";
                    }
                    else {
                        return format1.format(gc.getTime());
                    }
                }

            %>
            <%
                clientServiceImpl = (ClientServiceImpl)request.getAttribute("clientServiceImpl");

                if (request.getAttribute("quantity") != null) {
                    if (quantity != (int)request.getAttribute("quantity")){
                        clientServiceImpl.getClientService().setLastPrintedClientInx(-1);
                    }

                    counter =
                            clientServiceImpl.getClientService().getLastPrintedClientInx() + 1;
                    list =
                            clientServiceImpl.getClientService().showClientsByPortion(quantity);
                    lastOutInx = clientServiceImpl.getClientService().getLastPrintedClientInx();

                    if (lastOutInx ==
                            clientServiceImpl.getClientService().getClients().size() - 1){
                        clientServiceImpl.getClientService().setLastPrintedClientInx(counter - 1);
                    }
                }
                else {
                    list = (List) request.getAttribute("clientList");
                    counter = 0;
                    clientServiceImpl.getClientService().setLastPrintedClientInx(-1);
                }
                for (int i = 0; i < list.size(); i++) {
            %>
                    <tr>
                        <td class="shrink"><%= Integer.toString(i + 1 + counter)
                                + "."%></td>
                        <td align="left"><%= list.get(i).getName()%></td>
                        <td align="left"><%= list.get(i).getSurname()%></td>
                        <td><%= list.get(i).getPhone()%></td>
                        <td align="left"><%= list.get(i).getAddress()%></td>
                        <td><%=list.get(i).getTotalOrderAmount()%></td>
                        <td><%=checkDate(list.get(i).getLastOrderDate())%></td>
                    </tr>
            <%
                }
            %>
        </tbody>
        <tfoot>
            <td colspan="100%" align="center">
                &copy;<%=clientServiceImpl.getClientService().getFooter()%></td>
        </tfoot>
    </table>
</form>
</body>
</html>
