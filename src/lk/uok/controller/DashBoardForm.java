package lk.uok.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
