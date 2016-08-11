package ua.fantotsy.filters;

import ua.fantotsy.utils.ActionsGetter;
import ua.fantotsy.utils.UrnGetter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code AuthorizationFilter} is an authorization filter, which is used
 * to prevent illegal access. Also {@code AuthorizationFilter} prevents some
 * senseless access, such as access of logged in user to the start page.
 *
 * @author fantotsy
 * @version 1.0
 */
public class AuthorizationFilter implements Filter {
    private List<String> adminURIs;
    private List<String> guestURIs;
    private List<String> nonUserURIs;
    private List<String> generalURIs;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminURIs = new ArrayList<String>() {{
            add(ActionsGetter.getInstance().getAction(ActionsGetter.ADMIN));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.RESERVATIONS));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.APARTMENTS));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.GUESTS));
            add(UrnGetter.getInstance().getUrn(UrnGetter.ADMIN_ICON_IMG));
        }};
        guestURIs = new ArrayList<String>() {{
            add(ActionsGetter.getInstance().getAction(ActionsGetter.GUEST));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.BOOKING));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.ORDER_VALID));
            add(UrnGetter.getInstance().getUrn(UrnGetter.USER_ICON_IMG));
        }};
        nonUserURIs = new ArrayList<String>() {{
            add(ActionsGetter.getInstance().getAction(ActionsGetter.ROOT));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.INDEX));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.REGISTRATION));
            add(UrnGetter.getInstance().getUrn(UrnGetter.INDEX_CSS));
            add(UrnGetter.getInstance().getUrn(UrnGetter.REGISTRATION_CSS));
        }};
        generalURIs = new ArrayList<String>() {{
            add(ActionsGetter.getInstance().getAction(ActionsGetter.MAIN));
            add(UrnGetter.getInstance().getUrn(UrnGetter.GUEST_CSS));
            add(UrnGetter.getInstance().getUrn(UrnGetter.ADMIN_CSS));
            add(UrnGetter.getInstance().getUrn(UrnGetter.ERROR_CSS));
            add(UrnGetter.getInstance().getUrn(UrnGetter.SAD_CAT_ERROR_IMG));
            add(UrnGetter.getInstance().getUrn(UrnGetter.ENGLISH_LANGUAGE_IMG));
            add(UrnGetter.getInstance().getUrn(UrnGetter.UKRAINIAN_LANGUAGE_IMG));
            add(UrnGetter.getInstance().getUrn(UrnGetter.HEADER_CSS));
            add(UrnGetter.getInstance().getUrn(UrnGetter.FAVICON_ICO));
        }};
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(true);
        if (session != null) {
            String role = (String) session.getAttribute("role");
            String uriPath = ((HttpServletRequest) request).getRequestURI();
            System.out.println(role);
            System.out.println(uriPath);
            if (guestURIs.contains(uriPath)) {

                if (role != null && role.equals("guest")) {
                    chain.doFilter(request, response);
                } else {
                    System.out.println("error1");
                    request.getRequestDispatcher(UrnGetter.getInstance().getUrn(UrnGetter.ERROR_PAGE)).forward(request, response);
                }
            } else if (adminURIs.contains(uriPath)) {
                if (role != null && role.equals("admin")) {
                    chain.doFilter(request, response);
                } else {
                    System.out.println("error2");
                    request.getRequestDispatcher(UrnGetter.getInstance().getUrn(UrnGetter.ERROR_PAGE)).forward(request, response);
                }
            } else if (nonUserURIs.contains(uriPath)) {
                if (role == null || request.getParameter("logout") != null) {
                    chain.doFilter(request, response);
                } else {
                    if (role.equals("admin")) {
                        request.getRequestDispatcher(ActionsGetter.getInstance().getAction(ActionsGetter.ADMIN)).forward(request, response);
                    } else {
                        request.getRequestDispatcher(ActionsGetter.getInstance().getAction(ActionsGetter.GUEST)).forward(request, response);
                    }
                }
            } else if (generalURIs.contains(uriPath)) {
                chain.doFilter(request, response);
            } else {
                System.out.println("error3");
                request.getRequestDispatcher(UrnGetter.getInstance().getUrn(UrnGetter.ERROR_PAGE)).forward(request, response);
            }
        } else {
            System.out.println("error4");
            request.getRequestDispatcher(UrnGetter.getInstance().getUrn(UrnGetter.ERROR_PAGE)).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}