package controller.CustomerController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import model.Customer;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerManagementController implements Initializable {

    CustomerControllerService customerControllerService = new CustomerController();

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
            customerControllerService.AddCustomer( custid , title,  name, dob, salary, address, city, province, postalcode);
        }
        view();
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
        customerControllerService.UpdateCustomer(custid , title,  name, dob, salary, address, city, province, postalcode);
        }
        view();
    }

    public void btndeletecustOnaction(ActionEvent event) {

        String custid = txtcustid.getText();
        customerControllerService.DeleteCustomer(custid);
        view();
    }

    public void view() {

        ObservableList<Customer> customerInfos = FXCollections.observableArrayList();

        customerControllerService.ViewCustomers(customerInfos);

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

        view();

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
