import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "uploadServlet", urlPatterns = "/uploadServlet")
@MultipartConfig
public class uploadServlet extends javax.servlet.http.HttpServlet {
    private String getFilename(Part part) {
        String contentDispositionHeader = part.getHeader("content-disposition");
        String[] elements = contentDispositionHeader.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String hno = request.getParameter("hno");
        homeworkDao hd = new homeworkDao();
        Homework homework = hd.select(hno);

        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            if (part.getContentType() != null) {
                String fileName = getFilename(part);
                if (fileName != null && !fileName.isEmpty()) {
                    String path = getServletContext().getRealPath("/WEB-INF") + "/FILE";//文件夹路径
                    File filePath = new File(path);
                    if (!filePath.exists()) {
                        if (!filePath.mkdirs()) {
                            out.println("<p>文件储存地址创建失败</p>");
                            return;
                        }
                    }
                    path = path + homework.getHdir() + "/" + fileName;//文件路径
                    //将作业信息  文件地址  存入数据库

                    part.write(path);
                    out.println(path);
                    out.println("<br>Uploaded file name: " + fileName);
                    out.println("<br>Size:" + part.getSize() / 1024 + " KB");

                    out.println("<p>点击返回，<a href=\"html/my.html\">我的作业...</a></p>");
                }
            } else {
                String partName = part.getName();
                String filedValue = request.getParameter(partName);
                out.println("<br>" + partName + ":" + filedValue);
            }
        }

    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
