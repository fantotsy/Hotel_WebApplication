package ua.fantotsy.filters;

import ua.fantotsy.utils.ActionsGetter;
import ua.fantotsy.utils.URNsGetter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an authorization filter, which is used to prevent illegal access.
 * Also this filter prevents some senseless access, such as access of signed in user to the start page.
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
            add(URNsGetter.getInstance().getURN(URNsGetter.ADMIN_ICON_IMG));
        }};
        guestURIs = new ArrayList<String>() {{
            add(ActionsGetter.getInstance().getAction(ActionsGetter.GUEST));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.BOOKING));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.ORDER_VALID));
            add(URNsGetter.getInstance().getURN(URNsGetter.USER_ICON_IMG));
        }};
        nonUserURIs = new ArrayList<String>() {{
            add(ActionsGetter.getInstance().getAction(ActionsGetter.ROOT));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.INDEX));
            add(ActionsGetter.getInstance().getAction(ActionsGetter.REGISTRATION));
            add(URNsGetter.getInstance().getURN(URNsGetter.INDEX_CSS));
            add(URNsGetter.getInstance().getURN(URNsGetter.REGISTRATION_CSS));
        }};
        generalURIs = new ArrayList<String>() {{
            add(ActionsGetter.getInstance().getAction(ActionsGetter.MAIN));
            add(URNsGetter.getInstance().getURN(URNsGetter.GUEST_CSS));
            add(URNsGetter.getInstance().getURN(URNsGetter.ADMIN_CSS));
            add(URNsGetter.getInstance().getURN(URNsGetter.ERROR_CSS));
            add(URNsGetter.getInstance().getURN(URNsGetter.SAD_CAT_ERROR_IMG));
            add(URNsGetter.getInstance().getURN(URNsGetter.ENGLISH_LANGUAGE_IMG));
            add(URNsGetter.getInstance().getURN(URNsGetter.UKRAINIAN_LANGUAGE_IMG));
            add(URNsGetter.getInstance().getURN(URNsGetter.HEADER_CSS));
            add(URNsGetter.getInstance().getURN(URNsGetter.FAVICON_ICO));
        }};
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(true);
        if (session != null) {
            String role = (String) session.getAttribute("role");
            String uriPath = ((HttpServletRequest) request).getRequestURI();
            if (guestURIs.contains(uriPath)) {
                if (role != null && role.equals("guest")) {
                    chain.doFilter(request, response);
                } else {
                    System.out.println("error1");
                    request.getRequestDispatcher(URNsGetter.getInstance().getURN(URNsGetter.ERROR_PAGE)).forward(request, response);
                }
            } else if (adminURIs.contains(uriPath)) {
                if (role != null && role.equals("admin")) {
                    chain.doFilter(request, response);
                } else {
                    System.out.println("error2");
                    request.getRequestDispatcher(URNsGetter.getInstance().getURN(URNsGetter.ERROR_PAGE)).forward(request, response);
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
                request.getRequestDispatcher(URNsGetter.getInstance().getURN(URNsGetter.ERROR_PAGE)).forward(request, response);
            }
        } else {
            System.out.println("error4");
            request.getRequestDispatcher(URNsGetter.getInstance().getURN(URNsGetter.ERROR_PAGE)).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}