package zd.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Zhannur Diyas
 * 1/29/2017 | 2:40 PM
 */
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
