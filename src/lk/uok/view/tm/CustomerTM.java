package lk.uok.view.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import lk.uok.dao.DatabaseAccessCode;
import lk.uok.dao.DatabaseAccessItem;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private double salary;
    private Button btn=new Button("Delete");


    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
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
                    boolean isDeleted = DatabaseAccessCode.deleteCustomer(this.getId());

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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
