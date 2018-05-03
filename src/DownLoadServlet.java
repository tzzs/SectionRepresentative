import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * Servlet implementation class DownLoadServlet
 */
@WebServlet(name = "download")// urlPatterns = "/download"
public class DownLoadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        String fileName = request.getParameter("filename");

        String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        System.out.println("nsdop" + fileSaveRootPath);
        //通过文件名找出文件的所在目录
        //String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        File file = new File(fileSaveRootPath + "\\" + fileName);
        System.out.println(file);
        //如果文件不存在
        if (!file.exists()) {
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("message.jsp").forward(request, response);
            return;
        }
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(file);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
    }

    public String findFileSavePathByFileName(String filename, String saveRootPath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf; //0--15
        int dir2 = (hashcode & 0xf0) >> 4; //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2; //upload\2\3 upload\3\5
        File file = new File(dir);
        if (!file.exists()) {
            //创建目录
            file.mkdirs();
        }
        return dir;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
