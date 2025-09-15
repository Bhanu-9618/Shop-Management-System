package controller.ItemController;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemControllerService {

    void AddItem(String itemcode , String description, String packsize, String unitprice, String qtyonhand);
    void UpdateItem(String itemcode , String description, String packsize, String unitprice, String qtyonhand);
    void DeleteItem(String itemcode);
    void ViewItems(ObservableList<Item> itemInfos);
}
