package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * General interface for action classes operated by the servlet.
 */
public interface Action {

    /**
     * Executes certain functionality on user commands.
     *
     * @param request the http request to be processed
     * @param response the http response to be formulated
     * @throws ActionException action layer exception wrapper
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException;

}
