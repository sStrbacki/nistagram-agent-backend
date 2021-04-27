package rs.ac.uns.ftn.nistagram.auth.middle;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

    public static final String USERNAME_KEY = "HTTP_REQUEST::KEY_USERNAME";

    public static void setHttpRequestAttribute(HttpServletRequest request, String attrKey, Object value) {
        request.setAttribute(attrKey, value);
    }

    public static Object getHttpRequestAttribute(HttpServletRequest request, String attrKey) {
        return request.getAttribute(attrKey);
    }
}
