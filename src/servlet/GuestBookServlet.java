package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import dao.impl.CommentDaoImpl;
import entity.Comment;

public class GuestBookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5655676533104623943L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String opr = request.getParameter("opr");
		//System.out.println(opr);
		if(opr!=null && opr.equals("initShow")){
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("pageSize");
			int page = Integer.parseInt(pageStr);
			int pageSize = Integer.parseInt(pageSizeStr);
			List<Comment> list = new CommentDaoImpl().getByPage(page, pageSize);
			String listJson = JSON.toJSONString(list);
			//System.out.println(listJson);
			out.println(listJson);
		}else if(opr!=null && opr.equals("initPage")){
			String pageSizeStr = request.getParameter("pageSize");
			int pageSize = Integer.parseInt(pageSizeStr);
			int totalPages = new CommentDaoImpl().getTotalPage(pageSize);
			out.println(totalPages);
		} else if(opr!=null && opr.equals("change")){
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("pageSize");
			int page = Integer.parseInt(pageStr);
			int pageSize = Integer.parseInt(pageSizeStr);
			List<Comment> list = new CommentDaoImpl().getByPage(page, pageSize);
			String json = JSON.toJSONString(list);
			//System.out.println(json);
			out.println(json);
		} else if(opr!=null && opr.equals("getTotal")){
			String pageSizeStr = request.getParameter("pageSize");
			int pageSize = Integer.parseInt(pageSizeStr);
			int totalPages = new CommentDaoImpl().getTotalPage(pageSize);
			//System.out.println(totalPages);
			out.println(totalPages);
		} else if(opr!=null && opr.equals("insert")){
			int num = 0;
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String author = request.getParameter("author");
			//System.out.println(title+"  "+ content+"  "+ author);
			if(title!=null && content!=null && author!=null){
				Comment comm = new Comment(title, content, author);
				num = new CommentDaoImpl().save(comm);
			}
			//System.out.println(num);
			out.println(num);
		}
		out.flush();
		out.close();
	}

}
