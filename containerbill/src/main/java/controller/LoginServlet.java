package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Seller;
import imp.SellerImpl;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        SellerImpl dao = new SellerImpl();
        Seller seller = dao.loginSeller(email, password);

        if (seller != null) {
            HttpSession session = request.getSession();
            session.setAttribute("sellerId", seller.getId());
            session.setAttribute("sellerName", seller.getName());
            session.setAttribute("sellerEmail", seller.getEmail());
            session.setAttribute("sellerPassword", seller.getPassword()); 
            response.sendRedirect("containerinput.jsp");
        } else {
            request.setAttribute("errorMsg", "Invalid email or password");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
