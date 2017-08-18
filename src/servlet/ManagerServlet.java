package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Tool;
import dao.impl.ClassifyDaoImpl;
import dao.impl.CommentDaoImpl;
import dao.impl.GoodsDaoImpl;
import dao.impl.NewsDaoImpl;
import entity.Classify;
import entity.Comment;
import entity.Goods;
import pojo.News;
import pojo.User;

public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 7372006125121552843L;

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
		if(opr!=null && !opr.equals("")){
			if(opr.equals("initManagerName")){
				initManagerName(request, response);
			} else if(opr.equals("logout")){
				logout(request, response);
			} else if(opr.equals("initCategoryTable")){
				initCategoryTable(request, response);
			} else if(opr.equals("classModify")){
				classModify(request, response);
			} else if(opr.equals("classModifySubmit")){
				classModifySubmit(request, response);
			} else if(opr.equals("addFatherClass")){
				addFatherClass(request, response);
			} else if(opr.equals("addSecondClass")){
				addSecondClass(request, response);
			} else if(opr.equals("initProductList")){
				initProductList(request, response);
			} else if(opr.equals("modifyGoods")){
				modifyGoods(request, response);
			} else if(opr.equals("delGoods")){
				delGoods(request, response);
			} else if(opr.equals("delComment")){
				delComment(request, response);
			} else if(opr.equals("guestbookModify")){
				guestbookModify(request, response);
			} else if(opr.equals("guestbookModifyConfirm")){
				guestbookModifyConfirm(request, response);
			} else if(opr.equals("modifyNews")){
				modifyNews(request, response);
			} else if(opr.equals("modifyNewsConfirm")){
				modifyNewsConfirm(request, response);
			} else if(opr.equals("delNews")){
				delNews(request, response);
			} 
		}else{
			out.print("获取失败！");
		}
		out.flush();
		out.close();
	}
	
	private void modifyNews(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String idStr = request.getParameter("newsId");
		int id = Integer.parseInt(idStr);
		News news = new NewsDaoImpl().getNewsById(id);
		if(news!=null){
			request.setAttribute("news", news);
			request.getRequestDispatcher("news-modify.jsp").forward(request, response);
		}else{
			out.print("<script type='text/javascript'>alert('该新闻不存在！');location.href='guestbook.html';</script>");
		}
	}
		
	private void modifyNewsConfirm(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String idStr = request.getParameter("newsId");
		String newsTitle = request.getParameter("title");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String author = user.getUserName();
		int newsId = Integer.parseInt(idStr);
		News news = new News(newsId, newsTitle, author, content);
		int num = new NewsDaoImpl().updateNews(news);
		if(num>0){
			out.print("<script type='text/javascript'>alert('修改新闻成功！');location.href='news.html';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('修改新闻失败！');location.href='news.html';</script>");
		}
	}
	
	private void guestbookModify(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Comment comment = new CommentDaoImpl().getCommentById(id);
		if(comment!=null){
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("guestbook-modify.jsp").forward(request, response);
		}else{
			out.print("<script type='text/javascript'>alert('该留言不存在！');location.href='guestbook.html';</script>");
		}
	}
	
	private void guestbookModifyConfirm(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String idStr = request.getParameter("orderId");
		String author = request.getParameter("name");
		String content = request.getParameter("replyContent");
		int commentId = Integer.parseInt(idStr);
		Date date = new Date();
		String time = Tool.dateToString(date, "yyyy-MM-dd HH:mm:ss");
		Comment comment = new Comment(commentId, content, author, time);
		int num = new CommentDaoImpl().update(comment);
		if(num>0){
			out.print("<script type='text/javascript'>alert('修改留言成功！');location.href='guestbook.html';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('修改留言失败！');location.href='guestbook.html';</script>");
		}
	}

	private void delGoods(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String idStr = request.getParameter("goodsId");
		int id = Integer.parseInt(idStr);
		int num = new GoodsDaoImpl().del(id);
		if(num>0){
			out.print("<script type='text/javascript'>alert('删除产品成功');location.href='manage-result.html';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('删除产品失败');location.href='product.html';</script>");
		}
	}
	
	private void delComment(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		int num = new CommentDaoImpl().del(id);
		if(num>0){
			out.print("<script type='text/javascript'>alert('删除留言成功');location.href='manage-result.html';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('删除留言失败');location.href='product.html';</script>");
		}
	}
	
	private void delNews(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		int num = new NewsDaoImpl().delNews(id);
		if(num>0){
			out.print("<script type='text/javascript'>alert('删除新闻成功');location.href='manage-result.html';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('删除新闻失败');location.href='news.html';</script>");
		}
	}

	private void modifyGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("goodsId");
		int googsId = Integer.parseInt(idStr);
		Goods goods = new GoodsDaoImpl().getGoodsById(googsId);
		List<Classify> fatherClassify = new ClassifyDaoImpl().getAllFatherClass();
		int classifyId = goods.getClassifyId();
		Classify classify = new ClassifyDaoImpl().getClassifyById(classifyId);
		int fatherId = classify.getFatherId();
		List<Classify> secondClassify = new ClassifyDaoImpl().getClassByFatherId(fatherId);
		request.setAttribute("classifyId", classifyId);
		request.setAttribute("fatherId", fatherId);
		request.setAttribute("fatherClassify", fatherClassify);
		request.setAttribute("secondClassify", secondClassify);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("product-modify.jsp").forward(request, response);
	}

	private void initProductList(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		List<Goods> list = new GoodsDaoImpl().getByPage(1);
		StringBuffer strBuf = new StringBuffer("<tr><th>ID</th><th>商品名称</th><th>操作</th></tr>");
		for (int i = 0; i < list.size(); i++) {
			Goods go = list.get(i);
			int id = go.getGoodsId();
			strBuf.append("<tr><td class='first w4 c'>").append(id).append("</td>");
			strBuf.append("<td class='thumb'><img src='../images/product/").append(go.getPath());
			strBuf.append("' width='50px'/><a href='../ProductViewServlet?goodsId=");
			strBuf.append(id).append("' target='_blank'>").append(go.getGoodsName());
			strBuf.append("</a></td><td class='w1 c'><a href='ManagerServlet?opr=modifyGoods&goodsId=");
			strBuf.append(id).append("'>修改</a> <a href='javascript:DeleteGoods(");
			strBuf.append(id).append(");'>删除</a></td></tr>");
		}
		out.print(strBuf.toString());
	}

	public void initManagerName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user!=null)
			out.print(user.getUserName()+"<a href='ManagerServlet?opr=logout'>注销</a>");
		out.flush();
		out.close();
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		response.sendRedirect("../index.html");
	}
	
	public void classModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("classifyId");
		try {
			int id = Integer.parseInt(idStr);
			List<Classify> faList = new ClassifyDaoImpl().getAllFatherClass();
			Classify classify = new ClassifyDaoImpl().getClassifyById(id);
			request.setAttribute("fatherClassify", faList);
			request.setAttribute("classify", classify);
			request.getRequestDispatcher("productClass-modify.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("index.html");
		}
	}
	
	public void classModifySubmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String idStr = request.getParameter("classifyId");
		String parentId = request.getParameter("parentId");
		String className = request.getParameter("className");
