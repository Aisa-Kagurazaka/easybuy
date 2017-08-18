package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.UserDaoImpl;
import pojo.User;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String opr = request.getParameter("opr");
		//System.out.println(opr);
		if(opr!=null && !opr.equals("")){
			if(opr.equals("login")){
				login(request, response);
			}else if(opr.equals("loginOrNot")){
				loginOrNot(request, response);
			} else if (opr.equals("logout")){
				logout(request, response);
			} else if(opr.equals("checkUserName")){
				checkUserName(request, response);
			} else if(opr.equals("register")){
				register(request, response);
			} else if(opr.equals("isAdmin")){
				isAdmin(request, response);
			}
		}
	}
	
	private void isAdmin(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user !=null && user.getType()==1){
			out.print("<a href='manage/index.html'>ºóÌ¨¹ÜÀí</a>");
		}else{
			out.print("");
		}
	}

	public void loginOrNot(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//System.out.println(user);
		if(user!=null){
			out.print("»¶Ó­Äú£¡"+user.getUserName()+"<a href='UserServlet?opr=logout'>×¢Ïú</a>");
		}else{
			out.print("<a href='login.html'>µÇÂ¼</a>");
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("userName");
		String pwd = request.getParameter("passWord");
		PrintWriter out = response.getWriter();
		User user = null;
		if(name!=null && pwd!= null){
			user = new UserDaoImpl().login(name, pwd);
		}
		if(user!=null){
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.setAttribute("user", user);
			response.sendRedirect("reg-result.html");
		}else{
			out.print("µÇÂ¼Ê§°Ü£¡µã»÷ <a href='login.html'>ÖØÐÂµÇÂ¼</a>");
			out.flush();
			out.close();
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			PrintWriter out = response.getWriter();
			out.print("<script type='text/javascript'>location.href='index.html';</script>");
			out.flush();
			out.close();
	}
	
	public void checkUserName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		//System.out.println(name);
		int num = new UserDaoImpl().checkUserName(name);
		//System.out.println(num);
		if(num>0){
			out.print(true);
		}else{
			out.print(false);
		}
		out.flush();
		out.close();
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("userName").trim();
		String pwd = request.getParameter("passWord").trim();
		if(name==null || name.equals("") || pwd==null || pwd.equals("")){
			out.print("<script type='text/javascript'>alert('×¢²áÊ§°Ü£¡');location.href='register.html';</script>");
		}else{
			User user = new User(name, pwd);
			int num = new UserDaoImpl().addUser(user);
			if(num>0){
				out.print("<script type='text/javascript'>alert('×¢²á³É¹¦£¡');location.href='reg-result.html';</script>");
			}else{
				out.print("<script type='text/javascript'>alert('×¢²áÊ§°Ü£¡');location.href='register.html';</script>");
			}
		}
		out.flush();
		out.close();
	}
}
