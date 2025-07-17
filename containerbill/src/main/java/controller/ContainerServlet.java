package controller;

import model.Container;
import model.Seller;
import imp.ContainerImpl;
import imp.SellerImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.*;

import java.util.List;

@WebServlet("/ContainerServlet")
public class ContainerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int sellerId = (int) session.getAttribute("sellerId");
        
        ContainerImpl containerImpl = new ContainerImpl();
        
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("containerinput.jsp");
            return;
        }

        switch (action) {
            case "add": {
                double length = Double.parseDouble(request.getParameter("length"));
                double width = Double.parseDouble(request.getParameter("width"));
                double height = Double.parseDouble(request.getParameter("height"));
                containerImpl.insertContainer(sellerId, length, width, height);
                break;
            }
            case "delete": {
                int containerId = Integer.parseInt(request.getParameter("containerId"));
                containerImpl.deleteContainer(containerId);
                break;
            }
            case "update": {
                int containerId = Integer.parseInt(request.getParameter("containerId"));
                double length = Double.parseDouble(request.getParameter("length"));
                double width = Double.parseDouble(request.getParameter("width"));
                double height = Double.parseDouble(request.getParameter("height"));
                containerImpl.updateContainerLength(containerId, length);
                containerImpl.updateContainerWidth(containerId, width);
                containerImpl.updateContainerHeight(containerId, height);
                break;
            }
            case "deleteSeller": {
                SellerImpl sellerImpl = new SellerImpl();
                boolean deleted = sellerImpl.deleteSeller(sellerId);

                if (deleted) {
                    session.invalidate();
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("errorMsg", "Deletion failed. Please try again.");
                    request.getRequestDispatcher("containerinput.jsp").forward(request, response);
                }
                return;
            }

            case "redirectUpdateSeller": {
                session.setAttribute("updateMode", true);
                response.sendRedirect("register.jsp");
                return;
            }
            case "updateSeller": {
                Integer sellerId1 = (Integer) session.getAttribute("sellerId");
                if (sellerId1 == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                SellerImpl sellerImpl = new SellerImpl();
                boolean nameUpdated = sellerImpl.updateSellerName(sellerId1, name);
                boolean emailUpdated = sellerImpl.updateSellerEmail(sellerId1, email);
                boolean passwordUpdated = sellerImpl.updateSellerPassword(sellerId1, password);

                if (nameUpdated && emailUpdated && passwordUpdated) {
                    session.setAttribute("sellerName", name);
                    session.setAttribute("sellerEmail", email);
                    session.setAttribute("sellerPassword", password);
                    response.sendRedirect("containerinput.jsp");
                } else {
                    request.setAttribute("errorMsg", "Update failed.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                return;
            }

        }
        response.sendRedirect("containerinput.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int sellerId = (int) session.getAttribute("sellerId");
        ContainerImpl containerImpl = new ContainerImpl();
        
        List<Container> containers = containerImpl.getContainersBySeller(sellerId);
        request.setAttribute("containers", containers);
        request.getRequestDispatcher("containerinput.jsp").forward(request, response);
    }
}
