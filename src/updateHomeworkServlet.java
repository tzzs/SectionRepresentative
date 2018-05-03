import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "updateHomeworkServlet", urlPatterns = "/updateHomeworkServlet")
public class updateHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<String> list = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        String account = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                account = cookie.getValue();
            }
        }
        if (account != null) {
            Homework homework = new Homework();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh:ss");

            String hno = request.getParameter("hno");
            String content = request.getParameter("hcontent");
            String example = request.getParameter("hfile");
            String begin = request.getParameter("beginTime");
            begin = begin.replace("T", "-");
            System.out.println(begin);
            Date beginTime = null;
            try {
                beginTime = new Date(format.parse(begin).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String end = request.getParameter("endTime");
            end = end.replace("T", "-");
            System.out.println(end);
            Date endTime = null;
            try {
                endTime = new Date(format.parse(end).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String subInfo = request.getParameter("subInfo");

            homework.setHno(hno);
            homework.setHcontent(content);
            homework.setHdir("/" + hno);
            homework.setBeginTime(beginTime);
            homework.setEndTime(endTime);
            homework.setSubInfo(subInfo);
            homework.setIssuer(account);

            homeworkDao hd = new homeworkDao();

            hd.update(homework);
        }
        response.sendRedirect("html/publish.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
