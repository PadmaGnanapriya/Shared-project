package lk.uok.dao;

import lk.uok.controller.CustomerFormController;
import lk.uok.db.DBConnection;
import lk.uok.dto.CustomerDTO;
import lk.uok.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DatabaseAccessCustomer {

    //CURD operations here -Create/add new, Update, Read/Retrieve/Find, Delete
    public static void addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");
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
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM Customer");
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
        PreparedStatement pstm=con.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setObject(1,id);
        return pstm.executeUpdate()>0;

    }

    public static CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection con=DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setObject(1,id);
        ResultSet resultSet=pstm.executeQuery();
        if(resultSet.next()){
            CustomerDTO customerDTO=new CustomerDTO(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
            return customerDTO;
        }else
            return null;
    }

    public static ArrayList<CustomerDTO> searchbar(String code) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOList2=new ArrayList<>();
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM Customer WHERE id LIKE ? OR name LIKE ? OR address LIKE ? OR salary LIKE ? ");
        pstm.setObject(1,"%"+code+"%");
        pstm.setObject(2,"%"+code+"%");
        pstm.setObject(3,"%"+code+"%");
        pstm.setObject(4,"%"+code+"%");
        ResultSet resultSet=pstm.executeQuery();
        while(resultSet.next()){
            CustomerDTO customerDTO=new CustomerDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), resultSet.getDouble(4));
            customerDTOList2.add(customerDTO);
        }
        return customerDTOList2;
    }

    public static void updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("UPDATE Customer SET name=?,address=?, salary=? WHERE id=?");
        pstm.setObject(1,dto.getName());
        pstm.setObject(2,dto.getAddress());
        pstm.setObject(3,dto.getSalary());
        pstm.setObject(4, dto.getId());
        CustomerFormController customerFormController=new CustomerFormController();

        if(pstm.executeUpdate()>0) System.out.println("Done properly");
    }
}
