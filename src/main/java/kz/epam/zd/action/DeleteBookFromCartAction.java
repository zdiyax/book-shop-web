package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Customer action to delete a book instance from the shopping cart.
 */
public class DeleteBookFromCartAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HashMap books = (HashMap) request.getSession().getAttribute(CART);
        int id = Integer.parseInt(request.getParameter(BOOK));
        for (int i = 0; i < books.size(); i++) {
            for (Object o : books.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                Book book1 = (Book) pair.getKey();
                if (book1.getId() == id) {
                    books.remove(pair.getKey());
                    break;
                }
            }
        }

        request.getSession().setAttribute(CART, books);
        return REDIRECT_CART_PAGE;
    }
}
