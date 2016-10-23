
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="../JS/phone.js" type="text/javascript">    </script>

<html>
<head>
    <%--<style>--%>
        <%--<%@include file='../css/table.css'%>--%>
    <%--</style>--%>
    <title>Phone Table</title>
  </head>
<body>
<button id="createTableButton"
        onclick="generatePhoneTable(document.getElementById('BookSize').value)">
        Create Table</button>
<label for="BookSize" style="margin: 0 10px 0 20px ">Quantity of phone numbers:</label>
<input type="text" id="BookSize" style="width: 40px" value="50">

<%--<button type="submit" class="but">press</button>--%>
    <%--<table>--%>
        <%--<thead>--%>
            <%--<tr>--%>
                <%--<th class="column-left-bottom-top ">col1</th>--%>
                <%--<th class="column-left-bottom-top right">col2</th>--%>
                <%--<th>col3</th>--%>
            <%--</tr>--%>
        <%--</thead>--%>

        <%--<tbody>--%>
            <%--<tr >--%>
                <%--<td class="column-left-bottom"> ddewf1</td>--%>
                <%--<td class="column-left-bottom right">ddewf2</td>--%>
                <%--<td>ddewf3</td>--%>
            <%--</tr>--%>
        <%--</tbody>--%>
    <%--</table>--%>

</body>
</html>
