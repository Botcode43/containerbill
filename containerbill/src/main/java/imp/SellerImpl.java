package imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import db.dbconnection;
import model.Seller;
import operation.SellerOperations ;


import java.sql.*;


public class SellerImpl implements SellerOperations {

    @Override
    public boolean registerSeller(Seller s) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL register_seller(?, ?, ?)}");
            cs.setString(1, s.getName());
            cs.setString(2, s.getEmail());
            cs.setString(3, s.getPassword());
            cs.execute();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Email already exists.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Seller loginSeller(String email, String password) {
        Seller seller = null;

        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL login_check_email(?, ?)}");
            cs.setString(1, email);
            cs.setString(2, password);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                String message = rs.getString("message");
                if (message.equalsIgnoreCase("LOGIN SUCCESSFULLY")) {
                    seller = new Seller();
                    seller.setId(rs.getInt("seller_id"));
                    seller.setName(rs.getString("name"));
                    seller.setEmail(rs.getString("email"));
                    seller.setPassword(password); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return seller;
    }

    @Override
    public boolean updateSellerName(int sellerId, String name) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_seller_name(?, ?)}");
            cs.setInt(1, sellerId);
            cs.setString(2, name);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean updateSellerEmail(int sellerId, String email) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_seller_email(?, ?)}");
            cs.setInt(1, sellerId);
            cs.setString(2, email);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSellerPassword(int sellerId, String password) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_seller_password(?, ?)}");
            cs.setInt(1, sellerId);
            cs.setString(2, password);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSeller(int sellerId) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL delete_seller(?)}");
            cs.setInt(1, sellerId);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // This prints the actual error. Check your server logs or console.
            return false;
        }
    }
    
    }


    
