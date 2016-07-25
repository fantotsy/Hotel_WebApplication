package ua.fantotsy.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorTag extends TagSupport {
    private String errorType;
    private Locale locale;

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        //System.out.println("errorTypeIs:" + errorType + "!");
        //System.out.println("localeIs:" + locale + "!");
        if (errorType != null && !errorType.equals("")) {
            try {
                String language = locale.getLanguage();
                String error = errorType + "_" + language;

                ResourceBundle rb = ResourceBundle.getBundle("ua.fantotsy.properties.errors.messages");
                String errorMessage = (String) rb.getObject(error);

                pageContext.getOut().write("<span>" + errorMessage + "</span>");
            } catch (IOException ex) {
                throw new JspException(ex.getMessage());
            }
        }
        return SKIP_BODY;
    }
}