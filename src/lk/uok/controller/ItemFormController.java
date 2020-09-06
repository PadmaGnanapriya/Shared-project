package lk.uok.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import lk.uok.dao.DatabaseAccessCode;
import lk.uok.dao.DatabaseAccessItem;
import lk.uok.dto.ItemDTO;

import java.sql.SQLException;

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

    public void codeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO=DatabaseAccessItem.searchItem(txtCode.getText());
        txtDescription.setText(itemDTO.getDescription());
        txtQtyONHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO=new ItemDTO(txtCode.getText(),txtDescription.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyONHand.getText()));
        DatabaseAccessItem.updateItem(itemDTO);
    }

    public void newOnAction(ActionEvent actionEvent) {
    }
}
