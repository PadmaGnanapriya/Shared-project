package lk.uok.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DashBoardForm {
    public AnchorPane anchorPlaneArea;

    private void loadUi(String location) throws IOException {
        anchorPlaneArea.getChildren().clear();
        anchorPlaneArea.getChildren().add(FXMLLoader.load(this.getClass()
                .getResource("/lk/uok/view/"+location+".fxml")));
    }

    private void genarateOrderDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        txtOrderDate.setText(dateFormat.format(date));
    }


    public void onCustomerPerform(ActionEvent actionEvent) throws IOException {
        loadUi("CustomerForm");
    }

    public void onItemPerform(ActionEvent actionEvent) throws IOException {
        loadUi("ItemForm");
    }

    public void onOrderPerform(ActionEvent actionEvent) throws IOException {
        loadUi("OrderForm");
    }

    public void onPlaceOrderPerform(ActionEvent actionEvent) throws IOException {
        loadUi("PlaceOrderForm");
    }

    public void onReportPerform(ActionEvent actionEvent) {
    }

    public void onBackupPerform(ActionEvent actionEvent) {
    }
}
