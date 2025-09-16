package controller.OrderDetailController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetail;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailManagementController implements Initializable {

    OrderDetailControllerService orderDetailControllerService = new OrderDetailController();

    @FXML
    private JFXButton btnadd;

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private TableColumn<?, ?> coldiscount;

    @FXML
    private TableColumn<?, ?> colitemcode;

    @FXML
    private TableColumn<?, ?> colorderid;

    @FXML
    private TableColumn<?, ?> colorderqty;

    @FXML
    private TableView<OrderDetail> tblorderdetailmanagement;

    @FXML
    private JFXTextArea txtdiscount;

    @FXML
    private JFXTextArea txtitemcode;

    @FXML
    private JFXTextArea txtorderid;

    @FXML
    private JFXTextArea txtorderqty;

    public void btnupdateOnaction(ActionEvent event) {

        String orderid = txtorderid.getText().trim();
        String itemcode = txtitemcode.getText().trim();
        String  orderqty = txtorderqty.getText().trim();
        String  discount = txtdiscount.getText().trim();

        if (orderid.isEmpty() | itemcode.isEmpty() | orderqty.isEmpty() | discount.isEmpty()) {
            System.out.println("Fill all details!");
        } else {
           orderDetailControllerService.UpdateOrderdetail(orderid,itemcode,orderqty,discount);
        }
        view();
    }

    public void btndeleteOnaction(ActionEvent event) {

        String orderid = txtorderid.getText().trim();
        String itemcode = txtitemcode.getText().trim();

        if (orderid.isEmpty() | itemcode.isEmpty()) {
            System.out.println("Input OrderID and ItemCode!");
        } else {
            orderDetailControllerService.DeleteOrderdetail(orderid,itemcode);
        }
        view();
    }

    public void btnaddOnaction(ActionEvent event) {

        String orderid = txtorderid.getText().trim();
        String itemcode = txtitemcode.getText().trim();
        String  orderqty = txtorderqty.getText().trim();
        String  discount = txtdiscount.getText().trim();

        if (orderid.isEmpty() | itemcode.isEmpty() | orderqty.isEmpty() | discount.isEmpty()) {
            System.out.println("Fill all details!");
        } else {
            orderDetailControllerService.AddOrderdetail(orderid,itemcode,orderqty,discount);
        }
        view();
    }

    public void view(){

        ObservableList<OrderDetail> orderdetailInfos = FXCollections.observableArrayList();

        orderDetailControllerService.ViewOrderdetail(orderdetailInfos);

        colorderid.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colitemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colorderqty.setCellValueFactory(new PropertyValueFactory<>("orderqty"));
        coldiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        tblorderdetailmanagement.setItems(orderdetailInfos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        view();
    }
}
