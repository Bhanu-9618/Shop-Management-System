package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import db.DBConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderManagementController implements Initializable {

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
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                String SQL = "INSERT INTO orders VALUES(?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, orderid);
                preparedStatement.setObject(2, orderdate);
                preparedStatement.setObject(3, custid);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                String SQL = "UPDATE orders SET OrderDate = ?, CustID = ? WHERE OrderID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, orderdate);
                preparedStatement.setObject(2, custid);
                preparedStatement.setObject(3, orderid);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        view();
    }

    public void btndeleteOnaction(ActionEvent event) {

        String orderid = txtorderid.getText().trim();

        if (orderid.isEmpty()) {
            System.out.println("Input OrderID!");
        } else {
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                String SQL = "DELETE FROM orders WHERE OrderID = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, orderid);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        view();
    }

    public void view() {

        ObservableList<Order> orderInfos = FXCollections.observableArrayList();

        try {
            String SQL = "Select * from orders;";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Order order = new Order(
                        resultSet.getString("OrderID"),
                        resultSet.getDate("OrderDate"),
                        resultSet.getString("CustID")
                );
                orderInfos.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
