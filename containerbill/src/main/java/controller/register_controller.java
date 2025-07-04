package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import model.Seller;
import imp.sellerOperationImp;




@WebServlet("/register_controller")
public class register_controller extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Seller seller = new Seller();
        seller.setName(name);
        seller.setEmail(email);
        seller.setPassword(password);

        
        seller.insertSeller(seller);

        resp.sendRedirect("login.jsp?msg=Registration Successful. Please login.");
        
       
        
		
		
		
	}
	
	
       
   

}
