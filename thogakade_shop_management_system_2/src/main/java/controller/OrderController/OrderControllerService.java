package controller.OrderController;

import javafx.collections.ObservableList;
import model.Order;

public interface OrderControllerService {

    void PlaceOrder(String orderid , String orderdate, String custid);
    void UpdateOrder(String orderid , String orderdate, String custid);
    void DeleteOrder(String orderid);
    void ViewOrders(ObservableList<Order> orderInfos);
}
