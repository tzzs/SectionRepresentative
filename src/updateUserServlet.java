import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updateUserServlet", urlPatterns = "/updateUserServlet")
public class updateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String school = request.getParameter("school");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        System.out.println(school + name + email);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                String account = cookie.getValue();
                userDao ud = new userDao();
                User user = ud.select(account);

                if (name != null && !name.equals("")) {
                    user.setName(name);
                }
                if (email != null && !email.equals("")) {
                    user.setEmail(email);
                }
                if (school != null && !school.equals("")) {
                    user.setSchool(school);
                }

                ud.update(user);
                out.print("<p>修改完成...</p>");
                response.sendRedirect("html/manage.html");
                out.close();
                return;
            }
        }
        response.sendRedirect("html/index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
