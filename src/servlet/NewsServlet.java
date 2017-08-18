package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Tool;

import com.alibaba.fastjson.JSON;

import dao.impl.NewsDaoImpl;
import pojo.News;

public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = -8545873648784414205L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String opr = request.getParameter("opr");
		//System.out.println(opr);
		if(opr!=null && !opr.equals("")){
			if(opr.equals("initNewsList")){
				initNewsList(request, response);
			} else if(opr.equals("viewNews")){
				viewNews(request, response);
			} else if(opr.equals("getNewsJsonByPage")){
				getNewsJsonByPage(request, response);
			} else if(opr.equals("getTotalNews")){
				out.print(new NewsDaoImpl().getTotalPage(10));
			}
		}else{
			out.print("获取失败！");
		}
		out.flush();
		out.close();
	}
	
	private void getNewsJsonByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");
		int page = Integer.parseInt(pageStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		int totalPage = new NewsDaoImpl().getTotalPage(pageSize);
		if(page<1) page = 1;
		if(page>totalPage) page = totalPage;
		List<News> list = new NewsDaoImpl().getByPage(page, pageSize);
		String json = JSON.toJSONString(list);
		out.print(json);
	}

	public void initNewsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<News> list = new NewsDaoImpl().getSeveralLatestNews(7);
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			News news = list.get(i);
			strBuf.append("<li><a href='NewsServlet?opr=viewNews&newsId=");
			strBuf.append(news.getNewsId()).append("' target='_blank'>");
			strBuf.append(Tool.subStringOfNChar(news.getNewsTitle(), 16)).append("</a></li>");
		}
		String str = strBuf.toString();
		//System.out.println(str);
		out.print(str);
		out.flush();
		out.close();
	}
	
	public void viewNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String newsIdStr = request.getParameter("newsId");
		try {
			int newsId = Integer.parseInt(newsIdStr);
			News news = new NewsDaoImpl().getNewsById(newsId);
			if(news == null){
				out.print("<script type='text/javascript'>alert('获取新闻失败！');location.href='index.html';</script>");
			}else{
				request.setAttribute("news", news);
				request.getRequestDispatcher("news-view.jsp").forward(request, response);
			}
		} catch (Exception e) {
			out.print("<script type='text/javascript'>alert('获取新闻失败！');location.href='index.html';</script>");
		}
		out.flush();
		out.close();
	}

}
