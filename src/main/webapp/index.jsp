<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="springnotes.service.NotebookService" %>
<%@ page import="springnotes.dao.VendorDao" %>
<%@ page import="java.util.Arrays" %>
<%--<%@ include file="loginTaxi.jsp"%>--%>



<script src="../../../JS/phone.js" type="text/javascript">    </script>
<%--<script src="JS/alexEx.js" type="text/javascript">    </script>--%>

<%!
    VendorDao vendorDao;
    NotebookService service;
%>
    <%
        request.setAttribute("cnt", 1);
        request.setAttribute("totPages", 5);
        request.setAttribute("mode", "1");
        request.setAttribute("SelInx", "0");
//        request.setAttribute("kkk", "null5");

//        service = new NotebookServiceImpl();
//        request.setAttribute("NotebookService", service);
//        vendorDao = ((NotebookServiceImpl)service).getVendorDao();
//        request.setAttribute("vendorA", vendorDao.findAll());
//                Arrays.asList("ven1","ven2","ven5","ven4"));

    %>
    <%--<%request.getRequestDispatcher("hw7.notes/pages/addCPU.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("hw7.notes/pages/1.jsp").forward(request,response); %>--%>
    <jsp:forward page="menu.html" />

    <%--<%request.getRequestDispatcher("../webapp/WEB-INF/hw7.notes/pages/menu.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("hw7.notes/pages/addMemory.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("hw7.notes/pages/addVendor.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("session14/pages/EmployeeLogin.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("hw6.notes/pages/menu.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("hw5.auth/UserAuth.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("hw8.taxi/pages/loginTaxi.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("hw5.users/UserAddRender.jsp").forward(request,response); %>--%>
    <%--<%request.getRequestDispatcher("/pages/debug_phoneTable.jsp").forward(request,response); %>--%>
<html>
<head>
    <%--<button name="but1" onclick=" getTree();">press</button>--%>
    <%--<style>--%>
        <%--<%@ include file="css/table.css"%>--%>
    <%--</style>--%>
</head>
<body>
    <%--<p onclick="functionFun()">text1</p>--%>
    <button id="createTableButton" onclick="generatePhoneTable()">Create Table
    </button>

</body>
</html>

<%--<html>--%>
    <%--<head>--%>
        <%--<title>Welcome to JS</title>--%>
        <%--<script>--%>
            <%--console.log('Hello JS1');--%>
        <%--</script>--%>
    <%--</head>--%>
    <%--<body>--%>
    <%--&lt;%&ndash;<script src="JS/phone.js" type="text/javascript">    </script>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<script src="JS/getDOMTree.js" type="text/javascript">    </script>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<%request.getRequestDispatcher("loginTaxi.jsp").forward(request, response);%>&ndash;%&gt;--%>

    <%--<p id="outText1" onclick=" global = 2; functionFun()" >text1</p>--%>
    <%--<p id="outText2"></p>--%>

    <%--&lt;%&ndash;<script>&ndash;%&gt;--%>
        <%--&lt;%&ndash;var vector = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);&ndash;%&gt;--%>
        <%--&lt;%&ndash;document.getElementById("outText1").innerHTML = vector.toString();&ndash;%&gt;--%>

        <%--&lt;%&ndash;document.getElementById("outText2").innerHTML = mirrorArray(vector, 4).toString();&ndash;%&gt;--%>
    <%--&lt;%&ndash;</script>&ndash;%&gt;--%>

    <%--</body>--%>

<%--</html>--%>