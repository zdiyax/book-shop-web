package zd.action;

import zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public interface Action {

    String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException;
}
