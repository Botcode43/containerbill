<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Container" %>
<%
  Integer sellerId = (Integer) session.getAttribute("sellerId");
  String sellerName = (String) session.getAttribute("sellerName");
  String sellerEmail = (String) session.getAttribute("sellerEmail");
  List<Container> containers = (List<Container>) request.getAttribute("containers");

  String mode = request.getParameter("mode");
  boolean isUpdateMode = "edit".equals(mode);
  String cid = request.getParameter("containerId") != null ? request.getParameter("containerId") : "";
  String len = request.getParameter("length") != null ? request.getParameter("length") : "";
  String wid = request.getParameter("width") != null ? request.getParameter("width") : "";
  String hei = request.getParameter("height") != null ? request.getParameter("height") : "";
  String formAction = isUpdateMode ? "update" : "add";
  String buttonText = isUpdateMode ? "Update Container" : "Add Container";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Container Input - Container Billing System</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f5faff;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    .dashboard-wrapper { display: flex; min-height: 100vh; }
    .sidebar {
      width: 250px;
      background-color: #e8f0fe;
      padding: 30px 20px;
      color: #1d4ed8;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }
    .sidebar h4 { font-weight: bold; margin-bottom: 30px; text-align: center; }
    .sidebar .nav-link {
      background-color: white;
      border: 2px solid #1d4ed8;
      border-radius: 8px;
      color: #1d4ed8;
      font-weight: 600;
      margin-bottom: 10px;
      padding: 12px;
      text-align: center;
      transition: all 0.2s ease;
    }
    .sidebar .nav-link:hover { background-color: #1d4ed8; color: white; }
    .account-toggle { display: none; }
    .account-label {
      text-align: center;
      cursor: pointer;
      margin-top: 20px;
      display: block;
    }
    .account-label img { width: 40px; }
    .account-info-box {
      display: none;
      background-color: white;
      padding: 15px;
      border-radius: 10px;
      border: 1px solid #ddd;
      margin-top: 15px;
      font-size: 14px;
    }
    .account-toggle:checked + .account-label + .account-info-box {
      display: block;
    }
    .account-info-box p { margin-bottom: 5px; }
    .main-content {
      flex: 1;
      padding: 40px;
      background-color: #ffffff;
      border-top-left-radius: 20px;
      border-bottom-left-radius: 20px;
      box-shadow: -2px 0 10px rgba(0,0,0,0.05);
    }
    .form-title {
      font-weight: bold;
      font-size: 24px;
      margin-bottom: 25px;
      color: #2c3e50;
    }
    .card-style {
      background-color: #f0f6ff;
      border-radius: 15px;
      padding: 30px;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.05);
    }
    .btn-custom { background-color: #1d4ed8; border: none; color: white; }
    .btn-custom:hover { background-color: #2563eb; }
    .table thead { background-color: #1d4ed8; color: white; }
  </style>
</head>
<body>
<div class="dashboard-wrapper">
  <div class="sidebar">
    <div>
      <h4>Container Dashboard</h4>
      <nav class="nav flex-column">
        <a class="nav-link" href="#">Container Input</a>
        <a class="nav-link" href="productInput.jsp">Product Input</a>
        <a class="nav-link" href="shippingInput.jsp">Shipping Input</a>
        <a class="nav-link" href="taxInput.jsp">Tax Input</a>
        <a class="nav-link" href="billingSummary.jsp">Billing Summary</a>
      </nav>
    </div>
    <div class="text-center">
      <input type="checkbox" id="toggleAccount" class="account-toggle">
      <label for="toggleAccount" class="account-label">
        <img src="https://cdn-icons-png.flaticon.com/512/1144/1144760.png" alt="Account Icon"><br>
        <span>My Account</span>
      </label>
      <div class="account-info-box">
        <p><strong>Seller ID:</strong> <%= sellerId %></p>
        <p><strong>Name:</strong> <%= sellerName %></p>
        <p><strong>Email:</strong> <%= sellerEmail %></p>
        <div class="d-grid gap-2 mt-3">
          <form action="ContainerServlet" method="post">
            <input type="hidden" name="action" value="redirectUpdateSeller">
            <button type="submit" class="btn btn-outline-primary btn-sm">Update</button>
          </form>
          <form action="ContainerServlet" method="post">
            <input type="hidden" name="action" value="deleteSeller">
            <button type="submit" class="btn btn-outline-danger btn-sm">Delete</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div class="main-content">
    <div class="card-style mb-4">
      <div class="form-title">Add New Container</div>
      <form action="ContainerServlet" method="post">
        <input type="hidden" name="action" value="<%= formAction %>">
        <input type="hidden" name="containerId" value="<%= cid %>">
        <div class="row mb-3">
          <div class="col-md-4">
            <label class="form-label">Length (m)</label>
            <input type="number" name="length" class="form-control" step="0.01" value="<%= len %>" required>
          </div>
          <div class="col-md-4">
            <label class="form-label">Width (m)</label>
            <input type="number" name="width" class="form-control" step="0.01" value="<%= wid %>" required>
          </div>
          <div class="col-md-4">
            <label class="form-label">Height (m)</label>
            <input type="number" name="height" class="form-control" step="0.01" value="<%= hei %>" required>
          </div>
        </div>
        <div class="text-end">
          <button type="submit" class="btn btn-custom px-4"><%= buttonText %></button>
        </div>
      </form>
    </div>
    <div class="card-style">
      <div class="form-title">Container History</div>
      <div class="table-responsive">
        <table class="table table-bordered table-hover text-center">
          <thead>
            <tr>
              <th>Container ID</th>
              <th>Length (m)</th>
              <th>Width (m)</th>
              <th>Height (m)</th>
              <th>Volume (m³)</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <% if (containers != null && !containers.isEmpty()) {
                 for (Container c : containers) {
                   double lengthM = c.getLength();
                   double widthM = c.getWidth();
                   double heightM = c.getHeight();
                   double volumeM3 = lengthM * widthM * heightM;
            %>
              <tr>
                <td><%= c.getContainerId() %></td>
                <td><%= String.format("%.2f", lengthM) %></td>
                <td><%= String.format("%.2f", widthM) %></td>
                <td><%= String.format("%.2f", heightM) %></td>
                <td><%= String.format("%.2f", volumeM3) %></td>
                <td>
                  <form action="containerinput.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="mode" value="edit">
                    <input type="hidden" name="containerId" value="<%= c.getContainerId() %>">
                    <input type="hidden" name="length" value="<%= c.getLength() %>">
                    <input type="hidden" name="width" value="<%= c.getWidth() %>">
                    <input type="hidden" name="height" value="<%= c.getHeight() %>">
                    <button type="submit" class="btn btn-sm btn-warning">Update</button>
                  </form>
                  <form action="ContainerServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="containerId" value="<%= c.getContainerId() %>">
                    <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                  </form>
                </td>
              </tr>
            <%  }
               } else { %>
              <tr><td colspan="6">No container data available.</td></tr>
            <% } %>
          </tbody>
        </table>
      </div>
      <div class="d-flex justify-content-between mt-4">
                <form action="ContainerServlet" method="get">
          <input type="hidden" name="page" value="next">
          <button type="submit" class="btn btn-outline-primary">show</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
