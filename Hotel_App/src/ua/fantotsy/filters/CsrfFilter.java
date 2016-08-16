package ua.fantotsy.filters;

import org.apache.log4j.Logger;
import ua.fantotsy.utils.UrlGetter;
import ua.fantotsy.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class {@code CsrfFilter} is a filter, which is used to prevent CSRF attacks.
 * This defense uses token, which is certain session Id encrypted by MD5.
 *
 * @author fantotsy
 * @version 1.0
 */
public class CsrfFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);
        // In case of '/guest' URL it is needed to check whether guest came from booking page.
        // If yes - CSRF protection is not needed.
        String dateChosen = (String) session.getAttribute("date_chosen");
        if (dateChosen != null) {
            chain.doFilter(request, response);
        } else {
            String sessionId = session.getId();
            String encryptedSessionId = Utils.encryptionMD5(sessionId);
            String antiCsrfToken = httpRequest.getParameter("anti_csrf_token");
            if (antiCsrfToken != null && encryptedSessionId != null && encryptedSessionId.equals(antiCsrfToken)) {
                chain.doFilter(request, response);
            } else {
                if (httpRequest.getParameter("reservation_id") == null || httpRequest.getParameter("category_id") == null) {
                    chain.doFilter(request, response);
                } else {
                    Logger logger = Logger.getLogger(CsrfFilter.class.getName());
                    logger.warn("Potential CSRF detected!");
                    request.getRequestDispatcher(UrlGetter.getInstance().getUrl(UrlGetter.ERROR_PAGE)).forward(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {
    }
}
