package lk.uok.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DashBoardForm {
    public AnchorPane anchorPlaneArea;
    public Label txtOrderDate;
    public Label txtClock;
    public Button navBtn;

    public void initialize() throws InterruptedException {
        genarateOrderDate();
        genarateTime();
//        final String IDLE_BUTTON_STYLE = "-fx-background-color:  darkcyan; -fx-padding: 10px";
//        final String HOVERED_BUTTON_STYLE = "-fx-background-color:  cyan; -fx-padding: 10px";
//        navBtn.setOnMouseEntered(e -> navBtn.setStyle(HOVERED_BUTTON_STYLE));
//        navBtn.setOnMouseExited(e -> navBtn.setStyle(IDLE_BUTTON_STYLE));
    }


    private void genarateTime() throws InterruptedException {
        Calendar now = Calendar.getInstance();
        int h = now.get(Calendar.HOUR_OF_DAY);
        int m = now.get(Calendar.MINUTE);
        txtClock.setText(" " + h + " : " + m );
    }

    private void loadUi(String location) throws IOException {
        anchorPlaneArea.getChildren().clear();
        anchorPlaneArea.getChildren().add(FXMLLoader.load(this.getClass()
                .getResource("/lk/uok/view/"+location+".fxml")));
    }

    private void genarateOrderDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        txtOrderDate.setText(dateFormat.format(date));
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
