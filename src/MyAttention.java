

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

/**
 * Servlet implementation class MyAttention
 */
@WebServlet(name = "/MyAttention", urlPatterns = "/myAttention")
public class MyAttention extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ;
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String account = "";
        String attention = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")) {
                    account = cookie.getValue();
                    userDao ud = new userDao();
                    User user = ud.select(account);
                    attention = user.getAttention();
//                    System.out.println("attention: " + attention);
                    if (attention != null && !attention.equals("")) {
                        String[] attentions = attention.split(";");
//                        System.out.println(attentions[0]+attentions[1]+attentions[2]);
                        List<User> userList = new ArrayList<>();
                        for (String a : attentions) {
                            userList.add(ud.select(a));
                        }
                        JSONArray jsonArray = JSONArray.fromObject(userList);
                        out.print(jsonArray);
                    }
                }
            }
        } else {
            System.out.println("to login");
            response.sendRedirect("html/index.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
