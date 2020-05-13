package coHelp.filter;

import net.bytebuddy.dynamic.scaffold.FieldLocator;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
    import java.net.http.HttpRequest;
@Component
public class CustomFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getParameter("lang") != null) {
            String locale = request.getParameter("lang");
            if (locale.equals("en")) {
                ((HttpServletResponse) response).addCookie(new Cookie("localeInfo", "en"));
                chain.doFilter(request, response);
            } else if (locale.equals("ru")) {
                ((HttpServletResponse) response).addCookie(new Cookie("localeInfo", "ru"));
                chain.doFilter(request, response);
            } else {
                response.getWriter().println("unrecognized lang");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

}
