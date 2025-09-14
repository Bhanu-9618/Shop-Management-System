package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ItemManagementController {

    @FXML
    private JFXButton btnadditem;

    @FXML
    private JFXButton btndeleteitem;

    @FXML
    private JFXButton btnupdateitem;

    @FXML
    private JFXButton btnviewitem;

    @FXML
    private TableColumn<?, ?> colcode;

    @FXML
    private TableColumn<?, ?> coldescription;

    @FXML
    private TableColumn<?, ?> colpacksize;

    @FXML
    private TableColumn<?, ?> colqtyonhand;

    @FXML
    private TableColumn<?, ?> colunitprice;

    @FXML
    private JFXTextArea qtyonhand;

    @FXML
    private TableView<?> tblitemmanagement;

    @FXML
    private JFXTextArea txtdescription;

    @FXML
    private JFXTextArea txtitemcode;

    @FXML
    private JFXTextArea txtpacksize;

    @FXML
    private JFXTextArea txtunitprice;

    public void btnadditemOnaction(ActionEvent event) {
    }

    public void btnupdateitemOnaction(ActionEvent event) {
    }

    public void btndeleteitemOnaction(ActionEvent event) {
    }

    public void btnviewitemOnaction(ActionEvent event) {
    }
}
