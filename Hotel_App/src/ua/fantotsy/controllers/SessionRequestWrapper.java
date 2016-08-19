package ua.fantotsy.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code SessionRequestWrapper} implements {@link ISessionRequestWrapper}
 * and is the wrapper of session and request. It includes all request parameters
 * and session attributes, which are collected in several {@link HashMap}.
 * Also {@code SessionRequestWrapper} contains all needed methods for
 * request and session modification.
 *
 * @author fantotsy
 * @version 1.0
 */
public class SessionRequestWrapper implements ISessionRequestWrapper {
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private Boolean isSessionInvalidated = false;
    private String sessionId;

    public SessionRequestWrapper(HttpServletRequest request) {
        requestParameters = new HashMap<>();
        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();

        HttpSession session = request.getSession(true);
        sessionId = session.getId();
        isSessionInvalidated = Boolean.parseBoolean((String) request.getAttribute("isSessionInvalidated"));
        extractRequestParameters(request);
        extractSessionAttributes(request);
    }

    /**
     * Constructor for creating {@code SessionRequestWrapper} during JUnit testing.
     */
    public SessionRequestWrapper(HashMap<String, String[]> requestParameters, HashMap<String, Object> sessionAttributes) {
        this.requestParameters = requestParameters;
        this.sessionAttributes = sessionAttributes;
        requestAttributes = new HashMap<>();
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public Boolean getSessionInvalidated() {
        return isSessionInvalidated;
    }

    @Override
    public void setSessionInvalidated(Boolean sessionInvalidated) {
        isSessionInvalidated = sessionInvalidated;
    }

    @Override
    public void extractRequestParameters(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        if (map != null) {
            requestParameters.putAll(map);
        }
    }

    @Override
    public void extractSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String attributeKey = attributes.nextElement();
            Object attributeValue = session.getAttribute(attributeKey);
            sessionAttributes.put(attributeKey, attributeValue);
        }
    }

    @Override
    public String getRequestParameter(String key) {
        String[] value = requestParameters.get(key);
        if (value != null) {
            return value[0];
        } else {
            return null;
        }
    }

    @Override
    public String[] getRequestParameters(String key) {
        return requestParameters.get(key);
    }

    @Override
    public void setRequestAttribute(String key, Object value) {
        requestAttributes.put(key, value);
    }

    @Override
    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    @Override
    public void setSessionAttribute(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    /**
     * Returns a specific string, which is used in {@link ServletController}
     * to determine whether session was invalidated.
     */
    @Override
    public String sessionInvalidate() {
        return "session_invalidate";
    }

    /**
     * Inserts attributes, added during command manipulations, into
     * {@code request} before redirection in {@link ServletController}.
     */
    @Override
    public void insertAttributes(HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : requestAttributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            request.setAttribute(key, value);
        }
        HttpSession session = request.getSession();
        for (Map.Entry<String, Object> entry : sessionAttributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            session.setAttribute(key, value);
        }
    }
}