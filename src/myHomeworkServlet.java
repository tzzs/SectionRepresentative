import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "myHomeworkServlet", urlPatterns = "/myHomeworkServlet")
public class myHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        PrintWriter out = response.getWriter();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                String account = cookie.getValue();
                userDao ud = new userDao();

                User user = ud.select(account);
                String[] attentions = user.getAttention().split(";");
                homeworkDao hd = new homeworkDao();

                List<Homework> homeworkAllList = new ArrayList<>();

                for (String attention:attentions){
                    List<Homework> homeworkList = hd.selectAll(account);
                    homeworkAllList.addAll(homeworkList);
                }

                JSONArray json = JSONArray.fromObject(homeworkAllList);
                out.print(json);
                System.out.println(json);
                out.close();
                return;
            }
        }
        response.sendRedirect("html/index.html");//没找到关于account的cookie就退出
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
