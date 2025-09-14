package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Item {

    private String itemcode;
    private String description;
    private String packsize;
    private double unitprice;
    private int qtyonhand;
}
