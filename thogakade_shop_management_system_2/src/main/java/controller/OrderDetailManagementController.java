package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderDetailManagementController {

    @FXML
    private JFXButton btnadd;
    
    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private TableColumn<?, ?> colcustid;

    @FXML
    private TableColumn<?, ?> colcustid1;

    @FXML
    private TableColumn<?, ?> colorderid;

    @FXML
    private TableColumn<?, ?> colorderid1;

    @FXML
    private TableView<?> tblorderdetailmanagement;

    @FXML
    private JFXTextArea txtdiscount;

    @FXML
    private JFXTextArea txtitemcode;

    @FXML
    private JFXTextArea txtorderid;

    @FXML
    private JFXTextArea txtorderqty;


    public void btnupdateOnaction(ActionEvent event) {
    }

    public void btndeleteOnaction(ActionEvent event) {
    }

    public void btnaddOnaction(ActionEvent event) {
    }
}
