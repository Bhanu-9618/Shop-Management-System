package controller.ItemController;

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
import model.Item;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementController implements Initializable {

    ItemControllerService itemControllerService = new ItemController();
    @FXML
    private JFXButton btnadditem;

    @FXML
    private JFXButton btndeleteitem;

    @FXML
    private JFXButton btnupdateitem;

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
    private JFXTextArea txtqtyonhand;

    @FXML
    private TableView<Item> tblitemmanagement;

    @FXML
    private JFXTextArea txtdescription;

    @FXML
    private JFXTextArea txtitemcode;

    @FXML
    private JFXTextArea txtpacksize;

    @FXML
    private JFXTextArea txtunitprice;

    public void btnadditemOnaction(ActionEvent event) {

        String itemcode = txtitemcode.getText().trim();
        String description = txtdescription.getText().trim();
        String packsize = txtpacksize.getText().trim();
        String unitprice = txtunitprice.getText().trim();
        String qtyonhand = txtqtyonhand.getText().trim();

        if (itemcode.isEmpty() | description.isEmpty() | packsize.isEmpty() | unitprice.isEmpty() | qtyonhand.isEmpty()) {
            System.out.println("Fill all details!");
        } else {
           itemControllerService.AddItem(itemcode,description,packsize,unitprice,qtyonhand);
        }
        view();
    }

    public void btnupdateitemOnaction(ActionEvent event) {

        String itemcode = txtitemcode.getText().trim();
        String description = txtdescription.getText().trim();
        String packsize = txtpacksize.getText().trim();
        String unitprice = txtunitprice.getText().trim();
        String qtyonhand = txtqtyonhand.getText().trim();

        if ((itemcode.isEmpty()) || (description.isEmpty()) || (packsize.isEmpty()) || (unitprice.isEmpty()) || (qtyonhand.isEmpty())) {
            System.out.println("fill all details!");
        } else {
            itemControllerService.UpdateItem(itemcode,description,packsize,unitprice,qtyonhand);
        }
        view();
    }

    public void btndeleteitemOnaction(ActionEvent event) {

        String itemcode = txtitemcode.getText();
        itemControllerService.DeleteItem(itemcode);
        view();
    }

    public void view() {

        ObservableList<Item> itemInfos = FXCollections.observableArrayList();

        itemControllerService.ViewItems(itemInfos);

        colcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colpacksize.setCellValueFactory(new PropertyValueFactory<>("packsize"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colqtyonhand.setCellValueFactory(new PropertyValueFactory<>("qtyonhand"));

        tblitemmanagement.setItems(itemInfos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        view();
    }
}
