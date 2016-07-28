package ua.fantotsy.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class describes the custom tag 'errorTag',
 * which is located in 'web/WEB-INF/TLDs/'.
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

                pageContext.getOut().write("<span>" + errorMessage + "</span>");
            } catch (IOException e) {
                logger.error(e);
                throw new JspException(e.getMessage());
            }
        }
        return SKIP_BODY;
    }
}