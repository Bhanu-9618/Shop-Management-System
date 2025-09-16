package controller.OrderController;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController implements OrderControllerService{
    @Override
    public void PlaceOrder(String orderid, String orderdate, String custid) {

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

    @Override
    public void UpdateOrder(String orderid, String orderdate, String custid) {

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

    @Override
    public void DeleteOrder(String orderid) {

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

    @Override
    public void ViewOrders(ObservableList<Order> orderInfos) {

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
    }
}
