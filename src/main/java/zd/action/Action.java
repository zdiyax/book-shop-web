package zd.action;

import zd.exception.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:44 PM
 */
public interface Action {
    String execute(HttpServletRequest req, HttpServletResponse res) throws ValidatorException;
}
