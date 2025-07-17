<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%
  Integer sellerId = (Integer) session.getAttribute("sellerId");
  String sellerName = (String) session.getAttribute("sellerName");
  String sellerEmail = (String) session.getAttribute("sellerEmail");
  List<Product> products = (List<Product>) request.getAttribute("products");

  String mode = request.getParameter("mode");
  String editLength = request.getParameter("length");
  String editWidth = request.getParameter("width");
  String editHeight = request.getParameter("height");
  String editPrice = request.getParameter("unit_price");
  String productId = request.getParameter("productId");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Product Input - Container Billing System</title>
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
      border-radius: 8px;
      font-weight: 600;
      margin-bottom: 10px;
      padding: 12px;
      text-align: center;
      transition: all 0.2s ease;
      color: #1d4ed8;
      background-color: white;
    }
    .sidebar .nav-link:hover {
      background-color: #2563eb;
      color: white;
    }
    .sidebar .nav-link.active {
      background-color: #1d4ed8;
      color: white !important;
    }
    .account-toggle { display: none; }
    .account-label {
      text-align: center;
      cursor: pointer;
      margin-top: 20px;
      display: block;
    }
    .account-label img { width: 40px; }
    .account-info-box {
      background-color: white;
      padding: 15px;
      border-radius: 10px;
      border: 1px solid #ddd;
      margin-top: 15px;
      font-size: 14px;
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
      <h4>Product Dashboard</h4>
      <nav class="nav flex-column">
        <a class="nav-link" href="containerinput.jsp">Container Input</a>
        <a class="nav-link active" href="#">Product Input</a>
        <a class="nav-link" href="shippingInput.jsp">Shipping Input</a>
        <a class="nav-link" href="taxInput.jsp">Tax Input</a>
        <a class="nav-link" href="billingSummary.jsp">Billing Summary</a>
      </nav>
    </div>
    <div class="text-center">
      <label class="account-label">
        <img src="https://cdn-icons-png.flaticon.com/512/1144/1144760.png" alt="Account Icon"><br>
        <span>My Account</span>
      </label>
      <div class="account-info-box">
        <p><strong>Seller ID:</strong> <%= sellerId %></p>
        <p><strong>Name:</strong> <%= sellerName %></p>
        <p><strong>Email:</strong> <%= sellerEmail %></p>
      </div>
    </div>
  </div>
  <div class="main-content">
    <div class="card-style mb-4">
      <div class="form-title">Add New Product</div>
      <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="<%= (mode != null && mode.equals("edit")) ? "update" : "add" %>">
        <% if (mode != null && mode.equals("edit")) { %>
          <input type="hidden" name="productId" value="<%= productId %>">
        <% } %>
        <div class="row mb-3">
          <div class="col-md-3">
            <label class="form-label">Length (m)</label>
            <input type="number" name="length" class="form-control" step="0.01" required value="<%= (editLength != null) ? editLength : "" %>">
          </div>
          <div class="col-md-3">
            <label class="form-label">Width (m)</label>
            <input type="number" name="width" class="form-control" step="0.01" required value="<%= (editWidth != null) ? editWidth : "" %>">
          </div>
          <div class="col-md-3">
            <label class="form-label">Height (m)</label>
            <input type="number" name="height" class="form-control" step="0.01" required value="<%= (editHeight != null) ? editHeight : "" %>">
          </div>
          <div class="col-md-3">
            <label class="form-label">Unit Price ($)</label>
            <input type="number" name="unit_price" class="form-control" step="0.01" required value="<%= (editPrice != null) ? editPrice : "" %>">
          </div>
        </div>
        <div class="text-end">
          <button type="submit" class="btn btn-custom px-4"><%= (mode != null && mode.equals("edit")) ? "Update Product" : "Add Product" %></button>
        </div>
      </form>
    </div>

    <div class="card-style">
      <div class="form-title">Product History</div>
      <div class="table-responsive">
        <table class="table table-bordered table-hover text-center">
          <thead>
            <tr>
              <th>ID</th>
              <th>Length</th>
              <th>Width</th>
              <th>Height</th>
              <th>Unit Price</th>
              <th>Volume (m³)</th>
              <th>Units Fit</th>
              <th>Actions</th>
            </tr>
          </thead>
          <%
            Double containerVolAttr = (Double) request.getAttribute("latestContainerVolume");
            double latestContainerVolume = (containerVolAttr != null) ? containerVolAttr : 0.0;
          %>
          <tbody>
            <% if (products != null && !products.isEmpty()) {
                 for (Product p : products) {
                   double volume = p.getLength() * p.getWidth() * p.getHeight();
                   int unitsFit = (volume > 0 && latestContainerVolume > 0)
                                  ? (int) Math.floor(latestContainerVolume / volume)
                                  : 0;
            %>
              <tr>
                <td><%= p.getProductId() %></td>
                <td><%= p.getLength() %></td>
                <td><%= p.getWidth() %></td>
                <td><%= p.getHeight() %></td>
                <td><%= p.getUnitPrice() %></td>
                <td><%= String.format("%.2f", volume) %></td>
                <td><%= unitsFit %></td>
                <td>
                  <form action="ProductServlet" method="get" style="display:inline;">
                    <input type="hidden" name="mode" value="edit">
                    <input type="hidden" name="productId" value="<%= p.getProductId() %>">
                    <input type="hidden" name="length" value="<%= p.getLength() %>">
                    <input type="hidden" name="width" value="<%= p.getWidth() %>">
                    <input type="hidden" name="height" value="<%= p.getHeight() %>">
                    <input type="hidden" name="unit_price" value="<%= p.getUnitPrice() %>">
                    <button type="submit" class="btn btn-sm btn-warning">Update</button>
                  </form>
                  <form action="ProductServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="productId" value="<%= p.getProductId() %>">
                    <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                  </form>
                </td>
              </tr>
            <%   }
               } else { %>
              <tr><td colspan="8">No product data available.</td></tr>
            <% } %>
          </tbody>
        </table>
      </div>
      <div class="d-flex justify-content-between mt-4">
        <form action="ProductServlet" method="get">
          <input type="hidden" name="page" value="next">
          <button type="submit" class="btn btn-outline-primary">show</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
