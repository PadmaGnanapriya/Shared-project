package lk.uok.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.uok.dao.DatabaseAccessCustomer;
import lk.uok.dao.DatabaseAccessItem;
import lk.uok.dao.DatabasseAcessOrderDetail;
import lk.uok.dao.DatabseAccessOrders;
import lk.uok.dto.CustomerDTO;
import lk.uok.dto.ItemDTO;
import lk.uok.dto.OrderDetailDTO;
import lk.uok.dto.OrdersDTO;
import lk.uok.view.tm.PlaceOrderTM;

import javax.swing.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    public Button btnCancle;
    public Button btnPlaceOrder;
    public Button btnDelete;

    ObservableList<PlaceOrderTM> orderTMS=FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadCustomer();
        loadItem();
        loadOrderId();
    }

    private void loadOrderId() throws SQLException, ClassNotFoundException {
        String lastId=DatabseAccessOrders.getLastOrderId();
        try {

            String order_id = lastId.replaceAll("[\\[\\](){}]","");
            int lastDigits = Integer.parseInt(order_id.split("[A-Z]")[1])+1;
            txtOderID.setText( "D"+String.format("%03d", lastDigits).substring(0, 3));
        }catch (Exception ex){
            System.out.println("Error in Oderdetail \n"+ex);
            txtOderID.setText("D001");
        }

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
        ArrayList<CustomerDTO> customerDTOS = DatabaseAccessCustomer.getAllCustomers();
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        for (CustomerDTO dto : customerDTOS) {
            customerIds.add(dto.getId());
        }
        cmbXustomerID.setItems(customerIds);
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtCustomerName.getText().equals("")){
            Alert alert= new Alert(Alert.AlertType.ERROR,"Please Select Customer Id");
            alert.show();
            cmbXustomerID.requestFocus();
            return;
        }
        if(txtQty.getText().equals("")){
            Alert alert= new Alert(Alert.AlertType.ERROR,"Please Select amount of item");
            alert.show();
            txtQty.requestFocus();
            return;
        }
        if(Integer.parseInt(txtQty.getText())>Integer.parseInt(txtQtyONHand.getText())){
            Alert alert= new Alert(Alert.AlertType.ERROR,"qty should be less or equal for qtyOnHand");
            alert.show();
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
        for(int i=0;i<orderTMS.size();i++) tot+=orderTMS.get(i).getTotal();
        txtTotal.setText(String.valueOf(tot));
        btnCancle.setDisable(false);
        btnPlaceOrder.setDisable(false);
        btnDelete.setDisable(true);
        cmbItemCode.requestFocus();
    }



    public void cancleOnAction(ActionEvent actionEvent) {
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyONHand.setText("");
        txtQty.setText("");
        btnPlaceOrder.setDisable(true);
        btnCancle.setDisable(true);
        btnAdd.setDisable(true);
        cmbItemCode.requestFocus();
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        btnDelete.setDisable(false);
    }

    public void cancleOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        orderTMS.clear();
        tbl.setItems(orderTMS);
        txtTotal.setText("");
        txtCustomerName.setText("");
        cancleOnAction(actionEvent);

    }

    public void placeOderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InterruptedException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        OrdersDTO ordersDTO =new OrdersDTO(txtOderID.getText(),dateFormat.format(date),cmbXustomerID.getValue().toString());
        DatabseAccessOrders.addOrder(ordersDTO);

        for(PlaceOrderTM tm:orderTMS){
            int count=DatabaseAccessItem.searchItem(tm.getCode()).getQtyOnHand();
            ItemDTO itemDTO =new ItemDTO(tm.getCode(),tm.getDescription(), tm.getUnitPrice(),count-tm.getQty());
            DatabaseAccessItem.updateItem(itemDTO);
            OrderDetailDTO orderDetailDTO=new OrderDetailDTO(txtOderID.getText(),tm.getCode(), tm.getQty(), tm.getUnitPrice() );
            DatabasseAcessOrderDetail.addOrderDetail(orderDetailDTO);
        }
        Thread.sleep(300);
        cancleOrderOnAction(actionEvent);
        loadOrderId();
        cmbXustomerID.requestFocus();

    }

    public void customerIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO= DatabaseAccessCustomer.searchCustomer(cmbXustomerID.getValue().toString());
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
        if(Integer.parseInt(txtQty.getText())>Integer.parseInt(txtQtyONHand.getText())){
            Alert alert= new Alert(Alert.AlertType.ERROR,"qty should be less or equal for qtyOnHand");
            alert.show();
            txtQty.requestFocus();
            return;
        }
        btnAdd.requestFocus();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PlaceOrderTM tm= (PlaceOrderTM) tbl.getSelectionModel().getSelectedItem();
        orderTMS.remove(tm);
        tbl.getSelectionModel().clearSelection();
        btnDelete.setDisable(true);
        double tot=0;
        for(int i=0;i<orderTMS.size();i++) tot+=orderTMS.get(i).getTotal();
        txtTotal.setText(String.valueOf(tot));

    }

    public void qtyOnTyped(KeyEvent keyEvent) {
        btnAdd.setDisable(false);
    }
}
