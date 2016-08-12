package ua.fantotsy.controllers;

import javax.servlet.http.HttpServletRequest;

public interface ISessionRequestWrapper {
    String getSessionId();

    Boolean getSessionInvalidated();

    void setSessionInvalidated(Boolean sessionInvalidated);

    void extractRequestParameters(HttpServletRequest request);

    void extractSessionAttributes(HttpServletRequest request);

    String getRequestParameter(String key);

    String[] getRequestParameters(String key);

    void setRequestAttribute(String key, Object value);

    Object getSessionAttribute(String key);

    void setSessionAttribute(String key, Object value);

    String sessionInvalidate();

    void insertAttributes(HttpServletRequest request);
}