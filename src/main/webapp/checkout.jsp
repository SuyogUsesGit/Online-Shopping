
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mj
  Date: 7/9/2019
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout Details</title>
    <style>
        body {
            text-align: center;
        }
        input {
            border: none;
        }
        table {
            margin: 0 auto 0 auto;
        }
        #lgout {
            float: right;
        }
        .total {
            margin-left: 14%;
        }
        #total {
            text-align: left;
            padding-left: 3%;
            font-size: 110%;
        }
    </style>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<form action="/InvalidateServlet" method="post">
    <input id="lgout" type="submit" value="Logout">
</form>
<h1 id="h1_1">${checkoutMsg}</h1>

<h2 id="h2_1">You purchased the following items:</h2><br><br>


<table id="table1" border="2" cellpadding="10">
    <th>Item Name</th>
    <th>Quantity Added to Cart</th>
    <th>Price</th>

    <c:forEach var="itemBean" items="${viewCartItemsList}">
        <tr>
            <td>${itemBean.item.name}</td>
            <td>${itemBean.item.quantity}</td>
            <td>${itemBean.item.price}</td>
        </tr>
    </c:forEach>
</table><br><br>

<div class="total">
    <h2 id="h2_2">Total: <input id="total" name="total" value="${totalAmount}" readonly></h2><br><br>
</div><br><br>

<form method="post" action="/WelcomeServlet">
    <input type="submit" value="Continue Shopping"><br><br>
</form>

<script>
    msg = "${checkoutMsg}";

    if(msg.charAt(msg.length - 1) == '.') {
        document.getElementById("h2_1").style.visibility = "hidden";
        document.getElementById("table1").style.visibility = "hidden";
        document.getElementById("h2_2").style.visibility = "hidden";
        document.getElementById("total").style.visibility = "hidden";
        document.getElementById("h1_1").style.color = "red";
    } else {
        document.getElementById("h1_1").style.color = "green";
    }

    window.history.forward();
    function noBack() { window.history.forward(); }

</script>
<%
    if((session.getAttribute("userId") == null)) response.sendRedirect("index.jsp");
    if(request.getAttribute("checkoutMsg") == null) response.sendRedirect("welcome.jsp");
%>
</body>
</html>
