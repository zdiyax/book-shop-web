package kz.epam.zd.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Manages encoding used on the pages.
 */
public class EncodingFilter implements Filter {

    private static final String ENCODING = "encoding";
    private String encoding = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) {
        String encodingParam = filterConfig.getInitParameter(ENCODING);
        if (encodingParam != null) encoding = encodingParam;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
