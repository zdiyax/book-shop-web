package kz.epam.zd.action;

import kz.epam.zd.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.CART;
import static kz.epam.zd.util.ConstantHolder.TOTAL_COST;

/**
 * Customer action to display Cart page.
 */
public class ShowCartPageAction implements Action {

    private int totalCost = 0;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HashMap hashMap = (HashMap) request.getSession().getAttribute(CART);
        calculateTotalCost(hashMap);
        request.getSession().setAttribute(TOTAL_COST, totalCost);
        return CART;
    }

    /**
     * Calculates total cost of books in the cart.
     *
     * @param hashMap shopping cart
     */
    private void calculateTotalCost(HashMap hashMap) {
        for (Object o : hashMap.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            Book book = (Book) pair.getKey();
            totalCost = totalCost + book.getPrice() * (int) pair.getValue();
        }
    }
}
