package controller.OrderController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderManagementController implements Initializable {

    OrderControllerService orderControllerService = new OrderController();

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
    private TableView<Order> tblordermanagement;

    @FXML
    private JFXTextArea txtcustid;

    @FXML
    private JFXTextArea txtorderid;

    public void btnplaceorderOnaction(ActionEvent event) {

        String orderid = txtorderid.getText().trim();
        String orderdate = String.valueOf(dateorderdate.getValue());
        String custid = txtcustid.getText().trim();

        if (orderid.isEmpty() | orderdate.isEmpty() | custid.isEmpty()) {
            System.out.println("Fill all details!");
        } else {
           orderControllerService.PlaceOrder(orderid,orderdate,custid);
        }
        view();
    }

    public void btnupdateOnaction(ActionEvent event) {

        String orderid = txtorderid.getText().trim();
        String orderdate = String.valueOf(dateorderdate.getValue());
        String custid = txtcustid.getText().trim();

        if (orderid.isEmpty() | orderdate.isEmpty() | custid.isEmpty()) {
            System.out.println("Fill all details!");
        } else {
            orderControllerService.UpdateOrder(orderid,orderdate,custid);
        }
        view();
    }

    public void btndeleteOnaction(ActionEvent event) {

        String orderid = txtorderid.getText().trim();

        if (orderid.isEmpty()) {
            System.out.println("Input OrderID!");
        } else {
           orderControllerService.DeleteOrder(orderid);
        }
        view();
    }

    public void view() {

        ObservableList<Order> orderInfos = FXCollections.observableArrayList();

        orderControllerService.ViewOrders(orderInfos);

        colorderid.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colorderdate.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        colcustid.setCellValueFactory(new PropertyValueFactory<>("custid"));

        tblordermanagement.setItems(orderInfos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        view();
    }
}
