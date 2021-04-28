package rs.ac.uns.ftn.nistagram.auth.middle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HttpUtil {

    @Value("${nistagram.const.http.username-key}")
    public String USERNAME_KEY;

    public void setHttpRequestAttribute(HttpServletRequest request, String attrKey, Object value) {
        request.setAttribute(attrKey, value);
    }

    public Object getHttpRequestAttribute(HttpServletRequest request, String attrKey) {
        return request.getAttribute(attrKey);
    }
}
