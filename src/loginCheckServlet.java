import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
//import userDao;


@WebServlet(name = "loginCheckServlet", urlPatterns = "/loginCheckServlet")
public class loginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();

        String account = request.getParameter("account");
        String password = request.getParameter("password");
//        String[] remember = request.getParameterValues("remember");

        userDao ud = new userDao();
        User user = ud.select(account);


        //获取服务器账号密码 对比
        if (password.equals(user.getPassword())) {
            Cookie cookie = new Cookie("account", user.getAccount());
            response.addCookie(cookie);
            response.sendRedirect("html/main.html");
        } else {
            Cookie cookie = new Cookie("account", user.getAccount());
            cookie.setMaxAge(0);//清除cookie
            response.addCookie(cookie);
            response.sendRedirect("html/index.html");
//            session.setAttribute("loginInfo", "账号或密码输入错误，请重新登录");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
