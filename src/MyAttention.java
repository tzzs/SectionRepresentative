

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
import java.util.StringTokenizer;

/**
 * Servlet implementation class MyAttention
 */
@WebServlet(name = "/MyAttention", urlPatterns = "/myAttention")
public class MyAttention extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { ;
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ArrayList<User> listInfor = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        String account = "";
        String attention = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")) {
                    System.out.println("amgrpk");
                    account = cookie.getValue();
                    User user = new User();
                    user.setAccount(account);
                    System.out.println("this is " + user.getName());
                    attention = user.getAttention();
                    if (attention != null) {
                        StringTokenizer stringTokenizer1 = new StringTokenizer(attention, ";");
                        while (stringTokenizer1.hasMoreTokens()) {
                            String string = "";
                            string = stringTokenizer1.nextToken();
                            list.add(string);
                        }
                        for (int i = 0; i < list.size(); i++) {
                            userDao userDao = new userDao();
                            listInfor.add(userDao.select(list.get(i)));
                        }
                        JSONArray jsonArray = JSONArray.fromObject(listInfor);
                        System.out.println("the is " + jsonArray);
                        out.print(jsonArray);
                        out.close();
                        break;
                    }
                }
            }
        } else {
            System.out.println("a'mgep");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
