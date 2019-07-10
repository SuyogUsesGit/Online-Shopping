<html>
<head>
    <style>
        p {
            color: red;
        }

        form, p {
            text-align: center;
        }

    </style>
</head>
<body>


<form action="/LoginServlet" method="post">
    <h1>Welcome user! Please Login to continue</h1>
    Username: <input type="text" name="username"> <br><br>
    Password: <input type="password" name="password"> <br><br>
    <input type="submit" value="Login"> <br><br>
    <input type="button" onclick="myFunc()" value="Register"> <br><br>
</form>


<p>${diff_msgs}</p>

<script language="JavaScript">
    function myFunc() {
        location.href = "register.jsp";
    }
</script>


</body>
</html>
