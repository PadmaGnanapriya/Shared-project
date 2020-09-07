package lk.uok.dao;

import lk.uok.db.DBConnection;
import lk.uok.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DatabseAccessOrders {
    public static void addOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("INSERT INTO orders VALUES (?,?,?)");
        pstm.setObject(1,dto.getId());
        pstm.setObject(2, dto.getDate());
        pstm.setObject(3,dto.getCustomerId());
        pstm.executeUpdate();
    }
}
