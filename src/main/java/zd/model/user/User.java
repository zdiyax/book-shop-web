package zd.model.user;

import lombok.Data;
import zd.model.Model;
import zd.model.Order;

import java.util.List;

@Data
public class User extends Model {
    private Role role = Role.CUSTOMER;
    private String username;
    private String password;
    private UserInfo userInfo;
    private ShippingAddress shippingAddressList;
    private List<Order> orders;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }
}
