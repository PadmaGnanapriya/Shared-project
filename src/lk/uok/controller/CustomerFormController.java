package lk.uok.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.uok.dao.DatabaseAccessCode;
import lk.uok.dto.CustomerDTO;
import lk.uok.view.tm.CustomerTM;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

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
        loadAllData();
    }

    private void loadAllData() throws SQLException, ClassNotFoundException{
        ArrayList<CustomerDTO> customerDTOS=DatabaseAccessCode.getAllCustomers();
//        ObservableList<CustomerTM> customerTMS= FXCollections.observableArrayList();
//        for(CustomerDTO dto:customerDTOS){
//            JFXButton btn=new JFXButton("Delete");
//            CustomerTM tm=new CustomerTM(dto.getId(), dto.getName(),dto.getAddress(), dto.getSalary(), btn);
//            customerTMS.add(tm);
//        }
    }

    public void idOnAction(ActionEvent actionEvent)  {

    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void newOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO dto=new CustomerDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()));
        DatabaseAccessCode.addCustomer(dto);
    }
//
//    public void successfullyRan(){
////        txtId.setText("");
////        txtName.setText("");
////        txtAddress.setText("");
////        txtSalary.setText("");
////        txtSearch.setText("");
//
//    }
//
//    public void errorOnRun() {
//    }
}
