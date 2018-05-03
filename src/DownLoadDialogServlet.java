import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownLoadDialog
 */
@WebServlet("/updowndialog")
public class DownLoadDialogServlet extends HttpServlet {
	private int i=0;
	private static final long serialVersionUID = 1L;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadDialogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<File> fileList = new ArrayList<>();
		homeworkDao homework=new homeworkDao();
		String s1=request.getParameter("hno");
		String string=homework.selectHno(s1);
		String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
		File file=new File(fileSaveRootPath+"/"+string);
		if(file.isDirectory()){
			System.out.println("spd,v[a");
			File file2[]=file.listFiles();
			if(file2!=null&&file2.length>0){
				for(File f:file2){
					fileList.add(f);
				}
			}
		}
		/** 压缩方法  */
		File zipFile=new File("mytest02.zip");
	   //toZip(fileList, fos2);
		long start = System.currentTimeMillis();
		response.setContentType("application/zip");
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(zipFile.getName(), "UTF-8"));
		ServletOutputStream fos2 = response.getOutputStream();
		ZipOutputStream fos = null;
		try {
			fos=new ZipOutputStream(fos2);
			for(File srcFile:fileList){
				byte[]buf=new byte[2*1024];
				fos.putNextEntry(new ZipEntry(srcFile.getName()));
				int len;
				FileInputStream in = new FileInputStream(srcFile);
				while ((len = in.read(buf)) != -1){
					fos.write(buf, 0, len);
				}
				System.out.println("下载结束"+fos);
				fos.closeEntry();
				in.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("zip error from ZipUtils",e);
		}
		finally{
			if(fos != null){
				try {
					fos.close();
					fos2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(!file.exists()){
			request.setAttribute("message", "您要下载的资源已被删除！！");
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
			}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
