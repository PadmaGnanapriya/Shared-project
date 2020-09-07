package lk.uok.dto;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class OrdersDTO {
    private String id;
    private String date;
    private String customerId;

    public OrdersDTO(String id, String date, String customerId) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
    }

    public OrdersDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
