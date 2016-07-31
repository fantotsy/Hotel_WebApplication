package ua.fantotsy.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class {@code ErrorTag} describes the custom tag named 'errorTag'.
 *
 * @author fantotsy
 * @version 1.0
 */
public class ErrorTag extends TagSupport {
    private Logger logger = Logger.getLogger(ErrorTag.class.getName());
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
        if (errorType != null && !errorType.equals("")) {
            try {
                String language = locale.getLanguage();
                String error = errorType + "_" + language;

                ResourceBundle rb = ResourceBundle.getBundle("ua.fantotsy.properties.errors.messages");
                String errorMessage = (String) rb.getObject(error);

                pageContext.getOut().write("<div class=\"error\"><span>" + errorMessage + "</span></div>");
            } catch (IOException e) {
                logger.error(e);
                throw new JspException(e.getMessage());
            }
        }
        return SKIP_BODY;
    }
}