package ua.fantotsy.filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private FilterConfig filterConfig = null;
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