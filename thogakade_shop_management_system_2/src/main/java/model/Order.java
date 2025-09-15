package model;

import lombok.*;

import java.util.Date;

@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Order {

    private String orderid;
    private Date orderdate;
    private String custid;

}
