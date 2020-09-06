package lk.uok.view.tm;

import com.jfoenix.controls.JFXButton;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private double salary;
    private JFXButton btn;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;

//        btn.setStyle("-fx-background-color: #d35400");
    }

    public CustomerTM(String id, String name, String address, double salary, JFXButton btn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.btn = btn;
//        btn.setStyle("-fx-background-color: #d35400");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
