package ua.fantotsy.filters;

import org.apache.log4j.Logger;
import ua.fantotsy.utils.UrnGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CsrfFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);
        String sessionId = session.getId();
        String encryptedSessionId = Utils.encryptionMD5(sessionId);
        String antiCsrfToken = httpRequest.getParameter("anti_csrf_token");

        if (antiCsrfToken != null && encryptedSessionId != null && encryptedSessionId.equals(antiCsrfToken)) {
            chain.doFilter(request, response);
        } else {
            Logger logger = Logger.getLogger(CsrfFilter.class.getName());
            logger.warn("Potential CSRF detected!");
            request.getRequestDispatcher(UrnGetter.getInstance().getUrn(UrnGetter.ERROR_PAGE)).forward(request, response);
        }

    }

    @Override
    public void destroy() {
    }
}
