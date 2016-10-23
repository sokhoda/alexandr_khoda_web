<%@ page import="static hw6.notes.view.Menu.*" %>
<%@ page import="hw6.notes.view.Menu" %>
<%@ page errorPage="/hw6.notes/pages/generalErrorPage.jsp" %>
<%--
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
            <%@include file='/hw6.notes/css/menu.css' %>
        </style>
    </head>
    <body>
    <%
        String[] message = getAttribArray(request);

    %>
    <form action="/MenuNote" method="post">

        <center><img id="MenuImg" src="/hw6.notes/img/laptop1.gif">
        </center>
        <h1 align="center">Главное меню</h1>
        <div>
            <label align="left" style="font-size: larger; font-weight: bold;
              color: firebrick">Выберите действие:
            </label>
            <label id="message" style="width: 100%; margin-top:10%;
                color:<%=message[0]%>; text-align: center; font-size:x-large"><%=message[1]%>
            </label
        </div>

      <table width="100%">
          <thead>
          </thead>

          <tbody>
              <tr>
                  <td class="col0">
                      <input type="submit" name="addNote" value="1. Add notebook">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNote"
                             value="2. List all notebooks">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="delNote"
                             value="3. Delete notebook by id">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">id:</label>
                          <input type="text" name="idDelNote" value="" >
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="updtPrice"
                             value="4. Update notebook price by id">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">id:</label>
                          <input type="text" name="idUpdtPrice" value="" >
                      </div>
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">price:</label>
                          <input type="text" name="priceUpdtPrice" value="" >
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="updtSnVendor"
                             value="5. Update notebook serial & vendor by id">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">id:</label>
                          <input type="text" name="idUpdtSnVendor" value="">
                      </div>
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">serial:</label>
                          <input type="text" name="serialUpdtSnVendor" value="">
                      </div>
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">vendor:</label>
                          <input type="text" name="vendorUpdtSnVendor" value="">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="delNoteByModel"
                             value="6. Delete notebook by model">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">model:</label>
                          <input type="text" name="modelDelNoteByModel"
                                 value="">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNoteByVendor"
                             value="7. Get notebooks by vendor">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">vendor:</label>
                          <input type="text" name="vendorListNoteByVendor"
                                 value="">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNoteByPriceManDate"
                             value="8. Get notebooks by price and date of manuf.">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">price:</label>
                          <input type="text" name="priceListNoteByPriceManDate"
                                 value="">
                      </div>
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">date of manuf.:</label>
                          <input type="text"
                                 name="manDateListNoteByPriceManDate"
                                 value="">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNoteByPriceManDateVendor"
                             value="9. Get notebooks by price range, date of manuf. and vendor">

                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">price from:</label>
                          <input type="text"
                                 name="priceFromListNoteByPriceManDateVendor"
                                 value="">
                      </div>
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">price to:</label>
                          <input type="text"
                                 name="priceToListNoteByPriceManDateVendor"
                                 value="">
                      </div>
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">date of manuf.:</label>
                          <input type="text"
                                 name="manDateListNoteByPriceManDateVendor"
                                 value="">
                      </div>
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">vendor:</label>
                          <input type="text"
                                 name="vendorListNoteByPriceManDateVendor"
                                 value="">
                      </div>
                  </td>
              </tr>


              <tr>
                  <td  class="col0">
                      <%--<input type="submit" name="exit" class="but"--%>
                             <%--value=">10. Exit">--%>
                      <button name="exit" onclick="self.close()" class="but">10.
                          Exit</button>
                  </td>
              </tr>
          </tbody>

      </table>
        <footer style="text-align: center">&copy;<%=Menu.NameSurname%>
        </footer>
    </form>
    </body>
</html>
