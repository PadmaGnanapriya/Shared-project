package lk.uok.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.uok.dao.DatabaseAccessCode;
import lk.uok.dao.DatabaseAccessItem;
import lk.uok.dto.ItemDTO;
import lk.uok.view.tm.CustomerTM;
import lk.uok.view.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

import static sun.net.www.MimeTable.loadTable;

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
}
