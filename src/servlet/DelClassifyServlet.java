package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.ClassifyDaoImpl;
import entity.Classify;

public class DelClassifyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String success = "<script type='text/javascript'>alert('删除分类成功！');location.href='productClass.html';</script>";
	private String fail = "<script type='text/javascript'>alert('删除分类失败！');location.href='productClass.html';</script>";

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
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Classify classify = new ClassifyDaoImpl().getClassifyById(id);
		int fatherId = classify.getFatherId();
		if(fatherId==0){
			boolean flag = new ClassifyDaoImpl().isFatherClassifyHaveSubclassify(id);
			if(flag){
				out.print("<script type='text/javascript'>alert('该父分类下还有子分类，不能删除！');location.href='productClass.html';</script>");
			}else{
				int num = new ClassifyDaoImpl().delClassify(id);
				if(num>0){
					out.print(success);
				}else{
					out.print(fail);
				}
			}
		}else{
			boolean flag = new ClassifyDaoImpl().isClassifyContainsGoods(id);
			if(flag){
				out.print("<script type='text/javascript'>alert('该分类下还有商品，不能删除！');location.href='productClass.html';</script>");
			}else{
				int num = new ClassifyDaoImpl().delClassify(id);
				if(num>0){
					out.print(success);
				}else{
					out.print(fail);
				}
			}
		}
	}

}
