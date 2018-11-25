package com.zt.sr.service;

import com.zt.sr.dao.homeworkDao;
import com.zt.sr.pojo.Homework;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Servlet implementation class DownLoadDialog
 */
@WebServlet(name = "com.zt.sr.service.downloadZipServlet", urlPatterns = "/downloadZipServlet")
public class downloadZipServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<File> fileList = new ArrayList<>();
        homeworkDao hd = new homeworkDao();
        String hno = request.getParameter("hno");
        Homework homework = hd.select(hno);
        String path = getServletContext().getRealPath("/WEB-INF/FILE");
        if (homework.getHdir().substring(0, 1).equals("/")) {
            path = path + homework.getHdir();//文件路径
        } else {
            path = path + "/" + homework.getHdir();//文件路径
        }
        File file = new File(path);
        System.out.println(path);
        if (file.isDirectory()) {
            System.out.println(path);
            File file2[] = file.listFiles();
            if (file2 != null && file2.length > 0) {
                fileList.addAll(Arrays.asList(file2));
            }
            System.out.println(fileList.get(0));
        }
        /* 压缩方法  */
        File zipFile = new File(hno+".zip");
        //toZip(fileList, fos2);
        long start = System.currentTimeMillis();
        response.setContentType("application/zip");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(zipFile.getName(), "UTF-8"));
        ServletOutputStream fos2 = response.getOutputStream();
        ZipOutputStream fos = null;
        try {
            fos = new ZipOutputStream(fos2);
            for (File srcFile : fileList) {
                byte[] buf = new byte[2 * 1024];
                fos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                System.out.println("下载结束" + srcFile.getPath());
                fos.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!file.exists()) {
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
