package zd.model.user;

import zd.model.order.Order;

import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:06 PM
 */
public class User {
    private Role role = Role.CUSTOMER;
    private String username;
    private String password;
    private UserInfo userInfo;
    private List<UserAddress> userAddressList;
    private List<Order> orders;
}
