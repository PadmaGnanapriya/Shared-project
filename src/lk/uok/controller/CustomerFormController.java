package lk.uok.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.uok.dao.DatabaseAccessCode;
import lk.uok.dto.CustomerDTO;
import lk.uok.view.tm.CustomerTM;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class CustomerFormController extends Component {
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOperation;
    public TableView tbl;
    public TextField txtSearch;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;



    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOperation.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllCustomers();
    }

    private void loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = DatabaseAccessCode.getAllCustomers();
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
        for (CustomerDTO dto : customerDTOS) {
            JFXButton btn = new JFXButton("Delete");

            CustomerTM tm = new CustomerTM(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary(), btn);
            customerTMS.add(tm);

            btn.setOnAction(e -> {
                ButtonType ok = new ButtonType("OK",
                        ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO",
                        ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(
                        Alert.AlertType.CONFIRMATION,
                        "Are You Sure whether You Want to Delete This Customer?",
                        ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    try {
                        boolean isDeleted = DatabaseAccessCode.deleteCustomer(tm.getId());
                        if (isDeleted) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted !",
                                    ButtonType.OK).show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again !",
                                    ButtonType.OK).show();
                        }
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                }
            });
        }
        tbl.setItems(customerTMS);
    }
    public void idOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO dto=DatabaseAccessCode.searchCustomer(txtId.getText());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtSalary.setText(String.valueOf(dto.getSalary()));
        System.out.println("Id on acrtion");

    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void newOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO dto=new CustomerDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()));
        DatabaseAccessCode.addCustomer(dto);
    }

}
