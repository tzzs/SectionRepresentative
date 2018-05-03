package cqut;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListFileServlet
 */
@WebServlet("/listfileservelt")
public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String uploadFilePath=this.getServletContext().getRealPath("/WEB-INF/upload");
		System.out.println(uploadFilePath);
		if(uploadFilePath!=null){
		}
		//存储要下载的文件名
		Map<String,String> fileNameMap = (Map<String, String>) new HashMap<String,String>();
		if(listFile(new File(uploadFilePath), fileNameMap)==0){
			request.setAttribute("message", "您要下载的资源已被删除！！");
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}else{
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("listShow.jsp").forward(request, response);
		return;
		}
	}
	private static int listFile(File file,Map<String,String>map){
		
		// 如果文件代表的是一个目录而不是文件
		if(!file.isFile()){
			File files[]=file.listFiles();
			System.out.println(files);
			if(files!=null&&files.length>0){
				System.out.println("jodr[wajeg-"+files.length);
				for(File f: files){
					
					listFile(f, map);
				}
			}else{
				return 0;
			}

		}else{
			/**
			* 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
			file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
			那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
			*/
			String realName = file.getName();
			System.out.println("wpmrkg"+realName);
			//file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
			map.put(file.getName(), realName);
			return 1;
			}
		return 1;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
