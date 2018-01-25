package kz.epam.zd.model.user;

import kz.epam.zd.model.Model;
import kz.epam.zd.model.Order;

import java.util.List;

public class User extends Model {
    private UserRole userRole;
    private Locale locale;
    private String username;
    private String password;
    private List<Order> orders;

    public User(String username, String password, Locale locale, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.locale = locale;
        this.userRole = userRole;
    }

    public User() {

    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
