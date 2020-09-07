package lk.uok.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.uok.dao.DatabaseAccessCustomer;
import lk.uok.dao.DatabaseAccessItem;
import lk.uok.dto.CustomerDTO;
import lk.uok.dto.ItemDTO;
import lk.uok.view.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class ItemFormController {
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colOperation;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyONHand;
    public TableView tbl;
    public TextField searchBar;
    public Button btnUpdate;
    public Button btnNew;

    public void initialize() throws SQLException, ClassNotFoundException {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOperation.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadALlItems();
      }

    public void loadALlItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS= DatabaseAccessItem.getAllItem();
        ObservableList<ItemTM> observableList= FXCollections.observableArrayList();
        for(ItemDTO dto:itemDTOS){
            ItemTM itemTM=new ItemTM(dto.getCode(), dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
            observableList.add(itemTM);
        }
        tbl.setItems(observableList);

    }

    public void codeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO=DatabaseAccessItem.searchItem(txtCode.getText());
        txtDescription.setText(itemDTO.getDescription());
        txtQtyONHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS= DatabaseAccessItem.searchbar(searchBar.getText());
        tbl.getItems().removeAll();
        ObservableList<ItemTM> observableList= FXCollections.observableArrayList();
        for(ItemDTO dto:itemDTOS){
            ItemTM itemTM=new ItemTM(dto.getCode(), dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
            observableList.add(itemTM);
        }
        tbl.setItems(observableList);

    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO=new ItemDTO(txtCode.getText(),txtDescription.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyONHand.getText()));
        DatabaseAccessItem.updateItem(itemDTO);
    }

    public void newOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO=new ItemDTO(txtCode.getText(),txtDescription.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyONHand.getText()));
        DatabaseAccessItem.addItem(itemDTO);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        ObservableList<ItemTM> selectedRows = tbl.getSelectionModel().getSelectedItems();
        txtCode.setText(selectedRows.get(0).getCode());
        txtDescription.setText(selectedRows.get(0).getDescription());
        txtUnitPrice.setText(String.valueOf(selectedRows.get(0).getUnitPrice()));
        txtQtyONHand.setText(String.valueOf(selectedRows.get(0).getQtyOnHand()));
    }

    public void descriptionFieldTyping(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        checkUpdateCanPerform();
    }

    public void codeFieldTyping(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        checkUpdateCanPerform();
    }

    public void unitPriceFieldTyping(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        checkUpdateCanPerform();

    }

    public void gtyOnHandFieldTyping(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        checkUpdateCanPerform();

    }

    public void searchFieldTyping(KeyEvent keyEvent) {
        txtCode.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyONHand.setText("");
        btnUpdate.setDisable(true);
        btnNew.setDisable(true);
    }

    public void checkUpdateCanPerform() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = DatabaseAccessItem.getAllItem();
        for(ItemDTO dto:itemDTOS){
            if(txtCode.getText().equals(dto.getCode())){
                btnUpdate.setDisable(false);
                btnNew.setDisable(true);
                return;
            }
        }
        btnNew.setDisable(false);
        btnUpdate.setDisable(true);
    }

    public void unitPriceOnKeyReliease(KeyEvent keyEvent) {
        try{
            Double num = Double.parseDouble(txtUnitPrice.getText());
        } catch (NumberFormatException e) {
            Alert error=new Alert(Alert.AlertType.ERROR,"Not allowed for non numeric values");
            error.show();
            txtUnitPrice.setText(txtUnitPrice.getText().substring(0,txtUnitPrice.getText().length()-1));
            txtUnitPrice.requestFocus();
            return;
        }
    }

    public void qtyOnHandOnReleaseAction(KeyEvent keyEvent) {
        try{
            Double num = Double.parseDouble(txtQtyONHand.getText());
        } catch (NumberFormatException e) {
            Alert error=new Alert(Alert.AlertType.ERROR,"Not allowed for non numeric values");
            error.show();
            txtQtyONHand.setText(txtQtyONHand.getText().substring(0,txtQtyONHand.getText().length()-1));
            txtQtyONHand.requestFocus();
            return;
        }
    }
}
