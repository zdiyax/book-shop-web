package kz.epam.zd.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Utility class to store all widely used constants.
 */
public final class ConstantHolder {

    //UTILS
    public static final String REDIRECT_PREFIX = "redirect:";
    public static final String REFERER = "referer";
    public static final String ACTION = "action";
    public static final String WEB_INF_PATH_TO_JSP = "/WEB-INF/jsp/";
    public static final String EXT_JSP = ".jsp";
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final String LOCALE_EN = "en";
    public static final String LOCALE_RU = "ru";
    public static final String STATUSES = "statuses";

    //INDEXES FOR RESULT SET
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int INDEX_4 = 4;
    public static final int INDEX_5 = 5;
    public static final int INDEX_6 = 6;
    public static final int INDEX_7 = 7;
    public static final int INDEX_8 = 8;
    public static final int INDEX_9 = 9;

    public static final int ZERO = 0;
    public static final int ONE = 1;

    //ATTRIBUTES FROM HTTP REQUEST
    public static final String PAGE = "page";
    public static final String PAGE_COUNT = "pageCount";
    public static final String SEARCH = "search";
    public static final int DEFAULT_PAGE_COUNT = 10;
    public static final String SEARCH_INPUT = "search_input";

    public static final String BOOKS = "books";
    public static final String BOOK = "book";
    public static final String CART = "cart";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String PRICE = "price";
    public static final String ISBN = "isbn";
    public static final String DESCRIPTION = "description";
    public static final String QUANTITY = "quantity";
    public static final String TOTAL_COST = "totalCost";

    public static final String FULL_NAME = "fullName";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String TELEPHONE_NUMBER = "telephoneNumber";

    public static final String ID = "id";
    public static final String DETAILS = "details";

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "confirm_password";
    public static final String USER = "user";
    public static final String USERNAME = "username";
    public static final String LOCALE = "locale";
    public static final String FORM_ERRORS = "FormErrors";
    public static final String ERROR = "error";
    public static final String ORDERS = "orders";

    //REDIRECTS
    public static final String REDIRECT_CART_PAGE = "redirect:/do/?action=show-cart-page";
    public static final String BOOK_DETAILS_BY_ID = "/do/?action=show-book-details&id=";


    /**
     * Local solution for i18n of statuses, as it's the only thing to be localized.
     */
    public static ArrayList<String> ORDER_STATUSES_EN = new ArrayList<String>() {{
        add("waiting");
        add("ready");
        add("completed");
        add("cancelled");
    }};

    public static ArrayList<String> ORDER_STATUSES_RU = new ArrayList<String>() {{
        add("ожидает");
        add("готов");
        add("выполнен");
        add("отменен");
    }};

    public static HashMap<String, String> ORDER_STATUSES = new HashMap<String, String>() {{
        put("waiting", "ожидает");
        put("ready", "готов");
        put("completed", "выполнен");
        put("cancelled", "отменен");
    }};

}
