package imp;

import db.dbconnection;
import model.Container;
import operation.ContainerOperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContainerImpl implements ContainerOperations {

    public boolean insertContainer(int sellerId, double length, double width, double height) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL insert_container(?, ?, ?, ?)}");
            cs.setInt(1, sellerId);
            cs.setFloat(2, (float) length);
            cs.setFloat(3, (float) width);
            cs.setFloat(4, (float) height);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContainerLength(int containerId, double length) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_container_length(?, ?)}");
            cs.setInt(1, containerId);
            cs.setFloat(2, (float) length);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContainerWidth(int containerId, double width) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_container_width(?, ?)}");
            cs.setInt(1, containerId);
            cs.setFloat(2, (float) width);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContainerHeight(int containerId, double height) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_container_height(?, ?)}");
            cs.setInt(1, containerId);
            cs.setFloat(2, (float) height);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteContainer(int containerId) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL delete_container(?)}");
            cs.setInt(1, containerId);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSeller(int sellerId) {
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL delete_seller(?)}");
            cs.setInt(1, sellerId);
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
   

    public List<Container> getContainersBySeller(int sellerId) {
        List<Container> list = new ArrayList<>();
        try (Connection conn = dbconnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL get_containers_by_seller(?)}");
            cs.setInt(1, sellerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Container c = new Container();
                c.setContainerId(rs.getInt("container_id"));
                c.setLength(rs.getDouble("length"));
                c.setWidth(rs.getDouble("width"));
                c.setHeight(rs.getDouble("height"));
                c.setVolume(rs.getDouble("volume_m3"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public double getLatestContainerVolume(int sellerId) {
        double volume = 0;
        try (Connection conn = dbconnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL get_latest_container_volume(?)}")) {
            cs.setInt(1, sellerId);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                volume = rs.getDouble("volume");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return volume;
    }

}
