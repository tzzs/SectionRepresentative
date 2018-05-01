import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Servlet implementation class PublishAttentionServlet
 */
@WebServlet(name = "publishServlet", urlPatterns = "/publishServlet")
public class PublishAttentionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            hd.add(homework);

            String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/FILE");
            System.out.println(fileSaveRootPath);
            File file = new File(fileSaveRootPath + homework.getHdir());
            if (file.exists()) {
                System.out.println("this file is exist");
            } else {
                file.mkdirs();
            }

            hd.close();
        }
        response.sendRedirect("html/publish.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }

    /*
     * 获取文件的名字
     */
    public String getId() {
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date d = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-ss");//转换格式
        System.out.println(sdf.format(d));//打印
        return sdf.format(d);

    }

}
