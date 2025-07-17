package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import model.Seller;
import imp.SellerImpl;









@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Seller seller = new Seller(0, name, email, password);
        SellerImpl dao = new SellerImpl();

        boolean success = dao.registerSeller(seller);

        if (success) {
            response.sendRedirect("login.jsp");
        } else {
            // Show error on same page
            request.setAttribute("errorMsg", "Email already registered or error occurred.");
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }
    }
}

	
       
   


