package zd.model.user;

import lombok.Data;
import zd.model.Order;

import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:06 PM
 */
@Data
public class User {
    private Role role = Role.CUSTOMER;
    private String username;
    private String password;
    private UserInfo userInfo;
    private ShippingAddress shippingAddressList;
    private List<Order> orders;
}
