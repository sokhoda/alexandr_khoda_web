<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 28.02.16
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="myapp">
<head>
    <title>Hello AngularJS</title>
    <script src="../../JS/angular.js"></script>
    <%--<script src="../../JS/controller.js"></script>--%>
    <script src="../../JS/ajaxa.js"></script>
    <%--<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.18/angular-resource.js"></script>--%>
</head>
<body>
<div ng-controller="HelloCtrl">
    Your name: <input type="text" ng-model="name" >
    <button ng-click="update()">update</button>
    <p>Model state: {{name}}</p>
    <p>{{greeting}}</p>


<p>{{randInt}}<p>
    <div>
        <ul>
            <li ng-repeat="el in vector track by $index"> {{$index + 1}}. {{el}}
            </li>
        </ul>
    </div>

</div>
<p>{{vector}}<p>
</body>
</html>
