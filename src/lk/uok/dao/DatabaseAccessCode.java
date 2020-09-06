package lk.uok.dao;

import lk.uok.controller.CustomerFormController;
import lk.uok.db.DBConnection;
import lk.uok.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DatabaseAccessCode {

    //CURD operations here -Create/add new, Update, Read/Retrieve/Find, Delete
    public static void addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
        pstm.setObject(1, dto.getId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3,dto.getAddress());
        pstm.setObject(4,dto.getSalary());
        CustomerFormController customerFormController=new CustomerFormController();
//        if(pstm.executeUpdate()>0) customerFormController.successfullyRan();
//        else customerFormController.errorOnRun();
        if(pstm.executeUpdate()>0) System.out.println("Done properly");
    }

    public static ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM customer");
        ResultSet set = pstm.executeQuery();
        ArrayList<CustomerDTO> customerList=new ArrayList();
        while (set.next()){
            customerList.add(new CustomerDTO(set.getString(1),
                    set.getString(2),set.getString(3),
                    set.getDouble(4)
            ));
        }
        return customerList;

    }

    public static boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection con=DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("DELETE FROM WHERE id=?");
        pstm.setObject(1,id);
        return pstm.executeUpdate()>0;

    }

    public static CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection con=DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM customer WHERE id=?");
        pstm.setObject(1,id);
        ResultSet resultSet=pstm.executeQuery();
        if(resultSet.next()){
            CustomerDTO customerDTO=new CustomerDTO(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
            return customerDTO;
        }else
            return null;


    }
}
