<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 10/6/2020
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Customer Manager</title>
    <link href="/view/admin/dist/css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="/view/admin/dist/cssmain/style.css">
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/customers">Customer</a>
    <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" action="${pageContext.request.contextPath}/customers/search" method="post">
        <div class="input-group">
            <input class="form-control" type="text" name="search" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" />
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit"><i class="fas fa-search"></i></button>
            </div>
        </div>
    </form>
    <!-- Navbar-->
    <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="/accounts">Change Password</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/">Logout</a>
            </div>
        </li>
    </ul>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <a class="nav-link collapsed" href="/customers"  aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        List Customer
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <a class="nav-link collapsed" href="/provinces"  aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        List Province
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <a class="nav-link collapsed" href="/customerTypes" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        List Customer Type
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                </div>
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-xl">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-8 row-logo" style="display: flex;">
                                    <div class="logo-content"><h2>Customer Type List</h2></div>

                                </div>
                                <div class="col-sm-4">
                                    <a href="${pageContext.request.contextPath}/customerTypes/add" class="btn btn-success"><i
                                            class="material-icons">&#xE147;</i> <span>Add New Customer Type</span></a>
                                </div>
                            </div>
                        </div>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Customer Type Name</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="customerType" items="${listCustomerType}">
                                <tr>
                                    <td>${customerType.getIdCustomerType()}</td>
                                    <td>
                                        <a href="/customerTypes/view?id=${customerType.idCustomerType}"> ${customerType.getTypeCusName()}</a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/customerTypes/delete?id=${customerType.idCustomerType}" class="delete"><i
                                                class="material-icons"
                                                data-toggle="tooltip"
                                                title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
<%--        <footer class="py-4 bg-light mt-auto">--%>
<%--            <div class="container-fluid">--%>
<%--                <div class="d-flex align-items-center justify-content-between small">--%>
<%--                    <div class="text-muted">Copyright &copy; Your Website 2020</div>--%>
<%--                    <div>--%>
<%--                        <a href="#">Privacy Policy</a>--%>
<%--                        &middot;--%>
<%--                        <a href="#">Terms &amp; Conditions</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </footer>--%>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/view/admin/dist/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src=/view/admin/dist/assets/demo/assets/demo/chart-area-demo.js"></script>
<script src="/view/admin/dist/assets/demo/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src=/view/admin/dist/assets/demo/assets/demo/datatables-demo.js"></script>
</body>
</html>

