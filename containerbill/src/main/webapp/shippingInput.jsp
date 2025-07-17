<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ShippingCost" %> 
<%
  Integer sellerId = (Integer) session.getAttribute("sellerId");
  String sellerName = (String) session.getAttribute("sellerName");
  String sellerEmail = (String) session.getAttribute("sellerEmail");
  List<ShippingCost> shippingCosts = (List<ShippingCost>) request.getAttribute("shippingCosts");

  String mode = request.getParameter("mode");
  String shippingId = request.getParameter("shippingId");

  String oceanFreight = request.getParameter("ocean_freight");
  String rentPerDay = request.getParameter("rent_per_day");
  String numDays = request.getParameter("num_days");
  String portHandling = request.getParameter("port_handling");
  String inlandTransport = request.getParameter("inland_transport");
  String insurance = request.getParameter("insurance");

  boolean isEdit = "edit".equals(mode);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Shipping Input - Container Billing System</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body { background-color: #f5faff; font-family: 'Segoe UI', Tahoma, sans-serif; }
    .dashboard-wrapper { display: flex; min-height: 100vh; }
    .sidebar { width: 250px; background-color: #e8f0fe; padding: 30px 20px; color: #1d4ed8; display: flex; flex-direction: column; justify-content: space-between; }
    .sidebar .nav-link { border-radius: 8px; font-weight: 600; margin-bottom: 10px; padding: 12px; text-align: center; transition: 0.2s; color: #1d4ed8; background-color: white; }
    .sidebar .nav-link.active, .sidebar .nav-link:hover { background-color: #1d4ed8; color: white; }
    .main-content { flex: 1; padding: 40px; background-color: white; border-top-left-radius: 20px; border-bottom-left-radius: 20px; box-shadow: -2px 0 10px rgba(0,0,0,0.05); }
    .form-title { font-weight: bold; font-size: 24px; margin-bottom: 25px; color: #2c3e50; }
    .card-style { background-color: #f0f6ff; border-radius: 15px; padding: 30px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.05); }
    .btn-custom { background-color: #1d4ed8; border: none; color: white; }
    .btn-custom:hover { background-color: #2563eb; }
    .table thead { background-color: #1d4ed8; color: white; }
  </style>
</head>
<body>
<div class="dashboard-wrapper">
  <div class="sidebar">
    <h4>Shipping Dashboard</h4>
    <nav class="nav flex-column">
      <a class="nav-link" href="containerInput.jsp">Container Input</a>
      <a class="nav-link" href="productInput.jsp">Product Input</a>
      <a class="nav-link active" href="#">Shipping Input</a>
      <a class="nav-link" href="taxInput.jsp">Tax Input</a>
      <a class="nav-link" href="billingSummary.jsp">Billing Summary</a>
    </nav>
    <div class="account-info-box text-center mt-3 bg-white p-3 rounded">
      <p><strong>Seller ID:</strong> <%= sellerId %></p>
      <p><strong>Name:</strong> <%= sellerName %></p>
      <p><strong>Email:</strong> <%= sellerEmail %></p>
    </div>
  </div>
  <div class="main-content">
    <div class="card-style mb-4">
      <div class="form-title"><%= isEdit ? "Update Shipping Cost" : "Add New Shipping Cost" %></div>
      <form action="ShippingServlet" method="post">
        <% if (isEdit) { %>
          <input type="hidden" name="action" value="update">
          <input type="hidden" name="shippingId" value="<%= shippingId %>">

        <% } else { %>
          <input type="hidden" name="action" value="add">
        <% } %>
        <div class="row mb-3">
          <div class="col-md-2"><input class="form-control" name="ocean_freight" step="0.01" placeholder="Ocean Freight" value="<%= oceanFreight != null ? oceanFreight : "" %>" required></div>
          <div class="col-md-2"><input class="form-control" name="rent_per_day" step="0.01" placeholder="Rent/Day" value="<%= rentPerDay != null ? rentPerDay : "" %>" required></div>
          <div class="col-md-2"><input class="form-control" name="num_days" type="number" placeholder="No. of Days" value="<%= numDays != null ? numDays : "" %>" required></div>
          <div class="col-md-2"><input class="form-control" name="port_handling" step="0.01" placeholder="Port Handling" value="<%= portHandling != null ? portHandling : "" %>" required></div>
          <div class="col-md-2"><input class="form-control" name="inland_transport" step="0.01" placeholder="Inland Transport" value="<%= inlandTransport != null ? inlandTransport : "" %>" required></div>
          <div class="col-md-2"><input class="form-control" name="insurance" step="0.01" placeholder="Insurance" value="<%= insurance != null ? insurance : "" %>" required></div>
        </div>
        <div class="text-end">
          <button type="submit" class="btn btn-custom px-4"><%= isEdit ? "Update" : "Add" %></button>
        </div>
      </form>
    </div>

    <div class="card-style">
      <div class="form-title">Shipping Cost History</div>
      <div class="table-responsive">
        <table class="table table-bordered text-center">
          <thead>
            <tr>
              <th>ID</th>
              <th>Ocean Freight</th>
              <th>Rent/Day</th>
              <th>Days</th>
              <th>Port Handling</th>
              <th>Inland Transport</th>
              <th>Insurance</th>
              <th>Total Cost</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <% if (shippingCosts != null && !shippingCosts.isEmpty()) {
                 for (ShippingCost s : shippingCosts) {
                   float total = s.getOceanFreight() + (s.getRentPerDay() * s.getNumDays()) +
                                 s.getPortHandling() + s.getInlandTransport() + s.getInsurance();
            %>
              <tr>
                <td><%= s.getShippingId() %></td>
                <td><%= s.getOceanFreight() %></td>
                <td><%= s.getRentPerDay() %></td>
                <td><%= s.getNumDays() %></td>
                <td><%= s.getPortHandling() %></td>
                <td><%= s.getInlandTransport() %></td>
                <td><%= s.getInsurance() %></td>
                <td><%= String.format("%.2f", total) %></td>
                <td>
                  <form method="post" action="shippingInput.jsp" style="display:inline;">
                    <input type="hidden" name="mode" value="edit">
                   
                  <input type="hidden" name="shippingId" value="<%= s.getShippingId() %>">
                    <input type="hidden" name="ocean_freight" value="<%= s.getOceanFreight() %>">
                    <input type="hidden" name="rent_per_day" value="<%= s.getRentPerDay() %>">
                    <input type="hidden" name="num_days" value="<%= s.getNumDays() %>">
                    <input type="hidden" name="port_handling" value="<%= s.getPortHandling() %>">
                    <input type="hidden" name="inland_transport" value="<%= s.getInlandTransport() %>">
                    <input type="hidden" name="insurance" value="<%= s.getInsurance() %>">
                    <button class="btn btn-warning btn-sm">Update</button>
                  </form>
                  <form method="post" action="ShippingServlet" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="shippingId" value="<%= s.getShippingId() %>">
                    <button class="btn btn-danger btn-sm">Delete</button>
                  </form>
                </td>
              </tr>
            <% } } else { %>
              <tr><td colspan="9">No shipping data found.</td></tr>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>
