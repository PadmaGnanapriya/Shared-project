package lk.uok.dao;

import lk.uok.db.DBConnection;
import lk.uok.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DatabseAccessOrders {
    public static void addOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("INSERT INTO Orders VALUES (?,?,?)");
        pstm.setObject(1,dto.getId());
        pstm.setObject(2, dto.getDate());
        pstm.setObject(3,dto.getCustomerId());
        pstm.executeUpdate();
    }

    public static void deleteOrder(String id) throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("DELETE FROM Orders WHERE id=?");
        pstm.setObject(1, id);
        pstm.execute();
    }

    public static String getLastOrderId() throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM Orders ");
        ResultSet set = pstm.executeQuery();
        String id=null;
        while (set.next()){
            id= set.getString(1);
        }
        return id;


    }
}
