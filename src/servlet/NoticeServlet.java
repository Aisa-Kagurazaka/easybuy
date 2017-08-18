package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.NoticeDaoImpl;
import pojo.Notice;

public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = -5511436264747022245L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String opr = request.getParameter("opr");
		if(opr!=null && !opr.equals("")){
			if(opr.equals("initNoticeList")){
				initNoticeList(request, response);
			} else if (opr.equals("viewNotice")){
				viewNotice(request, response);
			}
		}else{
			out.print("获取失败！");
		}
		out.flush();
		out.close();
	}
	
	public void initNoticeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuffer strBuf = new StringBuffer();
		//获取最新7条公告
		List<Notice> list = new NoticeDaoImpl().getSeveralLatestNotice(7);
		for (int i = 0; i < list.size(); i++) {
			Notice notice = list.get(i);
			strBuf.append("<li><a href='NoticeServlet?opr=viewNotice&noticeId=");
			strBuf.append(notice.getNoticeId()).append("' target='_blank'>");
			strBuf.append(notice.getTitle()).append("</a></li>");
		}
		String str = strBuf.toString();
		out.print(str);
		out.flush();
		out.close();
	}
	
	public void viewNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String noticeIdStr = request.getParameter("noticeId");
		try {
			int noticeId = Integer.parseInt(noticeIdStr);
			Notice notice = new NoticeDaoImpl().getNoticeById(noticeId);
			if(notice == null){
				out.print("<script type='text/javascript'>alert('获取公告失败！');location.href='index.html';</script>");
			}else{
				request.setAttribute("notice", notice);
				request.getRequestDispatcher("news-view.jsp").forward(request, response);
			}
		} catch (Exception e) {
			out.print("<script type='text/javascript'>alert('获取公告失败！');location.href='index.html';</script>");
		}
		out.flush();
		out.close();
	}
}
