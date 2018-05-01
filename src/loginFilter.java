import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginFilter")//,urlPatterns = "/html/index.html"
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println("filter");
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            System.out.println("index.html");
            response.sendRedirect("index.html");
            chain.doFilter(req, resp);
        } else {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals("account")) {
                    System.out.println("main.html");
                    response.sendRedirect("main.html");
                }
            }
            chain.doFilter(req, resp);
            destroy();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
