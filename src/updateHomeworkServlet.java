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

            homeworkDao hd = new homeworkDao();

            String hno = request.getParameter("hno");
            System.out.println("hnohno:" + hno);
            homework = hd.select(hno);

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh:ss");

            String content = request.getParameter("hcontent");
            if (content != null && !content.equals("")) {
                homework.setHcontent(content);
            }


            String begin = request.getParameter("beginTime");
            Date beginTime = null;
            if (begin != null && !begin.equals("")) {
                begin = begin.replace("T", "-");
                System.out.println(begin);
                try {
                    beginTime = new Date(format.parse(begin).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                homework.setBeginTime(beginTime);
            }

            String end = request.getParameter("endTime");
            Date endTime = null;
            if (end != null && !end.equals("")) {
                end = end.replace("T", "-");
                System.out.println(end);
                try {
                    endTime = new Date(format.parse(end).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                homework.setEndTime(endTime);
            }
            System.out.println("a:" + content);
            System.out.println("a:" + beginTime);
            System.out.println("a:" + endTime);
            System.out.println("a:" + homework.getHcontent());
            System.out.println("hno:" + homework.getHno());
            System.out.println("a:" + endTime);
            hd.update(homework);
            response.sendRedirect("html/publish3.html");
        } else {
            response.sendRedirect("html/index.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
