package ua.fantotsy.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Class {@code EncodingFilter} is an encoding filter, which is used to set specific encoding
 * before {@link ua.fantotsy.controllers.ServletController} starts its work.
 *
 * @author fantotsy
 * @version 1.0
 */
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.encoding = config.getInitParameter("defaultEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String currentEncoding = request.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(currentEncoding)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}