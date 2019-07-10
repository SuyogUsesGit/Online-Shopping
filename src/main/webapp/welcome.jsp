<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mj
  Date: 7/8/2019
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <style>
        input {
            border: none;
        }
        form, input, .cart-msg {
            text-align: center;
        }
        #table1 {
           margin: 0 auto 0 auto;
        }
        .cart-msg {
            color: green;
        }
        #lgout {
            float: right;
        }
    </style>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<form action="/InvalidateServlet" method="post">
    <input id="lgout" type="submit" value="Logout">
</form>

<form id="frm" action="/CartServlet" method="post">
    <h1>Welcome ${username}</h1> <br><br>
    <h2>${availabilityMsg} </h2>
    <table id="table1" border="2" cellpadding="10">
        <th>Item Name</th>
        <th>Quantity in Stock</th>
        <th>Price</th>
        <th>Enter Quantity</th>
        <th>Total Price</th>
        <% int x = 0; %>
        <c:forEach var="item" items="${itemsList}">
            <tr>
                <% ++x; %>
                <td><input name="<%= "ip1_" + x %>" value="${item.name}" readonly></td>
                <td><input name="<%= "ip2_" + x %>" value="${item.quantity}" readonly></td>
                <td><input name="<%= "ip3_" + x %>" value="${item.price}" readonly></td>
                <td><input id="<%= "ip4_" + x %>" name="<%= "ip4_" + x %>" type="number" value="0" onkeyup="myFunc(this.value, ${item.quantity}, ${item.price}, <%= x %>)" onchange="myFunc(this.value, ${item.quantity}, ${item.price}, <%= x %>)"></td>
                <td><input id="<%= "ip5_" + x %>" name="<%= "ip5_" + x %>" value="0" readonly></td>
            </tr>
        </c:forEach>
    </table>

    <br><br>
    <input type="submit" value="Update Cart"> <br><br>
    <input type="submit" value="View Cart" onclick="cartBtn2()">

</form>

<h2 class="cart-msg">${addedToCart_msg}</h2>

<script language="JavaScript">

    var msg = "${availabilityMsg}";

    if(msg.charAt(0) == 'S') document.getElementById("table1").style.visibility = "hidden";

    function myFunc(value, quantity, price, x) {
        if(value > quantity) { document.getElementById("ip4_" + x).value = quantity; value = quantity; }
        if(value < 0) { document.getElementById("ip4_" + x).value = 0; value = 0; }
        document.getElementById("ip5_" + x).value = Math.round(Math.trunc(value) * price * 100) / 100;
    }


    function cartBtn2() {
       document.getElementById("frm").action = "/ViewCartServlet";
    }

    window.history.forward();
    function noBack() { window.history.forward(); }
</script>

</body>
</html>
