package imp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import operation.ProductDAO;
import model.Product;
import db.dbconnection;

public class ProductImpl implements ProductDAO {

    @Override
    public boolean insertProduct(Product p) {
        try (Connection con = dbconnection.getConnection();
             CallableStatement cs = con.prepareCall("{call insert_product(?, ?, ?, ?, ?)}")) {
            cs.setInt(1, p.getSellerId());
            cs.setFloat(2, p.getLength());
            cs.setFloat(3, p.getWidth());
            cs.setFloat(4, p.getHeight());
            cs.setFloat(5, p.getUnitPrice());
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> getProductsBySeller(int sellerId) {
        List<Product> list = new ArrayList<>();
        float containerVolume = 0;

        try (Connection conn = dbconnection.getConnection()) {

            // Step 1: Get latest container volume for the seller
            CallableStatement containerStmt = conn.prepareCall(
                "SELECT length * width * height AS volume FROM containers WHERE seller_id = ? ORDER BY container_id DESC LIMIT 1"
            );
            containerStmt.setInt(1, sellerId);
            ResultSet containerRs = containerStmt.executeQuery();
            if (containerRs.next()) {
                containerVolume = containerRs.getFloat("volume");
            }

            // Step 2: Get all products of the seller
            CallableStatement cs = conn.prepareCall("{CALL get_products_by_seller(?)}");
            cs.setInt(1, sellerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setLength(rs.getFloat("length"));
                p.setWidth(rs.getFloat("width"));
                p.setHeight(rs.getFloat("height"));
                p.setUnitPrice(rs.getFloat("unit_price"));

                float volume = p.getLength() * p.getWidth() * p.getHeight();
                p.setVolume(volume);  // must exist in Product model

                int unitsFit = containerVolume > 0 ? (int) Math.floor(containerVolume / volume) : 0;
                p.setUnitsFit(unitsFit); // must exist in Product model

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateProductLength(int productId, float length) {
        try (Connection con = dbconnection.getConnection()) {
            CallableStatement cs = con.prepareCall("{CALL update_product_length(?, ?)}");
            cs.setInt(1, productId);
            cs.setFloat(2, length);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProductWidth(int productId, float width) {
        try (Connection con = dbconnection.getConnection()) {
            CallableStatement cs = con.prepareCall("{CALL update_product_width(?, ?)}");
            cs.setInt(1, productId);
            cs.setFloat(2, width);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProductHeight(int productId, float height) {
        try (Connection con = dbconnection.getConnection()) {
            CallableStatement cs = con.prepareCall("{CALL update_product_height(?, ?)}");
            cs.setInt(1, productId);
            cs.setFloat(2, height);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProductPrice(int productId, float price) {
        try (Connection con = dbconnection.getConnection()) {
            CallableStatement cs = con.prepareCall("{CALL update_product_price(?, ?)}");
            cs.setInt(1, productId);
            cs.setFloat(2, price);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        try (Connection con = dbconnection.getConnection();
             CallableStatement cs = con.prepareCall("{call delete_product(?)}")) {
            cs.setInt(1, productId);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Optional helper (not used currently)
    private boolean callSimpleUpdate(String procedure, int id, float value) {
        try (Connection con = dbconnection.getConnection();
             CallableStatement cs = con.prepareCall(procedure)) {
            cs.setInt(1, id);
            cs.setFloat(2, value);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
