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
import model.Item;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ItemManagementController implements Initializable {

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
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                String SQL = "INSERT INTO item VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, itemcode);
                preparedStatement.setObject(2, description);
                preparedStatement.setObject(3, packsize);
                preparedStatement.setObject(4, unitprice);
                preparedStatement.setObject(5, qtyonhand);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        view();
    }

    public void btnupdateitemOnaction(ActionEvent event) {

        String itemcode = txtitemcode.getText().trim();
        String description = txtdescription.getText().trim();
        String packsize = txtpacksize.getText().trim();
        String unitprice = txtunitprice.getText().trim();
        String qtyonhand = txtqtyonhand.getText().trim();

        if ((itemcode.isEmpty()) || (description.isEmpty()) || (packsize.isEmpty()) || (unitprice.isEmpty()) || (qtyonhand.isEmpty() )){
            System.out.println("fill all details!");
        }else {

            Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection();
                String SQL = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setObject(1, description );
                preparedStatement.setObject(2, packsize);
                preparedStatement.setObject(3, unitprice);
                preparedStatement.setObject(4, qtyonhand);
                preparedStatement.setObject(5, itemcode);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        view();
    }

    public void btndeleteitemOnaction(ActionEvent event) {

        String itemcode = txtitemcode.getText();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM item WHERE ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, itemcode);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        view();
    }

    public void view() {

        ObservableList<Item> itemInfos = FXCollections.observableArrayList();

        try {
            String SQL = "Select * from item;";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Item item = new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
                itemInfos.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
