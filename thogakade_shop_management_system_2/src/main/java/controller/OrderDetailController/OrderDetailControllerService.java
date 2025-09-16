package controller.OrderDetailController;

import javafx.collections.ObservableList;
import model.OrderDetail;

public interface OrderDetailControllerService {

    void AddOrderdetail(String orderid , String itemcode, String orderqty, String discount);
    void UpdateOrderdetail(String orderid , String itemcode, String orderqty, String discount);
    void DeleteOrderdetail(String orderid , String itemcode);
    void ViewOrderdetail(ObservableList<OrderDetail> orderdetailInfos);
}
