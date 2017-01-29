package zd.model.user;

import lombok.Data;

@Data
public class ShippingAddress {
    private String shippingName;
    private String country;
    private String city;
    private String street;
    private String telephoneNumber;
}
