package zd.model.user;

import lombok.Data;
import zd.model.Model;

@Data
public class ShippingAddress extends Model {
    private String shippingName;
    private String country;
    private String city;
    private String street;
    private String telephoneNumber;
}
