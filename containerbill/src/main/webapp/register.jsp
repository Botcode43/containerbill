<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Seller Registration</title>
</head>
<body>
    <h2>Register as Seller</h2>
    <form action="register_controller" method="post">
        Name: <input type="text" name="name" required><br><br>
        Email: <input type="email" name="email" required><br><br>
        Password: <input type="password" name="password" required><br><br>
        <input type="submit" value="Register">
    </form>

    <%-- Success message (from login.jsp redirect) --%>
    <%
        String msg = request.getParameter("msg");
        if (msg != null) {
    %>
        <p style="color:green;"><%= msg %></p>
    <% } %>
</body>
</html>
