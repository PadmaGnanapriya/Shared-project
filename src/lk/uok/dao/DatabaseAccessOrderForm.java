package lk.uok.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.uok.db.DBConnection;
import lk.uok.dto.CustomerDTO;
import lk.uok.view.tm.OrderTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DatabaseAccessOrderForm {

    public static ObservableList<OrderTM> getAllDate() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("select o.id, o.date, o.customerId,c.name, od.itemCode, od.qty, od.unitPrice from orders o, orderdetail od, customer c where o.id=od.orderId and c.id=o.customerId;");
        ResultSet set = pstm.executeQuery();
        ObservableList<OrderTM> customerList= FXCollections.observableArrayList();
        while (set.next()){
            customerList.add(new OrderTM(set.getString(1), set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getInt(6), set.getDouble(7)));
        }
        return customerList;
    }
}
