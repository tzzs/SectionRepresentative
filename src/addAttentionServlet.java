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
                    User user = ud.select(account);
                    String attentions = user.getAttention();
                    String[] attentionsList = attentions.split(";");
                    for (String a : attentionsList) {
                        if (a.equals(attention)) {
                            out.close();//已存在
                            response.sendRedirect("html/add.html");
                            return;
                        }
                    }
                    attentions += ";" + attention;
                    user.setAttention(attentions);
                    ud.update(user);//更新关注人
                    ud.close();
                    out.print("<p>添加成功，即将跳转...</p>");
                    response.sendRedirect("html/add.html");
                    out.close();
                    return;
                }
            }
        }
        out.close();
        response.sendRedirect("html/index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
