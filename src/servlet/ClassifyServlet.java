package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import dao.impl.ClassifyDaoImpl;
import entity.Classify;

public class ClassifyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645691883363992755L;

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
		if(opr!=null){
			if(opr.equals("initFatherList")){
				initFatherList(request, response);
			}else if(opr.equals("initSecondList")){
				initSecondList(request, response);
			}else if(opr.equals("changeList")){
				changeList(request, response);
			}else if(opr.equals("initClassify")){
				initClassify(request, response);
			}else if(opr.equals("initNavBar")){
				initNavBar(request, response);
			}else if(opr.equals("checkFatherClassifyName")){
				checkFatherClassifyName(request, response);
			}else if(opr.equals("checkSecondClassifyName")){
				checkSecondClassifyName(request, response);
			}
		}
		out.flush();
		out.close();
	}
	
	public void initFatherList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ClassifyDaoImpl impl = new ClassifyDaoImpl();
		List<Classify> list = impl.getAllFatherClass();
		String json = JSON.toJSONString(list, true);
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void initSecondList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ClassifyDaoImpl impl = new ClassifyDaoImpl();
		List<Classify> list = impl.getTopSecondClass();
		String json = JSON.toJSONString(list, true);
		out.println(json);
		out.flush();
		out.close();
	}

	private void changeList(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ClassifyDaoImpl impl = new ClassifyDaoImpl();
		String faIdStr = request.getParameter("fatherId");
		int fatherId = Integer.parseInt(faIdStr);
		List<Classify> list = impl.getClassByFatherId(fatherId);
		String json = JSON.toJSONString(list, true);
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void checkFatherClassifyName(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		int num = new ClassifyDaoImpl().checkFatherClassifyName(name);
		out.print(num);
		out.flush();
		out.close();
	}
	
	public void checkSecondClassifyName(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String fatherIdStr = request.getParameter("fatherId");
		int fatherId = Integer.parseInt(fatherIdStr);
		int num = new ClassifyDaoImpl().checkSecondClassifyName(name, fatherId);
		out.print(num);
		out.flush();
		out.close();
	}
	
	private void initClassify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		List<Classify> fatherList = new ClassifyDaoImpl().getAllFatherClass();
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < fatherList.size(); i++) {
			Classify faClas = fatherList.get(i);
			buff.append("<dt>"+faClas.getClassifyName()+"</dt>");
			List<Classify> secondList = new ClassifyDaoImpl().getClassByFatherId(faClas.getClassifyId());
			for (int j = 0; j < secondList.size(); j++) {
				Classify seceClas = secondList.get(j);
				buff.append("<dd><a href='ProductServlet?opr=showByClassify&classifyId="+seceClas.getClassifyId()+"'>");
				buff.append(seceClas.getClassifyName()+"</a></dd>");
			}
		}
		String html = buff.toString();
		//System.out.println(html);
		out.println(html);
		out.flush();
		out.close();	
	}
	
	private void initNavBar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		List<Classify> fatherList = new ClassifyDaoImpl().getAllFatherClass();
		StringBuffer buff = new StringBuffer("<li class='current'><a href='index.html'>Ê×Ò³</a></li>");
		for (int i = 0; i < fatherList.size(); i++) {
			Classify faClas = fatherList.get(i);
			buff.append("<li><a href='#'>"+ faClas.getClassifyName() + "</a></li>");
		}
		String html = buff.toString();
		out.println(html);
		out.flush();
		out.close();	
	}

}
