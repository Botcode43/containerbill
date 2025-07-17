package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import imp.ShippingImpl;
import model.ShippingCost;

@WebServlet("/ShippingServlet")
public class ShippingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ShippingImpl impl = new ShippingImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        int sellerId = (int) session.getAttribute("sellerId");

        switch (action) {
            case "add":
                float oceanFreight = Float.parseFloat(request.getParameter("ocean_freight"));
                float rentPerDay = Float.parseFloat(request.getParameter("rent_per_day"));
                int numDays = Integer.parseInt(request.getParameter("num_days"));
                float portHandling = Float.parseFloat(request.getParameter("port_handling"));
                float inlandTransport = Float.parseFloat(request.getParameter("inland_transport"));
                float insurance = Float.parseFloat(request.getParameter("insurance"));

                ShippingCost shipping = new ShippingCost(0, sellerId, oceanFreight, rentPerDay, numDays, portHandling, inlandTransport, insurance, oceanFreight);
                impl.insertShippingCost(shipping);
                break;

            case "delete":
                int shippingIdDel = Integer.parseInt(request.getParameter("shippingId"));
                impl.deleteShippingCost(shippingIdDel);
                break;

            case "update":
                int shippingId = Integer.parseInt(request.getParameter("shippingId"));
                float uOceanFreight = Float.parseFloat(request.getParameter("ocean_freight"));
                float uRentPerDay = Float.parseFloat(request.getParameter("rent_per_day"));
                int uNumDays = Integer.parseInt(request.getParameter("num_days"));
                float uPortHandling = Float.parseFloat(request.getParameter("port_handling"));
                float uInlandTransport = Float.parseFloat(request.getParameter("inland_transport"));
                float uInsurance = Float.parseFloat(request.getParameter("insurance"));

                impl.updateOceanFreight(shippingId, uOceanFreight);
                impl.updateRentPerDay(shippingId, uRentPerDay);
                impl.updateNumDays(shippingId, uNumDays);
                impl.updatePortHandling(shippingId, uPortHandling);
                impl.updateInlandTransport(shippingId, uInlandTransport);
                impl.updateInsurance(shippingId, uInsurance);
                break;
        }

        // Redirect to refresh the list
        response.sendRedirect("ShippingServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int sellerId = (int) session.getAttribute("sellerId");

        List<ShippingCost> shippingCosts = impl.getShippingBySeller(sellerId);

        request.setAttribute("shippingCosts", shippingCosts);
        request.getRequestDispatcher("shippingInput.jsp").forward(request, response);
    }
}
