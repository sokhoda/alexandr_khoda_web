<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="hw7.notes.view.Menu" %>
<%@ page import="hw7.notes.dao.CPUDao" %>
<%@ page import="hw7.notes.domain.CPU" %>
<%@ page import="hw7.notes.domain.Memory" %>
<%@ page import="hw7.notes.domain.Vendor" %>
<%@ page import="hw7.notes.dao.VendorDao" %>
<%@ page import="hw7.notes.dao.MemoryDao" %>
<%@ page import="hw7.notes.service.NotebookServiceImpl" %>
<%@ page errorPage="../pages/generalErrorPage.jsp" %>
<script src="../JS/select.js" type="text/javascript">    </script>
<script src="../JS/notebooks.js" type="text/javascript">    </script>

<html>
<head>
    <title>Add Notebook</title>
    <style>
        <%@include file='../css/addNotebook.css' %>
    </style>
    <center><h1>Add New Notebook Type</h1></center>
</head>
<body>
<%!
    CPUDao cpuDao;
    List<CPU> cpu;
    MemoryDao memoryDao;
    List<Memory> memory;
    VendorDao vendorDao;
    List<Vendor> vendor;
%>
<%
    cpuDao = ((NotebookServiceImpl) Menu.service).getCpuDao();
    cpu = (List<CPU>)cpuDao.findAll();
    memoryDao = ((NotebookServiceImpl) Menu.service).getMemoryDao();
    memory = (List<Memory>)memoryDao.findAll();
    vendorDao = ((NotebookServiceImpl) Menu.service).getVendorDao();
    vendor = (List<Vendor>)vendorDao.findAll();
    request.setAttribute("cpu", cpu);
    request.setAttribute("memory", memory);
    request.setAttribute("vendor", vendor);
%>

<form action="/AddNotebook1" method="get">
    <img src="../img/addLaptop1.jpg" align="left"
         style="margin-right: 20px">

    <div id="divVenSel">
        <label for="venSel">VENDOR:</label>
        <select size="1" name="venSel" id="venSel">
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
        </select>
        <br>
    </div>

    <label for="model">MODEL:</label>
    <input  type="text" value="${modelA}" placeholder="Satellite S-2535LX"
            name="model" id="model"><br>

    <c:set var="now" value="<%=new Date()%>"/>
    <fmt:formatDate var="today" pattern="dd.MM.yyyy" value="${now}"/>
    <%--<fmt:parseDate value="${manDateA}" pattern="dd.MM.yyyy" var="manDateAParsed"/>--%>
    <fmt:formatDate var="manDateAFormatted" pattern="dd.MM.yyyy"
                    value="${manDateA}"/>

    <label for="manDate">DATE OF MANUFACTURE:</label>
    <input  type="text" value="${manDateAFormatted}" placeholder="${today}"
            name="manDate" id="manDate"><br>

    <div id="divCpuSel">
        <label for="cpuSel">CPU:</label>
        <select size="1" name="cpuSel" id="cpuSel">
            <option disabled>select item</option>
            <c:forEach var="c" items="${cpu}" varStatus="cnt">
                <c:choose>
                    <c:when test="${SelInxC != null}">
                        <c:if test="${cnt.index == SelInxC}">
                            <option value="${c.id}"selected>${c.toString()}</option>
                        </c:if>
                        <c:if test="${cnt.index != SelInxC}">
                            <option value="${c.id}">${c.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:when test="${SelValC != null}">
                        <c:if test="${c.id == SelValC}">
                            <option value="${c.id}"selected>${c.toString()}</option>
                        </c:if>
                        <c:if test="${c.id != SelValC}">
                            <option value="${c.id}">${c.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.toString()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
    </div>

    <div id="divMemorySel">
        <label for="memorySel">MEMORY:</label>
        <select size="1" name="memorySel" id="memorySel">
            <option disabled>select item</option>
            <c:forEach var="m" items="${memory}" varStatus="cnt">
                <c:choose>
                    <c:when test="${SelInxM != null}">
                        <c:if test="${cnt.index == SelInxM}">
                            <option value="${m.id}"selected>${m.toString()}</option>
                        </c:if>
                        <c:if test="${cnt.index != SelInxM}">
                            <option value="${m.id}">${m.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:when test="${SelValM != null}">
                        <c:if test="${m.id == SelValM}">
                            <option value="${m.id}"selected>${m.toString()}</option>
                        </c:if>
                        <c:if test="${m.id != SelValM}">
                            <option value="${m.id}">${m.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <option value="${m.id}">${m.toString()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
    </div>

    <br><br>
    <br><br>
    <p style="text-align: center;">
        <input type="submit" name="back2Menu" value="&longleftarrow; to Menu">
        <input type="submit" name="add" value="Add">

        <input type="button" value="Clear All"
               onclick="setSelectIndex('venSel', 1);
               clearElemContent('manDate');
               clearElemContent('model');
               setSelectIndex('cpuSel', 1);
               setSelectIndex('memorySel', 1);
               clearElemContent('message');">
    </p>
    <br>
    <br>
    <label id="message" style="width: 100%; margin-top:10%;
            color:${messageColor}; text-align: center; font-size:x-large">${messageText}
    </label>
</form>

</body>
</html>