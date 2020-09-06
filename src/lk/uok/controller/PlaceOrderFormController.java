package lk.uok.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.uok.dao.DatabaseAccessCode;
import lk.uok.dao.DatabaseAccessItem;
import lk.uok.dto.CustomerDTO;
import lk.uok.dto.ItemDTO;
import lk.uok.view.tm.CustomerTM;
import lk.uok.view.tm.ItemTM;
import lk.uok.view.tm.PlaceOrderTM;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class PlaceOrderFormController {
    public ComboBox cmbItemCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyONHand;
    public TextField txtQty;
    public TableView tbl;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colTotal;
    public TextField txtOderID;
    public ComboBox cmbXustomerID;
    public TextField txtCustomerName;
    public TextField txtTotal;
    public Button btnAdd;

    ObservableList<PlaceOrderTM> orderTMS=FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadCustomer();
        loadItem();
    }

    private void loadItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> customerDTOS = DatabaseAccessItem.getAllItem();
        ObservableList<String> itemCodes = FXCollections.observableArrayList();
        for (ItemDTO dto : customerDTOS) {
            itemCodes.add(dto.getCode());
        }
        cmbItemCode.setItems(itemCodes);
    }

    private void loadCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = DatabaseAccessCode.getAllCustomers();
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        for (CustomerDTO dto : customerDTOS) {
            customerIds.add(dto.getId());
        }
        cmbXustomerID.setItems(customerIds);
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtCustomerName.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Select Customer Id", "alert", JOptionPane.ERROR_MESSAGE);
            cmbXustomerID.requestFocus();
            return;
        }
        if(txtQty.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Select amount of item", "alert", JOptionPane.ERROR_MESSAGE);
            txtQty.requestFocus();
            return;
        }
        ItemDTO itemDTO=DatabaseAccessItem.searchItem(cmbItemCode.getValue().toString());

        int priviousQty = 0;
        double tot=0;
        for(int i=0; i<orderTMS.size();i++){
            if(cmbItemCode.getValue().toString().equals(orderTMS.get(i).getCode())){
                priviousQty=orderTMS.get(i).getQty();
                orderTMS.remove(i);
            }
        }
        PlaceOrderTM tm=new PlaceOrderTM(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),Integer.parseInt(txtQty.getText())+priviousQty);
        orderTMS.add(tm);
        tbl.setItems(orderTMS);
        System.out.println(orderTMS.size());
        for(int i=0;i<orderTMS.size();i++) tot+=orderTMS.get(i).getTotal();
        txtTotal.setText(String.valueOf(tot));
        cmbItemCode.requestFocus();
    }

    public void cancleOnAction(ActionEvent actionEvent) {
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyONHand.setText("");
        txtQty.setText("");
        cmbItemCode.requestFocus();
    }

    public void onMouseClick(MouseEvent mouseEvent) {
    }

    public void cancleOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        orderTMS.clear();
        tbl.setItems(orderTMS);
        txtTotal.setText("");
        txtCustomerName.setText("");
//       cmbItemCode.setItems(null);
//       loadCustomer();
//       loadItem();
        cancleOnAction(actionEvent);

    }

    public void placeOderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        for(PlaceOrderTM tm:orderTMS){
            int count=DatabaseAccessItem.searchItem(tm.getCode()).getQtyOnHand();
            ItemDTO itemDTO =new ItemDTO(tm.getCode(),tm.getDescription(), tm.getUnitPrice(),count-tm.getQty());
            DatabaseAccessItem.updateItem(itemDTO);
            System.out.println("UUUUU");
        }

    }

    public void customerIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO=DatabaseAccessCode.searchCustomer(cmbXustomerID.getValue().toString());
        txtCustomerName.setText(customerDTO.getName());
    }

    public void itemCodeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO=DatabaseAccessItem.searchItem(cmbItemCode.getValue().toString());
        txtDescription.setText(itemDTO.getDescription());
        txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
        txtQtyONHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
        txtQty.setText("");
        txtQty.requestFocus();

    }

    public void qtyOnAction(ActionEvent actionEvent) {
        btnAdd.requestFocus();
    }
}
