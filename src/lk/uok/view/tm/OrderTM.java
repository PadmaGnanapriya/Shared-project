package lk.uok.view.tm;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class OrderTM {
    private String oid;
    private String date;
    private String cusId;
    private String cusName;
    private String itemCode;
    private String description;
    private int qty;

    public OrderTM() {
    }

    public OrderTM(String oid, String date, String cusId, String cusName, String itemCode, String description, int qty, double unitPrice) {
        this.oid = oid;
        this.date = date;
        this.cusId = cusId;
        this.cusName = cusName;
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    private double unitPrice;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
