package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.OrderDaoImpl;
import pojo.Order;
import pojo.User;

public class OrderSevlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String opr = request.getParameter("opr");
		if(opr!=null && !opr.equals("")){
			if(opr.equals("add")){
				addOrder(request, response);
			}
		}else{
			out.print("获取失败，点击<a href='shopping.jsp'>返回</a>");
		}
		out.flush();
		out.close();
	}

	public void addOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		User user = (User)session.getAttribute("user");
		//System.out.println(user);
		if(user == null){
			out.print("<script type='text/javascript'>alert('请不起！请先登录！');location.href='login.html'</script>");
			out.flush();
			out.close();
		}else{
			int userId = user.getUserId();
			String goodsIdStr = request.getParameter("goodsId");
			String amountStr = request.getParameter("amount");
			String totalStr = request.getParameter("total");
//			System.out.println("userId:"+userId);
//			System.out.println("goodsIdStr:"+goodsIdStr);
//			System.out.println("amountStr:"+amountStr);
//			System.out.println("totalStr:"+totalStr);
			int goodsId = Integer.parseInt(goodsIdStr);
			int amount = Integer.parseInt(amountStr);
			double total = Double.parseDouble(totalStr);
			Order order = new Order(userId, goodsId, amount, total);
			int num = new OrderDaoImpl().addOrder(order);
			if(num>0){
				out.print("<script type='text/javascript'>alert('下单成功！');location.href='shopping-result.html'</script>");
			}else{
				out.print("<script type='text/javascript'>alert('下单失败！');location.href='SoppingServlet?opr=addToCart&goodsId="+goodsId+"'</script>");
			}
			out.flush();
			out.close();
		}
	}
}
