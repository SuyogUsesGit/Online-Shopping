<%--
  Created by IntelliJ IDEA.
  User: mj
  Date: 7/8/2019
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        form {
            text-align: center;
        }
    </style>
</head>
<body>
<form action="/RegisterServlet" method="post">
    <h1>Please Register to create a new Account</h1>
    Username: <input type="text" name="username"> <br><br>
    Password: <input type="password" name="password"> <br><br>

    <input type="submit" value="Register">
</form>

</body>
</html>
