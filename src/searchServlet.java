import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "searchServlet", urlPatterns = "/searchServlet")
public class searchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hno = request.getParameter("hno");
        homeworkDao hd = new homeworkDao();
        Homework homework = hd.select(hno);
        PrintWriter out = response.getWriter();


        JSONArray jsonArray = JSONArray.fromObject(homework);

        out.print(jsonArray);//返回给html
        System.out.println(jsonArray);
        out.close();
        if (homework.getBeginTime() != null) {//sql.date  to util.date
            homework.setBeginTime(new Date(homework.getBeginTime().getTime()));
        }
        if (homework.getEndTime() != null) {
            homework.setEndTime(new Date(homework.getEndTime().getTime()));
        }

        JSONArray json = JSONArray.fromObject(homework);
        out.print(json);
        System.out.println(json.toString());
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
