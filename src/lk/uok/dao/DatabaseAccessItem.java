package lk.uok.dao;

import lk.uok.db.DBConnection;
import lk.uok.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DatabaseAccessItem {
    public static void addItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("INSERT INTO item VALUE(?,?,?,?)");
        pstm.setObject(1,dto.getCode());
        pstm.setObject(2,dto.getDescription());
        pstm.setObject(3,dto.getUnitPrice());
        pstm.setObject(4,dto.getQtyOnHand());
        pstm.executeUpdate();
    }

    public static ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        Connection con =DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM item");
        ResultSet resultSet=pstm.executeQuery();
        ArrayList<ItemDTO> itemDTOList=new ArrayList<>();
        while (resultSet.next()){
            ItemDTO itemDTO=new ItemDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3), resultSet.getInt(4));
            itemDTOList.add(itemDTO);

        }
        return itemDTOList;
    }

    public static ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM item WHERE code=?");
        pstm.setObject(1,code);
        ResultSet resultSet=pstm.executeQuery();
        if(resultSet.next()){
            ItemDTO itemDTO=new ItemDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3), resultSet.getInt(4));
            return itemDTO;
        }
        else
            return null;
    }

    public static ArrayList<ItemDTO> searchbar(String code) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOList2=new ArrayList<>();
        Connection con= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("SELECT * FROM item WHERE code LIKE ? OR unitPrice LIKE ? OR description LIKE ? ");
        pstm.setObject(1,"%"+code+"%");
        pstm.setObject(2,"%"+code+"%");
        pstm.setObject(3,"%"+code+"%");
        ResultSet resultSet=pstm.executeQuery();
        while(resultSet.next()){
            ItemDTO itemDTO=new ItemDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3), resultSet.getInt(4));
            itemDTOList2.add(itemDTO);
        }
        return itemDTOList2;
    }

    public static void updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        Connection con=DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("UPDATE item SET description =?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setObject(1,dto.getDescription());
        pstm.setObject(2,dto.getUnitPrice());
        pstm.setObject(3,dto.getQtyOnHand());
        pstm.setObject(4,dto.getCode());
        pstm.executeUpdate();
    }

    public static boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        Connection con=DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement("DELETE FROM item WHERE code=?");
        pstm.setObject(1,code);
        return pstm.executeUpdate()>0;
//        return false;
    }
}
