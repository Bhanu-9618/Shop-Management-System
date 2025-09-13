package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import model.Customer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerManagementController implements Initializable {

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
    private JFXComboBox<String> comboprovince;

    @FXML
    private JFXComboBox<String> combotitle;

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

    @FXML
    private TableView<Customer> tblcustmanagement;

    public void btnaddcustOnaction(ActionEvent event) {
    }

    public void btnupdatecustOnaction(ActionEvent event) {
    }

    public void btndeletecustOnaction(ActionEvent event) {
    }

    public void btnViewcustOnaction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Customer> customerInfos = FXCollections.observableArrayList();

        try {
            String SQL = "Select * from customer;";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement  preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Customer customer = new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );

                customerInfos.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        colcustID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colcustTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colcustName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colcustDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colcustSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colcustAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colcustCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colcustProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colcustPostalcode.setCellValueFactory(new PropertyValueFactory<>("postalcode"));

        tblcustmanagement.setItems(customerInfos);

        ObservableList<String> titles = FXCollections.observableArrayList(
                "Mr",
                "Mrs",
                "Ms"
        );
        combotitle.setItems(titles);

        ObservableList<String> provinces = FXCollections.observableArrayList(
                "Northern",
                "Western",
                "Central",
                "Southern",
                "Eastern",
                "North western",
                "Sabaragamuwa",
                "Uwa",
                "North Central"
        );
        comboprovince.setItems(provinces);
    }
}
