package imp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import operation.ShippingDAO;
import model.ShippingCost;
import db.dbconnection;

public class ShippingImpl implements ShippingDAO {

    public boolean insertShippingCost(ShippingCost s) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL insert_shipping_details(?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, s.getSellerId());
            cs.setFloat(2, s.getOceanFreight());
            cs.setFloat(3, s.getRentPerDay());
            cs.setInt(4, s.getNumDays());
            cs.setFloat(5, s.getPortHandling());
            cs.setFloat(6, s.getInlandTransport());
            cs.setFloat(7, s.getInsurance());
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteShippingCost(int id) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL delete_shipping(?)}");
            cs.setInt(1, id);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOceanFreight(int id, float val) {
        return updateFloatField("update_shipping_ocean_freight", id, val);
    }

    public boolean updateRentPerDay(int id, float val) {
        return updateFloatField("update_shipping_rent_per_day", id, val);
    }

    public boolean updateNumDays(int id, int days) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_shipping_num_days(?, ?)}");
            cs.setInt(1, id);
            cs.setInt(2, days);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePortHandling(int id, float val) {
        return updateFloatField("update_shipping_port_handling", id, val);
    }

    public boolean updateInlandTransport(int id, float val) {
        return updateFloatField("update_shipping_inland_transport", id, val);
    }

    public boolean updateInsurance(int id, float val) {
        return updateFloatField("update_shipping_insurance", id, val);
    }

    private boolean updateFloatField(String procedureName, int id, float value) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL " + procedureName + "(?, ?)}");
            cs.setInt(1, id);
            cs.setFloat(2, value);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ShippingCost> getShippingBySeller(int sellerId) {
        List<ShippingCost> list = new ArrayList<>();
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL get_shipping_by_seller(?)}");
            cs.setInt(1, sellerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ShippingCost s = new ShippingCost();
                s.setShippingId(rs.getInt("shipping_id"));
                s.setOceanFreight(rs.getFloat("ocean_freight"));
                s.setRentPerDay(rs.getFloat("rent_per_day"));
                s.setNumDays(rs.getInt("num_days"));
                s.setPortHandling(rs.getFloat("port_handling"));
                s.setInlandTransport(rs.getFloat("inland_transport"));
                s.setInsurance(rs.getFloat("insurance"));
                s.setTotalShippingCost(rs.getFloat("total_shipping_cost"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
