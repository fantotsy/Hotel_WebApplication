package ua.fantotsy.controllers;

import javax.servlet.http.HttpServletRequest;

public interface ISessionRequestWrapper {
    Boolean getSessionInvalidated();

    void setSessionInvalidated(Boolean sessionInvalidated);

    String getRequestParameter(String key);

    String[] getRequestParameters(String key);

    Object getSessionAttribute(String key);

    void extractRequestParameters(HttpServletRequest request);

    void extractSessionAttributes(HttpServletRequest request);

    void setRequestAttribute(String key, Object value);

    void setSessionAttribute(String key, Object value);

    void insertAttributes(HttpServletRequest request);

    String sessionInvalidate();
}