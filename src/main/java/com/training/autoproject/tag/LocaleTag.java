package com.training.autoproject.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Oleg on 16-May-17.
 */
public class LocaleTag extends TagSupport {
    private String param;

    public void setParam(String param) {
        this.param = param;
    }

    public int doStartTag() throws JspException {

        try {
            pageContext.getOut().print("<a href=?lang=" + param + " class=locale id=locale>" + param + "</a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}