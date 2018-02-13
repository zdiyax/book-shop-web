package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ShowCartPageAction implements Action {

    private int totalCost = 0;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        HashMap hashMap = (HashMap) req.getSession().getAttribute("cart");
        calculateTotalCost(hashMap);
        req.getSession().setAttribute("totalCost", totalCost);
        return "cart";
    }

    private void calculateTotalCost(HashMap hashMap) {
        for (Object o : hashMap.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            Book book1 = (Book) pair.getKey();
            totalCost = totalCost + book1.getPrice() * (int) pair.getValue();
        }
    }
}
