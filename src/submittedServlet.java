import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "submittedServlet", urlPatterns = "/submittedServlet")
public class submittedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String hno = request.getParameter("hno");
        fileDao fd = new fileDao();
        List<String> submitted = fd.select(hno);//获取已提交账号

        userDao ud = new userDao();
        List<User> userList = new ArrayList<>();
        for (String submit:submitted){
            userList.add(ud.select(submit));
        }

        JSONArray jsonArray = JSONArray.fromObject(userList);

//        JSON json = JSONArray.fromObject(submitted);

        out.print(jsonArray);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
