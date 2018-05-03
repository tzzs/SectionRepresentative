import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addAttentionServlet", urlPatterns = "/addAttentionServlet")
public class addAttentionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String attention = request.getParameter("attention");
        PrintWriter out = response.getWriter();
        userDao ud = new userDao();
        Cookie[] cookies = request.getCookies();
        String account = null;
        System.out.println(attention);
        if (attention != null && !attention.equals("")) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")) {
                    account = cookie.getValue();
                    System.out.println(account);

                    User user = ud.select(account);
                    String attentions = user.getAttention();
                    if (attentions != null) {
                        String[] attentionsList = attentions.split(";");
                        for (String a : attentionsList) {
                            if (a.equals(attention)) {
                                out.print("<p>添加失败，<a href=\"html/add.html\">点击返回...</a></p>");
                                return;
                            }
                        }
                    }
                    attentions += attention + ";";
                    user.setAttention(attentions);
                    ud.update(user);//更新关注人
                    out.print("<p>添加成功，<a href=\"html/add.html\">点击返回...</a></p>");
                    out.close();
                }
            }
        }
        if (account == null || account.equals("")) {
//            response.sendRedirect("html/index.html");
            out.print("<p>未登录，<a href=\"html/index.html\">点击返回...</a></p>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
