package ua.fantotsy.filters;

import ua.fantotsy.properties.Config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationFilter implements Filter {
    private List<String> adminURIs;
    private List<String> guestURIs;
    private List<String> nonUserURIs;
    private List<String> generalURIs;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminURIs = new ArrayList<>();
        guestURIs = new ArrayList<>();
        nonUserURIs = new ArrayList<>();
        generalURIs = new ArrayList<>();

        adminURIs.add("/admin");
        adminURIs.add("/reservations");
        adminURIs.add("/apartments");
        adminURIs.add("/guests");
        adminURIs.add("/images/admin_icon.png");

        guestURIs.add("/guest");
        guestURIs.add("/booking");
        guestURIs.add("/order_valid");
        guestURIs.add("/images/user_icon.png");

        nonUserURIs.add("/");
        nonUserURIs.add("/index");
        nonUserURIs.add("/registration");
        nonUserURIs.add("/css/index.css");
        nonUserURIs.add("/css/registration.css");

        generalURIs.add("/main");
        generalURIs.add("/css/main_guest.css");
        generalURIs.add("/css/error.css");
        generalURIs.add("/images/sad_cat_error.jpg");
        generalURIs.add("/images/english_language.jpg");
        generalURIs.add("/images/ukrainian_language.jpg");
        generalURIs.add("/favicon.ico");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(true);
        if (session != null) {
            String role = (String) session.getAttribute("role");
            String uriPath = ((HttpServletRequest) request).getRequestURI();
            //System.out.println("uri: " + uriPath);
            if (guestURIs.contains(uriPath)) {
                if (role != null && role.equals("guest")) {
                    chain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher(Config.getInstance().getProperty(Config.ERROR_PAGE)).forward(request, response);
                }
            } else if (adminURIs.contains(uriPath)) {
                if (role != null && role.equals("admin")) {
                    chain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher(Config.getInstance().getProperty(Config.ERROR_PAGE)).forward(request, response);
                }
            } else if (nonUserURIs.contains(uriPath)) {
                if (role == null || request.getParameter("logout").equals("true")) {
                    chain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher(Config.getInstance().getProperty(Config.ERROR_PAGE)).forward(request, response);
                }
            } else if (generalURIs.contains(uriPath)) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher(Config.getInstance().getProperty(Config.ERROR_PAGE)).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(Config.getInstance().getProperty(Config.ERROR_PAGE)).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}