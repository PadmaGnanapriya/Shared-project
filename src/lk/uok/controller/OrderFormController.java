package lk.uok.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.uok.dao.DatabaseAccessOrderForm;
import lk.uok.dao.DatabasseAcessOrderDetail;
import lk.uok.dao.DatabseAccessOrders;
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
    public TableColumn colDescription;
    public Button btnDelete;

    public void initialize() throws SQLException, ClassNotFoundException {
        colOID.setCellValueFactory(new PropertyValueFactory<>("oid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusID.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        loadData();
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        ObservableList<OrderTM> orderTMS=DatabaseAccessOrderForm.getAllDate();
        tbl.setItems(orderTMS);

    }

    public void onMouseClick(MouseEvent mouseEvent) {
        btnDelete.setDisable(false);
    }

    public void reloadOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnDelete.setDisable(true);
        loadData();
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        OrderTM tm= (OrderTM) tbl.getSelectionModel().getSelectedItem();
        DatabasseAcessOrderDetail.deleteOrderDetail(tm.getItemCode());
        btnDelete.setDisable(true);
        loadData();
    }
}
