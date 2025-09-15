package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderManagementController {

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnplaceorder;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private TableColumn<?, ?> colcustid;

    @FXML
    private TableColumn<?, ?> colorderdate;

    @FXML
    private TableColumn<?, ?> colorderid;

    @FXML
    private DatePicker dateorderdate;

    @FXML
    private TableView<?> tblordermanagement;

    @FXML
    private JFXTextArea txtcustid;

    @FXML
    private JFXTextArea txtorderid;


    public void btnplaceorderOnaction(ActionEvent event) {
    }

    public void btnupdateOnaction(ActionEvent event) {
    }

    public void btndeleteOnaction(ActionEvent event) {
    }
}
