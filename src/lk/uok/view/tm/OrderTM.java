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
    private int qty;
    private double unitPrice;

    public OrderTM() {
    }

    public OrderTM(String oid, String date, String cusId, String cusName, String itemCode, int qty, double unitPrice) {
        this.oid = oid;
        this.date = date;
        this.cusId = cusId;
        this.cusName = cusName;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

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
