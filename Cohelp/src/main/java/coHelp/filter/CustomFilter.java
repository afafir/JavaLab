package coHelp.filter;

import net.bytebuddy.dynamic.scaffold.FieldLocator;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
    import java.net.http.HttpRequest;

public class CustomFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String useragent = req.getHeader("User-Agent");
        if (!(req.getHeader("User-Agent").contains("YaBrowser"))){
            chain.doFilter(request, response);
        }throw new IllegalStateException();
    }
}
