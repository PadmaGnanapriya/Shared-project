package lk.uok.view.tm;

import javafx.scene.control.Button;

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
