package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderDetail {

    private String orderid;
    private String itemcode;
    private int orderqyt;
    private int discount;
}
