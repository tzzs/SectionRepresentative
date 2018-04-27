import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.HttpCookie;

@WebServlet(name = "loginCheckServlet")
public class loginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("remember") && cookie.getValue().equals("true")) {
                //无需登录
            }
        }

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String[] remember = request.getParameterValues("remember");

        //获取服务器账号密码 对比
        if (account.equals("") && password.equals("")) {
            if (remember[0].equals("remember-me")) {
                Cookie newCookie = new Cookie("remember", "true");
            }

        } else {
            request.getRequestDispatcher("html/login.html");
            session.setAttribute("loginInfo", "账号或密码输入错误，请重新登录");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
