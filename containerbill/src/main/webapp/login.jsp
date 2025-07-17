<%@ page session="true" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Seller Login - Container Billing System</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body { background-color: #f0f2f5; }
    .login-container { display: flex; min-height: 100vh; }
    .left-panel {
      flex: 1; background-color: white;
      display: flex; justify-content: center;
      align-items: center; padding: 40px;
    }
    .right-panel {
      background: linear-gradient(to bottom, #0d9488, #14b8a6);
      color: white; padding: 60px 30px;
      display: flex; flex-direction: column;
      justify-content: center; align-items: center;
      flex: 1; text-align: center;
    }
    .right-panel h1 { font-size: 2.5rem; margin-bottom: 1rem; }
    .right-panel p { max-width: 320px; font-size: 0.95rem; }
    .form-box { width: 100%; max-width: 400px; }
    .form-box h2 { margin-bottom: 30px; }
    .form-control:focus {
      box-shadow: 0 0 0 0.2rem rgba(20, 184, 166, 0.25);
    }
    @media (max-width: 768px) {
      .login-container { flex-direction: column; }
      .left-panel, .right-panel { flex: none; width: 100%; }
    }
  </style>
</head>
<body>

<div class="login-container">
  <!-- Left Panel -->
  <div class="left-panel">
    <div class="form-box">
      <h2>Sign In to Your Account</h2>

      <% String err = (String) request.getAttribute("errorMsg"); %>
      <% if (err != null) { %>
        <div class="alert alert-danger"><%= err %></div>
      <% } %>

      <form method="post" action="LoginServlet">
        <div class="mb-3">
          <label for="email" class="form-label">Email Address</label>
          <input type="email" class="form-control" id="email" name="email" required placeholder="Enter your email">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input type="password" class="form-control" id="password" name="password" required placeholder="Enter your password">
        </div>
        <div class="d-grid gap-2">
          <button type="submit" class="btn btn-success">Sign In</button>
          <a href="register.jsp" class="btn btn-outline-secondary">Create an Account</a>
        </div>
      </form>
    </div>
  </div>

  <!-- Right Panel -->
  <div class="right-panel">
    <img src="https://cdn-icons-png.flaticon.com/512/10307/10307942.png" alt="Container Logo" width="90">
    <h1>Welcome Seller</h1>
    <p>Log in to manage your container space, input shipping details, and access billing summaries easily.</p>
  </div>
</div>


</body>
</html>
