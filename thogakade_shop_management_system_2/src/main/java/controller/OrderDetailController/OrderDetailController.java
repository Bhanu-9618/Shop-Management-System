package controller.OrderDetailController;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailController implements OrderDetailControllerService{

    @Override
    public void AddOrderdetail(String orderid, String itemcode, String orderqty, String discount) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO orderdetail VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, orderid);
            preparedStatement.setObject(2, itemcode);
            preparedStatement.setObject(3, orderqty);
            preparedStatement.setObject(4, discount);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdateOrderdetail(String orderid, String itemcode, String orderqty, String discount) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE orderdetail SET  OrderQty = ?, Discount = ? WHERE OrderID = ? AND ItemCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, orderqty);
            preparedStatement.setObject(2, discount);
            preparedStatement.setObject(3, orderid);
            preparedStatement.setObject(4, itemcode);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DeleteOrderdetail(String orderid, String itemcode) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM orderdetail WHERE OrderID = ? AND ItemCode = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, orderid);
            preparedStatement.setObject(2, itemcode);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ViewOrderdetail(ObservableList<OrderDetail> orderdetailInfos) {

        try {
            String SQL = "Select * from orderdetail;";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                OrderDetail orderDetail = new OrderDetail(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("OrderQty"),
                        resultSet.getInt("Discount")
                );
                orderdetailInfos.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
