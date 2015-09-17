package com.dong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dong.main.Menu;
import com.dong.main.update;
import com.dong.util.SqlString;

public class UpdateMenuServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;  
    // ���췽��  
    public UpdateMenuServlet() {  
        super();  
    }  
    // ���ٷ���  
    @Override
	public void destroy() {  
        super.destroy();  
    }  
    
 // ��ӦGet����  
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	    	
    	String sql=request.getParameter("key");
    	String sql1=request.getParameter("key1");
    	
        response.setContentType("text/xml");  
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();  
        // ʵ����  
        update dao = new update();
        dao.setSql(new SqlString().getSql(sql,sql1));
        // ��ȡ�γ���Ϣ  
        List<Menu> list = dao.getMenuList();  
          
        // ƴXML��ʽ����  
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");  
        // ���ڵ�  
        out.println("<courselist>");  
            for (int i = 0; i <list.size(); i++) {  
                Menu m = list.get(i);  
                out.println("<course>");  
                    // �γ�id  
                    out.print("<courseId>");  
                        out.print(m.getCourseId());  
                    out.println("</courseId>");  
                    // �γ����� 
                    out.print("<courseName>");  
                        out.print(m.getCourseName());  
                    out.println("</courseName>");  
                    // �γ̺� 
                    out.print("<courseNum>");  
                        out.print(m.getCourseNum());  
                    out.println("</courseNum>");  
                    // ��ʦ 
                    out.print("<teacherName>");  
                        out.print(m.getTeacherName());  
                    out.println("</teacherName>");  
                    // �ļ��ڿ� 
                    out.print("<courseTime>");  
                        out.print(m.getCourseTime());  
                    out.println("</courseTime>");  
                    // �ܼ��Ͽ� 
                    out.print("<week>");  
                        out.print(m.getWeek());  
                    out.println("</week>");  
                    //�ļ����Ͽ� 
                    out.print("<weekNum>");  
                        out.print(m.getWeekNum());  
                    out.println("</weekNum>"); 
                    //�Ͽεص�
                    out.print("<coursePlace>");  
                    out.print(m.getCoursePlace());  
                    out.println("</coursePlace>"); 
                out.println("</course>");  
            }  
        out.println("</courselist>");  
        out.flush();  
        out.close();  
    }  
    // ��ӦPost����  
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request,response);  
    }  
    // ��ʼ������  
    @Override
	public void init() throws ServletException {  
    }  
}  