//		System.out.println("classifyId: "+idStr);
//		System.out.println("parentId: "+parentId);
//		System.out.println("className: "+className);
		try {
			int id = Integer.parseInt(idStr);
			int fatherId = Integer.parseInt(parentId);
			Classify classify = new ClassifyDaoImpl().getClassifyById(id);
			classify.setFatherId(fatherId);
			classify.setClassifyName(className);
			int num = new ClassifyDaoImpl().updateClassify(classify);
			if(num>0){
				out.print("<script type='text/javascript'>alert('更新成功');location.href='manage-result.html';</script>");
			}else{
				out.print("<script type='text/javascript'>alert('更新失败');location.href='product-modify.html';</script>");
			}
		} catch (Exception e) {
			response.sendRedirect("index.html");
		}
	}
	
	public void initCategoryTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuffer strBuf = new StringBuffer("<tr><th>ID</th>=<th>分类名称</th>=<th>操作</th>=</tr>");
		List<Classify> faList = new ClassifyDaoImpl().getAllFatherClass();
		for (int i = 0; i < faList.size(); i++) {
			Classify faClas = faList.get(i);
			strBuf.append("<tr><td class='first w4 c'>").append(faClas.getClassifyId());
			strBuf.append("</td><td>").append(faClas.getClassifyName()).append("</td>");
			strBuf.append("<td class='w1 c'><a href='ManagerServlet?opr=classModify&classifyId=");
			strBuf.append(faClas.getClassifyId()).append("'>修改</a> <a href='javascript:Delete(");
			strBuf.append(faClas.getClassifyId()).append(");'>删除</a></td></tr>");
			List<Classify> chList = new ClassifyDaoImpl().getClassByFatherId(faClas.getClassifyId());
			for (int j = 0; j < chList.size(); j++) {
				Classify chClas = chList.get(j);
				strBuf.append("<tr><td class='first w4 c'>").append(chClas.getClassifyId()).append("</td>");
				strBuf.append("<td class='childClass'>").append(chClas.getClassifyName()).append("</td><td class='w1 c'>");
				strBuf.append("<a href='ManagerServlet?opr=classModify&classifyId=").append(chClas.getClassifyId());
				strBuf.append("'>修改</a> <a href='javascript:Delete(").append(chClas.getClassifyId()).append(");'>删除</a></td></tr>");	
			}
		}
		out.print(strBuf.toString());
		out.flush();
		out.close();
	}
	
	public void addFatherClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("className");
		String fail = "<script type='text/javascript'>alert('添加分类失败');location.href='productClass-add.html';</script>";
		if(name!=null && !name.equals("")){
			int num = new ClassifyDaoImpl().addFatherClassify(name);
			if(num>0){
				out.print("<script type='text/javascript'>alert('添加分类成功');location.href='manage-result.html';</script>");
			}else{
				out.print(fail);
			}
		}else{
			out.print(fail);
		}
	}
	
	public void addSecondClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("className");
		String idStr = request.getParameter("parentId");
		String success = "<script type='text/javascript'>alert('添加分类成功');location.href='manage-result.html';</script>";
		String fail = "<script type='text/javascript'>alert('添加分类失败');location.href='productClass-add.html';</script>";
		try {
			int id = Integer.parseInt(idStr);
			if(name!=null && !name.equals("")){
				int num = new ClassifyDaoImpl().addSecondClassify(name, id);
				if(num>0){
					out.print(success);
				}else{
					out.print(fail);
				}
			}else{
				out.print(fail);
			}
		} catch (Exception e) {
			out.print(fail);
		}
	}

}
