<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 03.11.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="">
<head>
    <title>Hello AngularJS</title>
    <script src="../../JS/angular.js"></script>
</head>
<body>
    <div ng-init="person={name:'Alex', age:12}; vector=[1,2,3,4,'потому что 5']">
      <p>{{ vector[4] + '' + person.age + jj}}</p>
        <ul>
            <li ng-repeat="el in vector"> {{el}} </li>
        </ul>
    </div>
</body>
</html>
