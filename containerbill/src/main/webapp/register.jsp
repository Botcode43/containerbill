<%@ page import="java.util.*" %>
<%@ page session="true" %>
<%
    // Check session attributes
    String sellerId = session.getAttribute("sellerId") != null ? session.getAttribute("sellerId").toString() : "";
    String name = session.getAttribute("sellerName") != null ? session.getAttribute("sellerName").toString() : "";
    String email = session.getAttribute("sellerEmail") != null ? session.getAttribute("sellerEmail").toString() : "";
    String password = session.getAttribute("sellerPassword") != null ? session.getAttribute("sellerPassword").toString() : "";

    boolean isUpdate = !sellerId.equals("");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><%= isUpdate ? "Update Profile" : "Seller Registration" %> - Container Billing System</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    body {
      background-color: #f0f2f5;
    }

    .register-container {
      display: flex;
      min-height: 100vh;
    }

    .left-panel {
      background: linear-gradient(to bottom, #0d9488, #14b8a6);
      color: white;
      padding: 60px 30px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      flex: 1;
    }

    .left-panel h1 {
      font-size: 2.5rem;
      margin-bottom: 1rem;
    }

    .left-panel p {
      max-width: 320px;
      text-align: center;
      font-size: 0.95rem;
    }

    .right-panel {
      flex: 1;
      background-color: white;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 40px;
    }

    .form-box {
      width: 100%;
      max-width: 400px;
    }

    .form-box h2 {
      margin-bottom: 30px;
    }

    .form-control:focus {
      box-shadow: 0 0 0 0.2rem rgba(20, 184, 166, 0.25);
    }

    @media (max-width: 768px) {
      .register-container {
        flex-direction: column;
      }

      .left-panel, .right-panel {
        flex: none;
        width: 100%;
      }
    }
  </style>
</head>
<body>

<div class="register-container">
  <!-- Left Panel -->
  <div class="left-panel text-center">
    <img src="https://cdn-icons-png.flaticon.com/512/10307/10307942.png" alt="Container Logo" width="90">
    <h1><%= isUpdate ? "Hello Again!" : "Welcome Seller" %></h1>
    <p><%= isUpdate ? "Update your profile details below." : "Start managing your container space, shipping costs, and generate automated bills with ease." %></p>
  </div>

  <!-- Right Panel -->
  <div class="right-panel">
    <div class="form-box">
      <h2><%= isUpdate ? "Update Your Profile" : "Create Your Account" %></h2>
      <% String err = (String) request.getAttribute("errorMsg"); %>
<% if (err != null) { %>
  <div class="alert alert-danger" role="alert">
    <%= err %>
  </div>
<% } %>
      
      <form method="post" action="<%= isUpdate ? "ContainerServlet" : "RegisterServlet" %>">
        <% if (isUpdate) { %>
         <input type="hidden" name="action" value="updateSeller">
          <input type="hidden" name="sellerId" value="<%= sellerId %>">
        <% } %>

        <div class="mb-3">
          <label for="name" class="form-label">Full Name</label>
          <input type="text" class="form-control" id="name" name="name" required placeholder="Enter your name" value="<%= name %>">
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email Address</label>
          <input type="email" class="form-control" id="email" name="email" required placeholder="Enter your email" value="<%= email %>">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input type="password" class="form-control" id="password" name="password" required placeholder="Enter a password" value="<%= password %>">
        </div>

        <% if (!isUpdate) { %>
        <div class="form-check mb-3">
          <input class="form-check-input" type="checkbox" id="terms" required>
          <label class="form-check-label" for="terms">
            I agree to the <a href="#">Terms & Conditions</a> of Container Billing System
          </label>
        </div>
        <% } %>

        <div class="d-grid gap-2">
          <button type="submit" class="btn btn-success"><%= isUpdate ? "Update" : "Register" %></button>
          <% if (!isUpdate) { %>
            <a href="login.jsp" class="btn btn-outline-secondary">Already Registered? Sign In</a>
          <% } else { %>
            <a href="containerInput.jsp" class="btn btn-outline-secondary">Cancel</a>
          <% } %>
        </div>
      </form>
    </div>
  </div>
</div>



</body>
</html>
