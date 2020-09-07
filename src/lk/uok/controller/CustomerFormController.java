package lk.uok.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.uok.dao.DatabaseAccessCustomer;
import lk.uok.dto.CustomerDTO;
import lk.uok.view.tm.CustomerTM;

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
    public Button btnUpdate;
    public Button btnNew;


    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOperation.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllCustomers();

    }



    private void loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = DatabaseAccessCustomer.getAllCustomers();
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
        for (CustomerDTO dto : customerDTOS) {
            CustomerTM tm = new CustomerTM(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());
            customerTMS.add(tm);
        }
        tbl.setItems(customerTMS);
    }
    public void idOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO dto= DatabaseAccessCustomer.searchCustomer(txtId.getText());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtSalary.setText(String.valueOf(dto.getSalary()));
        System.out.println("Id on acrtion");

    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = DatabaseAccessCustomer.searchbar(txtSearch.getText());
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
        for (CustomerDTO dto : customerDTOS) {
            CustomerTM tm = new CustomerTM(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());
            customerTMS.add(tm);
        }
        tbl.setItems(customerTMS);
    }

    public void newOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO dto=new CustomerDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()));
        DatabaseAccessCustomer.addCustomer(dto);
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO dto=new CustomerDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()));
        DatabaseAccessCustomer.updateCustomer(dto);
    }

    public void tableClickAction(SortEvent<TableView> tableViewSortEvent) {
        System.out.println("POOIOO");
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        ObservableList<CustomerTM> selectedRows = tbl.getSelectionModel().getSelectedItems();
        txtId.setText(selectedRows.get(0).getId());
        txtName.setText(selectedRows.get(0).getName());
        txtAddress.setText(selectedRows.get(0).getAddress());
        txtSalary.setText(String.valueOf(selectedRows.get(0).getSalary()));
    }

    public void salaryFieldIsTyping(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        checkUpdateCanPerform();
    }

    private void checkUpdateCanPerform() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = DatabaseAccessCustomer.getAllCustomers();
        for(CustomerDTO dto:customerDTOS){
            if(txtId.getText().equals(dto.getId())){
                btnUpdate.setDisable(false);
                btnNew.setDisable(true);
                return;
            }
        }
        btnNew.setDisable(false);
        btnUpdate.setDisable(true);

    }

    public void idFieldTyping(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        checkUpdateCanPerform();
    }

    public void nameFieldTyping(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        checkUpdateCanPerform();
    }

    public void adressFieldTyping(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        checkUpdateCanPerform();
    }

    public void searchFieldTyping(KeyEvent keyEvent) {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
        btnNew.setDisable(true);
        btnUpdate.setDisable(true);
    }


//    CustomerTM customerTM= (CustomerTM) tbl.getSelectionModel().getSelectedItems();

}
