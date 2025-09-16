package model;
import lombok.*;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;
    private String title;
    private String name;
    private Date dob;
    private double salary;
    private String address;
    private String city;
    private String province;
    private String postalcode;
}
