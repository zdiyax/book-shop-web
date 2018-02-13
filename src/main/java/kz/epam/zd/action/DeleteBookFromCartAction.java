package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.REDIRECT_PREFIX;

public class DeleteBookFromCartAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        HashMap books = (HashMap) req.getSession().getAttribute("cart");
        int id = Integer.parseInt(req.getParameter("book"));
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
        req.getSession().setAttribute("cart", books);

        return REDIRECT_PREFIX + "/do/?action=show-cart-page";
    }
}
