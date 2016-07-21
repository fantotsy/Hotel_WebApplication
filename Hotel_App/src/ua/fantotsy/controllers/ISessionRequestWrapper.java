package ua.fantotsy.controllers;

import javax.servlet.http.HttpServletRequest;

public interface ISessionRequestWrapper {
    String getRequestParameter(String key);

    String[] getRequestParameters(String key);

    Object getSessionAttribute(String key);

    void extractRequestParameters(HttpServletRequest request);

    void extractSessionAttributes();

    void setRequestAttribute(String key, Object value);

    void setSessionAttribute(String key, Object value);

    void insertAttributes(HttpServletRequest request);

    void sessionInvalidate();
}
