package ua.fantotsy.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestWrapper implements ISessionRequestWrapper {
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;

    public SessionRequestWrapper(HttpServletRequest request) {
        requestParameters = new HashMap<>();
        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();

        extractRequestParameters(request);
        extractSessionAttributes(request);
    }

    @Override
    public void extractRequestParameters(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        if (map != null) {
            requestParameters.putAll(request.getParameterMap());
        }
    }

    @Override
    public void extractSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
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
    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    @Override
    public void setRequestAttribute(String key, Object value) {
        requestAttributes.put(key, value);
    }

    @Override
    public void setSessionAttribute(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    @Override
    public void insertAttributes(HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : requestAttributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            request.setAttribute(key, value);
        }

        HttpSession session = request.getSession(true);
        for (Map.Entry<String, Object> entry : sessionAttributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            session.setAttribute(key, value);
        }
    }

    @Override
    public String sessionInvalidate() {
        return "session_invalidate";
    }
}