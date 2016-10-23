<%@ page import="hw8.taxi.service.ClientServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 19.01.2016
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Main Menu</title>
        <style>
            <%@include file='/hw8.taxi/css/dashboard.css' %>
        </style>
    </head>
    <body>
    <form action="/taxi" method="get">
      <table width="100%">
          <thead>
              <tr><th align="center"><img id="taxiImg"
                                          src="/img/taxi_clients.jpg"></th>
              </tr>
              <tr>
                  <th colspan="100%"><h1>Главное меню</h1></th>
              </tr>
          </thead>
          <%!
              ClientServiceImpl clientServiceImpl = null;
          %>
          <%
              if (clientServiceImpl == null) {
                  clientServiceImpl =
                          (ClientServiceImpl) request.getAttribute("clientServiceImpl");
              }
          %>
          <tbody>
              <tr align="left" style="font-size: larger; font-weight: bold;
              color: firebrick">
                  <td colspan="3"> Выберите действие:</td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="selAct"
                             value="createClient"> Зарегистрировать клиента
                  </td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="selAct"
                             value="showClientsByPortion"> Вывести всех
                      клиентов порциями по
                      <input type="text" name="clientQuantity"
                             placeholder="10" value="<%=
                          clientServiceImpl.getClientService().getClientQuantity()
                          %>" > человек
                  </td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="selAct"
                             value="showClientsGtSum"> Вывести всех
                      клиентов наездивших на сумму больше
                      <input type="text" name="clientOrderSum" value="<%=
                          clientServiceImpl.getClientService().getClientOrderSum()
                          %>" > грн.
                  </td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="selAct"
                             value="showClientsLastMonth" checked>
                      Вывести всех клиентов, делавших заказы за последний месяц
                  </td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="selAct" value="createOrder">
                      Оформить заказ
                  </td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="selAct" value="editOrder">
                      Отредактировать заказ
                  </td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="selAct" value="showOrdersSum">
                      Вывести список заказов на сумму в диапазоне от
                      <input type="text" name="minOrderSum" value="<%=
                          clientServiceImpl.getOrderService().getMinOrderSum()
                          %>" > грн. до
                      <input type="text" name="maxOrderSum" value="<%=
                          clientServiceImpl.getOrderService().getMaxOrderSum()
                          %>" > грн.
                  </td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="selAct" value="showOrdersByPortion">
                      Вывести список всех заказов порциями по
                      <input type="text" name="orderQuantity"
                             placeholder="10" value="<%=
                          clientServiceImpl.getOrderService().getOrderQuantity()
                          %>" > заказов
                  </td>
              </tr>
              <tr>
                  <td>
                      <br>
                      <input type="submit" value="Перейти">
                  </td>
              </tr>
          </tbody>
          <tfoot>
            <td align="center">&copy;<%=clientServiceImpl.getClientService().getFooter()%></td>
          </tfoot>
      </table>
    </form>
    </body>
</html>
