import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.List;

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

        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            if (part.getContentType() != null) {
                String fileName = getFilename(part);
                if (fileName != null && !fileName.isEmpty()) {
                    String path = getServletContext().getRealPath("/WEB-INF") + "/FILE";//文件夹路径
                    File filePath = new File(path);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    path = path + "/" + fileName;//文件路径
                    part.write(path);
                    out.println(path);
                    out.println("<br>Uploaded file name: " + fileName);
                    out.println("<br>Size:" + part.getSize() / 1024 + " KB");
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
