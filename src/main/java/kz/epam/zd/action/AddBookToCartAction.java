package kz.epam.zd.action;

import kz.epam.zd.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.REDIRECT_PREFIX;
import static kz.epam.zd.util.ConstantHolder.REFERER;

public class AddBookToCartAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        Book bookToAddToCart = (Book) req.getSession().getAttribute("book");
        HashMap hashMap = (HashMap) req.getSession().getAttribute("cart");
        putBookToMapIfAbsent(bookToAddToCart, hashMap);

        req.getSession().setAttribute("cart", hashMap);
        String referrer = req.getHeader(REFERER);
        return REDIRECT_PREFIX + referrer;
    }

    private void putBookToMapIfAbsent(Book bookToAddToCart, HashMap hashMap) {
        boolean flag = false;
        for (Object o : hashMap.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            Book book1 = (Book) pair.getKey();
            if (book1.getId().equals(bookToAddToCart.getId()))
                flag = true;
        }

        if (!flag) {
            hashMap.put(bookToAddToCart, 1);
        }
    }

}
