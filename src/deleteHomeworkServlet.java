import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteHomeworkServlet")
public class deleteHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hno = request.getParameter("hno");
        homeworkDao hd = new homeworkDao();
        //要加判断
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                String account = cookie.getValue();

                Homework homework = hd.select(hno);
                if (homework.getIssuer().equals(account)) {
                    hd.delete(hno);
                } else {
                    System.out.println("非法删除");
                }
                return;
            }
        }
        response.sendRedirect("html/index.html");//没找到关于account的cookie就退出
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
