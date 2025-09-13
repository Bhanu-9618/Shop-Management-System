package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class CustomerManagementController {

    @FXML
    private JFXButton btnViewcust;

    @FXML
    private JFXButton btnaddcust;

    @FXML
    private JFXButton btndeletecust;

    @FXML
    private JFXButton btnupdatecust;

    @FXML
    private TableColumn<?, ?> colcustAddress;

    @FXML
    private TableColumn<?, ?> colcustCity;

    @FXML
    private TableColumn<?, ?> colcustDOB;

    @FXML
    private TableColumn<?, ?> colcustID;

    @FXML
    private TableColumn<?, ?> colcustName;

    @FXML
    private TableColumn<?, ?> colcustPostalcode;

    @FXML
    private TableColumn<?, ?> colcustProvince;

    @FXML
    private TableColumn<?, ?> colcustSalary;

    @FXML
    private TableColumn<?, ?> colcustTitle;

    @FXML
    private JFXComboBox<?> comboprovince;

    @FXML
    private JFXComboBox<?> combotitle;

    @FXML
    private JFXTextArea txtcustaddress;

    @FXML
    private JFXTextArea txtcustcity;

    @FXML
    private JFXTextArea txtcustdob;

    @FXML
    private JFXTextArea txtcustid;

    @FXML
    private JFXTextArea txtcustname;

    @FXML
    private JFXTextArea txtcustpostalcode;

    @FXML
    private JFXTextArea txtcustsalary;

    public void btnaddcustOnaction(ActionEvent event) {
    }

    public void btnupdatecustOnaction(ActionEvent event) {
    }

    public void btndeletecustOnaction(ActionEvent event) {
    }

    public void btnViewcustOnaction(ActionEvent event) {
    }
}
