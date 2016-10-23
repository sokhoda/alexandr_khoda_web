<%@ page import="hw7.notes.domain.Vendor" %>
<%@ page import="hw7.notes.dao.VendorDao" %>
<%@ page import="java.util.List" %>
<%@ page import="hw7.notes.service.NotebookServiceImpl" %>
<%@ page import="hw7.notes.view.Menu" %>
<%@ page import="java.util.Arrays" %>
<%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 23.02.2016
  Time: 2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%! String s1;
    VendorDao vendorDao;
    Integer mode;
    List<Vendor> vendor = null;
    String headPart;
    String venSelInx;
    String venSelVal;
%>
<%vendorDao = ((NotebookServiceImpl) Menu.service).getVendorDao();
    vendor = (List<Vendor>)vendorDao.findAll();

    venSelInx = (String) request.getAttribute("venSelInx");

    request.setAttribute("vendor", vendor);
//    request.setAttribute("venSelInx", venSelInx);
%>

<c:set var="vendor3" value="12"/>
${venSelInx}

<select size="1" name="venSel" id="venSel">

    <option disabled>select item</option>
<c:forEach var="ven" items="${vendor}" varStatus="cnt">
    <c:if test="${cnt.index == venSelInx}">
        <option value="${ven.id}" selected>${cnt.index}</option>
    </c:if>
    <c:if test="${cnt.index != venSelInx}">
        <option value="${ven.id}">${cnt.index}</option>
    </c:if>
</c:forEach>
    </select>
</body>
</html>
