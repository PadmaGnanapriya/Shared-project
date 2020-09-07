package lk.uok.dao;

import lk.uok.db.DBConnection;
import lk.uok.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DatabasseAcessOrderDetail {
    public static void addOrderDetail(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("INSERT INTO orderdetail(orderId, itemCode,unitPrice) VALUES (?,?,?)");
        pstm.setObject(1,dto.getOrderId());
        pstm.setObject(2,dto.getCode());
        pstm.setObject(3,dto.getUnitPrice());
        pstm.executeUpdate();
    }
}
