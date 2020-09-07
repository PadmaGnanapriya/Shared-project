package lk.uok.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.uok.dao.DatabaseAccessCode;
import lk.uok.dao.DatabaseAccessItem;
import lk.uok.dto.CustomerDTO;
import lk.uok.dto.ItemDTO;
import lk.uok.view.tm.CustomerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public Label customerCount;
    public Label itemCount;

    public void initialize() throws InterruptedException, SQLException, ClassNotFoundException {
        genarateOrderDate();
        getCustomerCount();
        getItemCount();
        genarateTime();
//        final String IDLE_BUTTON_STYLE = "-fx-background-color:  darkcyan; -fx-padding: 10px";
//        final String HOVERED_BUTTON_STYLE = "-fx-background-color:  cyan; -fx-padding: 10px";
//        navBtn.setOnMouseEntered(e -> navBtn.setStyle(HOVERED_BUTTON_STYLE));
//        navBtn.setOnMouseExited(e -> navBtn.setStyle(IDLE_BUTTON_STYLE));
    }

    private void getItemCount() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS= DatabaseAccessItem.getAllItem();
        int count=itemDTOS.size();
        itemCount.setText(String.valueOf(count));
    }

    private void getCustomerCount() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS= DatabaseAccessCode.getAllCustomers();
        int count=customerDTOS.size();
        customerCount.setText(String.valueOf(count));
    }


    private void genarateTime() throws InterruptedException {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            txtClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();


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
