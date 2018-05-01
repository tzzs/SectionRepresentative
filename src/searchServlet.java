import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
