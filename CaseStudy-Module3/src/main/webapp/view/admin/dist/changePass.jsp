<%@ page import="dao.AccountDAO" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 10/7/2020
  Time: 5:07 PM
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">Change Pass</h3></div>
                            <div class="card-body">
                                <form method="post">
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputPassword">Password New</label>
                                        <input class="form-control py-4" name="newpassword" id="inputPassword" type="password" placeholder="Enter password" />
                                    </div>
                                    <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <input class="btn btn-primary" type="submit" value="Submit">
                                        <a href="/customers" class="btn btn-success"><span>Back</span></a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/view/admin/dist/js/scripts.js"></script>
<% if(AccountDAO.created){%>
<%String str = "<script>alert('Password changed successfully')</script>";%>
<%=str%>
<%}%>
<% AccountDAO.created = false; %>
</body>
</html>


