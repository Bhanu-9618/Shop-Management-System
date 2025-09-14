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
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
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
    private DatePicker custDob;

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

        String custid = txtcustid.getText().trim();
        String title = combotitle.getValue();
        String name = txtcustname.getText().trim();
        LocalDate dob = custDob.getValue();
        String salary = txtcustsalary.getText().trim();
        String address = txtcustaddress.getText().trim();
        String city = txtcustcity.getText().trim();
        String province = comboprovince.getValue();
        String postalcode = txtcustpostalcode.getText().trim();

        if ((custid.isEmpty()) || (title==null) || (name.isEmpty()) || (dob==null) || (salary.isEmpty() ) || (address.isEmpty() ) || (city.isEmpty()) || (province==null) || (postalcode.isEmpty())){

            System.out.println("fill all details!");

        }else {

            Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection();
                String SQL = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, custid);
                preparedStatement.setObject(2, title);
                preparedStatement.setObject(3, name);
                preparedStatement.setObject(4, dob);
                preparedStatement.setObject(5, salary);
                preparedStatement.setObject(6, address);
                preparedStatement.setObject(7, city);
                preparedStatement.setObject(8, province);
                preparedStatement.setObject(9, postalcode);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        btnViewcustOnaction(event);

    }

    public void btnupdatecustOnaction(ActionEvent event) {

        String custid = txtcustid.getText().trim();
        String title = combotitle.getValue();
        String name = txtcustname.getText().trim();
        LocalDate dob = custDob.getValue();
        String salary = txtcustsalary.getText().trim();
        String address = txtcustaddress.getText().trim();
        String city = txtcustcity.getText().trim();
        String province = comboprovince.getValue();
        String postalcode = txtcustpostalcode.getText().trim();

        if ((custid.isEmpty()) || (title==null) || (name.isEmpty()) || (dob==null) || (salary.isEmpty() ) || (address.isEmpty() ) || (city.isEmpty()) || (province==null) || (postalcode.isEmpty())){

            System.out.println("fill all details!");

        }else {

            Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection();
                String SQL = "UPDATE customer SET CustTitle = ?, CustName = ?, DOB = ?, salary = ?, CustAddress = ?, City = ?, Province = ?, PostalCode = ? WHERE CustID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);


                preparedStatement.setObject(1, title);
                preparedStatement.setObject(2, name);
                preparedStatement.setObject(3, dob);
                preparedStatement.setObject(4, salary);
                preparedStatement.setObject(5, address);
                preparedStatement.setObject(6, city);
                preparedStatement.setObject(7, province);
                preparedStatement.setObject(8, postalcode);
                preparedStatement.setObject(9, custid);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        btnViewcustOnaction(event);
    }

    public void btndeletecustOnaction(ActionEvent event) {

        String custid = txtcustid.getText();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM customer WHERE CustID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, custid);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnViewcustOnaction(event);
    }

    public void btnViewcustOnaction(ActionEvent event) {


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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnViewcustOnaction(new ActionEvent());

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
