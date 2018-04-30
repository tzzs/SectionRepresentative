import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addAttentionServlet")
public class addAttentionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String attention = request.getParameter("attention");
        userDao ud = new userDao();
        Cookie[] cookies = request.getCookies();
        String account = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                account = cookie.getValue();
            } else {
                //权限不足 重新登录
                response.sendRedirect("html/index.html");
                return;
            }
        }
        if (account == null) {
            //权限不足 重新登录
            response.sendRedirect("html/index.html");
            return;
        }
        User user = ud.select(account);
        String attentions = user.getAttention();
        attentions += ";" + attention;
        user.setAttention(attentions);
        ud.update(user);//更新关注人
        ud.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
