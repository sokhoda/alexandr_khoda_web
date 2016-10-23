<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="hw7.notes.view.Menu" %>
<%@ page import="hw7.notes.domain.Vendor" %>
<%@ page import="java.util.List" %>
<%@ page import="hw7.notes.dao.VendorDao" %>
<%@ page import="hw7.notes.service.NotebookService" %>
<%@ page import="hw7.notes.service.NotebookServiceImpl" %>
<%--<%@ page errorPage="../pages/generalErrorPage.jsp" %>--%>
<script src="../JS/select.js" type="text/javascript">    </script>
<script src="../JS/notebooks.js" type="text/javascript">    </script>

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
            <%@include file='../css/menu.css' %>
        </style>
    </head>
    <body>
    <%!
        NotebookService service;
        VendorDao vendorDao;
        List<Vendor> vendor = null;
    %>
    <%
        vendorDao = ((NotebookServiceImpl)Menu.service).getVendorDao();

        vendor = (List<Vendor>)vendorDao.findAll();
        request.setAttribute("vendor", vendor);
    %>
    <c:set var="defPortion" value="5"/>
    <c:set var="defQuant" value="15"/>

    <form action="/MainNote" method="post">

        <center><img id="MenuImg" src="../img/laptop1.gif">
        </center>
        <h1 align="center">Welcome to the Computer Store</h1>
        <div>
            <label align="left" style="font-size: larger; font-weight: bold;
              color: firebrick">Please, make your choice:
            </label>
            <label id="message" style="width: 100%; color:${messageColor == null ? 'brown' : messageColor};
                    text-align: center; font-size:x-large">${messageText}
            </label>
        </div>

      <table width="100%">
          <thead>
          </thead>

          <tbody>
              <tr>
                  <td class="col0">
                      <input type="submit" name="crCPU"
                             value="1. Create CPU type">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="crMemory"
                             value="2. Create Memory type">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="crVen"
                             value="3. Create Vendor">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="crNtbType"
                             value="4. Create Notebook type">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="accBatch"
                             value="5. Accept batch of Notes">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="sell"
                             value="6. Sell Notebooks from the store">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="updCPU"
                             value="7. Update CPU">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">Portion:</label>
                          <input type="text" name="updCPUPortion"
                                 value="${defPortion}">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="updMemory"
                             value="8. Update Memory">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">Portion:</label>
                          <input type="text" name="updMemoryPortion"
                                 value="${defPortion}">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="updVen"
                             value="9. Update Vendor">
                  </td>
              </tr>

              <tr>
                  <td  class="col0">
                      <input type="submit" name="updNtb"
                             value="10. Update Notebook">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">Portion:</label>
                          <input type="text" name="updNtbPortion"
                                 value="${defPortion}">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="writeOffNtb"
                             value="11. Write off Notebooks">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNtbTypesByPortion"
                             value="12. List all Notebook types by Portion">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">Portion:</label>
                          <input type="text" name="listNtbTypesByPortionPortion"
                                 value="${defPortion}">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNtbByPortion"
                             value="13. List all Notebooks in the Store by Portion">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">Portion:</label>
                          <input type="text" name="listNtbByPortionPortion"
                                 value="${defPortion}">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="ByGtQuan"
                             value="14. List all Notebooks, quant. >=">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">Quantity:</label>
                          <input type="text" name="ByGtQuanQuan"
                                 value="${defQuant}">
                      </div>
                  </td>

              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="ByCPUVen"
                             value="15. List all Notebooks by CPU Vendor">
                  </td>
                  <td class="colA">
                      <div class="cellIn">
                          <label class="smallSign">Portion:</label>
                          <input type="text" name="ByCPUVenPortion"
                                 value="${defPortion}">
                      </div>
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNtbStore"
                             value="16. List all Notebooks in the Store">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNtbStoreByVen"
                             value="17. List all Notebooks in the Store by Vendor">
                  </td>
              </tr>
              <tr>
                  <td  class="col0">
                      <input type="submit" name="listNoteByPriceManDateVendor"
                             value="18. Get AVE Daily Sell Rate">
                  </td>
              </tr>


              <tr>
                  <td  class="col0">
                      <%--<input type="submit" name="exit" class="but"--%>
                             <%--value=">10. Exit">--%>
                      <button name="exit" onclick="self.close()" class="but">19.
                          Exit</button>
                  </td>
              </tr>
          </tbody>

      </table>
        <footer style="text-align: center">&copy;<%=Menu.NameSurname%>
        </footer>
        ${kkk}
    </form>
    </body>
</html>
