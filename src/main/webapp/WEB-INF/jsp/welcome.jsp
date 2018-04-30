<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" ng-app="rfmApp">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/<style></style>.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
</head>
<body ng-controller="MainController">

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">RFM Finance Dashboard</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <%--<li><a href="#about">About</a></li>--%>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <div class="starter-template">
        <h1>Assembly Finances Summary</h1>
    </div>

    <main role="main" class="container">
        <br>

        <div class="my-3 p-3 bg-white rounded box-shadow">

            <form name="myForm" ng-submit="submitForm()">
                <div class="form-group">
                    <label for="mySelect">Select Assembly</label>
                    <select ng-model="selectedAssembly" class="form-control" id="mySelect" ng-options="assembly.name for assembly in assemblyList" ng-change="submitForm()">
                        <option value="">--- Please select an Assembly ---</option>
                    </select>
                </div>
                <div class="form-group">
                    <div id="reportrange" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%">
                        <i class="fa fa-calendar"></i>&nbsp;
                        <span></span> <i class="fa fa-caret-down"></i>
                    </div>
                </div>

                <div class="alert alert-success" role="alert" ng-show="onSuccess">
                    <span>{{successMessage}}</span>
                </div>
                <div class="alert alert-danger" role="alert" ng-show="onError">
                    <span>{{errorMessage}}</span>
                </div>
            </form>
            <br>
            <div class="panel panel-default" ng-show="onSuccess">
                <div class="panel-heading">Transactions Summary</div>
                <div class="panel-body">

                    <ul class="list-group">
                        <li class="list-group-item">
                            <span class="badge">R {{totalIncome}}</span>
                            Total Income
                        </li>
                        <li class="list-group-item">
                            <span class="badge">R {{totalExpenses}}</span>
                            Total Expenses
                        </li>
                       <%-- <li class="list-group-item">
                            <span class="badge">R {{totalExpenses}}</span>
                            Net Income for the Period
                        </li>--%>
                    </ul>
                </div>
            </div>
            <br>
            <!-- Default form register -->
            <table id="example" class="display" style="width:100%" >

            </table>


        </div>

        <div ng-show="onSuccess">
            <form class="inline">
                <button type="button" class="btn btn-primary" ng-click="downloadCSV()"><span class="glyphicon glyphicon-download-alt"></span>Download CSV</button>
                <%--<button type="button" class="btn btn-primary">Send To Email</button>--%>
            </form>
        </div>


    </main>

</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js" ></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script>
    var assembliesArr = new Array();
    <c:forEach items="${assemblyList}" var="assembly" varStatus="status">
    assembliesArr[${status.index}]= {
        id : ${assembly.id},
        name : "${assembly.name}"
    };


    </c:forEach>
</script>
<script src="js/app.js"></script>
</body>

</html>