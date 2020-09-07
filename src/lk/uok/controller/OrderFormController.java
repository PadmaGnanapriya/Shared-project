package lk.uok.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.uok.dao.DatabaseAccessOrderForm;
import lk.uok.view.tm.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class OrderFormController {
    public TableView tbl;
    public TableColumn colOID;
    public TableColumn colDate;
    public TableColumn colCusName;
    public TableColumn colCusID;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colUnitPrice;

    public void initialize() throws SQLException, ClassNotFoundException {
        colOID.setCellValueFactory(new PropertyValueFactory<>("oid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusID.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        loadData();

    }

    private void loadData() throws SQLException, ClassNotFoundException {

        ObservableList<OrderTM> orderTMS=DatabaseAccessOrderForm.getAllDate();
        tbl.setItems(orderTMS);

    }

    public void onMouseClick(MouseEvent mouseEvent) {
    }
}
