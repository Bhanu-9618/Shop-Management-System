package controller.ItemController;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemControllerService{

    @Override
    public void AddItem(String itemcode , String description, String packsize, String unitprice, String qtyonhand){
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

    @Override
    public void UpdateItem(String itemcode , String description, String packsize, String unitprice, String qtyonhand){

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, description);
            preparedStatement.setObject(2, packsize);
            preparedStatement.setObject(3, unitprice);
            preparedStatement.setObject(4, qtyonhand);
            preparedStatement.setObject(5, itemcode);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void DeleteItem(String itemcode){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM item WHERE ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, itemcode);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ViewItems(ObservableList<Item> itemInfos){

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
    }
}
