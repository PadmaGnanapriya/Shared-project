package lk.uok.view.tm;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import lk.uok.dao.DatabaseAccessItem;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class ItemTM {
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
    private Button btn=new Button("Delete");





    public ItemTM(String code, String description, double unitPrice, int qtyOnHand) {
        this.setCode(code);
        this.setDescription(description);
        this.setUnitPrice(unitPrice);
        this.setQtyOnHand(qtyOnHand);
        btn.setStyle("-fx-background-color: #d35400");

        btn.setOnAction(e->{
            ButtonType ok= new ButtonType("OK",
                    ButtonBar.ButtonData.OK_DONE);
            ButtonType no= new ButtonType("NO",
                    ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert= new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are You Sure whether You Want to Delete This Customer?",
                    ok,no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no)==ok){
                try {
                    boolean isDeleted = DatabaseAccessItem.deleteItem(this.getCode());

                    if (isDeleted){
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted !",
                                ButtonType.OK).show();
                    }else{
                        new Alert(Alert.AlertType.WARNING,"Try Again !",
                                ButtonType.OK).show();
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }else{
            }

        });
    }

    public ItemTM() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
