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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemManagementController implements Initializable {

    @FXML
    private JFXButton btnadditem;

    @FXML
    private JFXButton btndeleteitem;

    @FXML
    private JFXButton btnupdateitem;

    @FXML
    private JFXButton btnviewitem;

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
    private JFXTextArea qtyonhand;

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
    }

    public void btnupdateitemOnaction(ActionEvent event) {
    }

    public void btndeleteitemOnaction(ActionEvent event) {
    }

    public void btnviewitemOnaction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
}
