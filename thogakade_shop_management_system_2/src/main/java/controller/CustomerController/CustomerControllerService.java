package controller.CustomerController;

import javafx.collections.ObservableList;
import model.Customer;

import java.time.LocalDate;

public interface CustomerControllerService {

    void AddCustomer(String custid , String title, String name, LocalDate dob, String salary, String address, String city, String province, String postalcode);
    void UpdateCustomer(String custid ,String title, String name,LocalDate dob,String salary,String address, String city,String province, String postalcode);
    void DeleteCustomer(String custid);
    void ViewCustomers(ObservableList<Customer> customerInfos);
}
