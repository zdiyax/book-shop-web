package kz.epam.zd.model.user;

import kz.epam.zd.model.Model;
import kz.epam.zd.model.Order;

import java.util.List;

public class User extends Model {
    private UserRole role;
    private Locale locale;
    private String username;
    private String password;
    private List<Order> orders;
    private String fullName;
    private String email;
    private String address;
    private String telephoneNumber;

    public User(String username, String password, Locale locale, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.locale = locale;
        this.role = userRole;
    }

    public User(UserRole userRole, Locale locale, String username, String password, List<Order> orders, String fullName, String email, String address, String telephoneNumber) {
        this.role = userRole;
        this.locale = locale;
        this.username = username;
        this.password = password;
        this.orders = orders;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public User() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
