<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static hw7.notes.view.Servlet.*" %>
<%@ page import="hw7.notes.dao.VendorDao" %>
<%@ page import="hw7.notes.service.NotebookService" %>
<%@ page import="hw7.notes.domain.Vendor" %>
<%@ page import="hw7.notes.service.NotebookServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="hw7.notes.view.Menu" %>
<%--<%@ page errorPage="/hw7.notes/pages/generalErrorPage.jsp" %>--%>
<script src="../JS/select.js" type="text/javascript">    </script>
<script src="../JS/notebooks.js" type="text/javascript">    </script>

<html>

<%!
    VendorDao vendorDao;
    List<Vendor> vendor = null;
%>

<c:if test="${mode == null}">
    <c:set var="mode" value="0"/>
</c:if>

<head>
    <title>${mode == 0 ? 'Add New': 'Update'} Vendor Type</title>
    <style>
        <%@include file='../css/addNotebook.css' %>
    </style>
    <center><h1>${mode == 0 ? 'Add New': 'Update'} Vendor Type</h1></center><br>
</head>
<body>


<%
    vendorDao = ((NotebookServiceImpl) Menu.service).getVendorDao();
%>
    <c:if test="${mode == 1}">
        <% vendor = (List<Vendor>)vendorDao.findAll();
           request.setAttribute("vendor", vendor);
        %>
    </c:if>

<form action="/AddVen" method="get">
    <img src="../img/addLaptop1.jpg" align="left" style="margin-right: 20px">


    <div id="divVenSel">
        <c:if test="${mode == 1}">
            <label for="venSel">SELECT VENDOR:</label>
            <select size="1" name="venSel" id="venSel"
                    onchange="this.form.submit();">
                <option disabled>select item</option>
                <c:forEach var="v" items="${vendor}" varStatus="cnt">
                    <c:choose>
                        <c:when test="${SelInx != null}">
                            <c:if test="${cnt.index == SelInx}">
                                <option value="${v.id}" selected>${v.toString()}</option>
                            </c:if>
                            <c:if test="${cnt.index != SelInx}">
                                <option value="${v.id}">${v.toString()}</option>
                            </c:if>
                        </c:when>
                        <c:when test="${SelVal != null}">
                            <c:if test="${v.id == SelVal}">
                                <option value="${v.id}" selected>${v.toString()}</option>
                            </c:if>
                            <c:if test="${v.id != SelVal}">
                                <option value="${v.id}">${v.toString()}</option>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <option value="${v.id}">${v.toString()}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select><br>
        </c:if>
    </div>

    <label for="vendors">NAME:</label>
    <input  type="text" value="${nameA == null ? '': nameA}" placeholder="TOSHIBA"
            name="name" id="vendors"><br>

    <br><br>
    <br><br>
    <p style="text-align: center;">
        <input type="submit" name="back" value="&longleftarrow; back">
        <input type="submit" name="add" value="Add" id="addBtn">
        <input type="submit" name="update" value="Update" id="updateBtn">

        <input type="button" value="Clear All"
               onclick="clearElemContent('vendors');
               clearElemContent('message');">
    </p>
    <br>
    <br>
    <label id="message" style="width: 100%; margin-top:10%;
            color:${messageColor == null ? 'brown' : messageColor};
            text-align: center; font-size:x-large">${messageText}
    </label>
</form>


<c:if test="${mode == 1}">  <%--update--%>
    <script type="text/javascript">
        setVisibility('divVenSel', 'block');
        setVisibility('updateBtn','inline');
        setVisibility('addBtn','none');
    </script>
</c:if>
<c:if test="${mode != 1}">  <%--add--%>
<script type="text/javascript">
    setVisibility('divVenSel', 'none');
    setVisibility('updateBtn','none');
    setVisibility('addBtn','inline');
</script>
</c:if>

</body>
</html>