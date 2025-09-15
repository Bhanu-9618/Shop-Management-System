package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import db.DBConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderDetailManagementController implements Initializable {

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
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                String SQL = "UPDATE orderdetail SET  OrderQty = ?, Discount = ? WHERE OrderID = ? AND ItemCode = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, orderqty);
                preparedStatement.setObject(2, discount);
                preparedStatement.setObject(3, orderid);
                preparedStatement.setObject(4, itemcode);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        view();
    }

    public void btndeleteOnaction(ActionEvent event) {

        String orderid = txtorderid.getText().trim();
        String itemcode = txtitemcode.getText().trim();

        if (orderid.isEmpty() | itemcode.isEmpty()) {
            System.out.println("Input OrderID and ItemCode!");
        } else {
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                String SQL = "DELETE FROM orderdetail WHERE OrderID = ? AND ItemCode = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, orderid);
                preparedStatement.setObject(2, itemcode);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                String SQL = "INSERT INTO orderdetail VALUES(?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, orderid);
                preparedStatement.setObject(2, itemcode);
                preparedStatement.setObject(3, orderqty);
                preparedStatement.setObject(4, discount);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        view();
    }

    public void view(){

        ObservableList<OrderDetail> orderdetailInfos = FXCollections.observableArrayList();

        try {
            String SQL = "Select * from orderdetail;";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                OrderDetail orderDetail = new OrderDetail(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("OrderQty"),
                        resultSet.getInt("Discount")
                );
                orderdetailInfos.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
