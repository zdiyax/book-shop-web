package kz.epam.zd.action;

import kz.epam.zd.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Customer action to add Book to the session shopping cart.
 */
public class AddBookToCartAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Book bookToAddToCart = (Book) request.getSession().getAttribute(BOOK);
        HashMap hashMap = (HashMap) request.getSession().getAttribute(CART);
        //noinspection unchecked
        putBookToCartIfAbsent(bookToAddToCart, hashMap);

        request.getSession().setAttribute(CART, hashMap);
        String referrer = request.getHeader(REFERER);
        return REDIRECT_PREFIX + referrer;
    }

    /**
     * Checks whether book is already present in user's shopping cart and puts one
     * entity of it if not.
     *
     * @param bookToAddToCart book to be checked in the cart and put if absent
     * @param hashMap shopping cart HashMap instance
     */
    private void putBookToCartIfAbsent(Book bookToAddToCart, HashMap<Book, Integer> hashMap) {
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
