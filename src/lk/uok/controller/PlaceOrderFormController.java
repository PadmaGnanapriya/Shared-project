package lk.uok.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.uok.dao.DatabaseAccessCode;
import lk.uok.dao.DatabaseAccessItem;
import lk.uok.dto.CustomerDTO;
import lk.uok.dto.ItemDTO;
import lk.uok.view.tm.CustomerTM;
import lk.uok.view.tm.ItemTM;

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

    public void initialize() throws SQLException, ClassNotFoundException {
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
        ItemDTO itemDTO=DatabaseAccessItem.searchItem(cmbItemCode.getValue().toString());
//        ObservableList<>
//        tbl.setItems();
    }

    public void cancleOnAction(ActionEvent actionEvent) {
    }

    public void onMouseClick(MouseEvent mouseEvent) {
    }

    public void cancleOrderOnAction(ActionEvent actionEvent) {
    }

    public void placeOderOnAction(ActionEvent actionEvent) {
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

    }
}
