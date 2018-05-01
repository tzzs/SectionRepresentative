import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "myPublishServlet", urlPatterns = "/myPublishServlet")
public class myPublishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                String account = cookie.getValue();
                homeworkDao hd = new homeworkDao();
                List<Homework> homeworkList = hd.selectAll(account);
                JSONArray json = JSONArray.fromObject(homeworkList);
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
