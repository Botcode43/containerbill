package imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import db.dbconnection;
import model.Seller;
import operation.SellerOperation ;


public class sellerOperationImp implements SellerOperation {

	@Override
	 public void insertSeller(Seller seller) {
        try {
        	Connection con = dbconnection.getConnection();
        	
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO seller_ports (name, email, password) VALUES (?, ?, ?)");
            
            ps.setString(1, seller.getName());
            
            ps.setString(2, seller.getEmail());
            
            ps.setString(3, seller.getPassword());
            
            
            ps.executeUpdate();
            
            System.out.println("Seller registered successfully!");
            
            con.close();
            
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Email already exists.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

}
