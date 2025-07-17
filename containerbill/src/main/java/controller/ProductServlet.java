package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import imp.ContainerImpl;
import imp.ProductImpl;
import model.Product;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductImpl impl = new ProductImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        int sellerId = (int) session.getAttribute("sellerId");

        switch (action) {
            case "add":
                float length = Float.parseFloat(request.getParameter("length"));
                float width = Float.parseFloat(request.getParameter("width"));
                float height = Float.parseFloat(request.getParameter("height"));
                float price = Float.parseFloat(request.getParameter("unit_price"));
                Product p = new Product(0, sellerId, length, width, height, price);
                impl.insertProduct(p);
                break;

            case "delete":
                int productIdDel = Integer.parseInt(request.getParameter("productId"));
                impl.deleteProduct(productIdDel);
                break;

            case "update":
                int productId = Integer.parseInt(request.getParameter("productId"));
                float uLength = Float.parseFloat(request.getParameter("length"));
                float uWidth = Float.parseFloat(request.getParameter("width"));
                float uHeight = Float.parseFloat(request.getParameter("height"));
                float uPrice = Float.parseFloat(request.getParameter("unit_price"));

                impl.updateProductLength(productId, uLength);
                impl.updateProductWidth(productId, uWidth);
                impl.updateProductHeight(productId, uHeight);
                impl.updateProductPrice(productId, uPrice);
                break;

        }

        // Redirect to show updated list
        response.sendRedirect("ProductServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int sellerId = (int) session.getAttribute("sellerId");

        ProductImpl productImpl = new ProductImpl();
        List<Product> products = productImpl.getProductsBySeller(sellerId);

        
        ContainerImpl containerImpl = new ContainerImpl();
        double latestContainerVolume = containerImpl.getLatestContainerVolume(sellerId);
        System.out.println("Latest container volume: " + latestContainerVolume); 
        request.setAttribute("latestContainerVolume", latestContainerVolume);

        request.setAttribute("products", products);
        request.getRequestDispatcher("productInput.jsp").forward(request, response);
    }
    

}

