package test;

import com.zt.sr.pojo.User;
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

@WebServlet(name = "test.JsonServlet", urlPatterns = "/test.JsonServlet")
public class JsonServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("jsonServlet");
        response.setContentType("text/html");
//        String str = request

        PrintWriter out = response.getWriter();
        List<User> list = new ArrayList<>();//传递List
        //Map m=new HashMap();//传递Map
        
        User u1 = new User();
        u1.setAccount("zah");
        u1.setPassword("123");
        User u2 = new User();
        u2.setAccount("ztf");
        u2.setPassword("456");
        System.out.println("u1:" + u1.getAccount() + u1.getPassword());
        System.out.println("u2:" + u2.getAccount() + u2.getPassword());
        list.add(u1); //添加User对象
        list.add(u2); //添加User对象
        //m.put("u1", u1);
        //m.put("u2", u2);
//        JSONArray jsonArray2 = JSONArray.fromObject(list);//转化成json对象
        JSONArray jsonArray = JSONArray.fromObject(list);
        out.print(jsonArray);//返给ajax请求;
        System.out.println(jsonArray.toString());
        out.close();
    }
}
