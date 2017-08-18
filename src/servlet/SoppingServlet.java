package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.CartDaoImpl;
import dao.impl.GoodsDaoImpl;
import entity.Cart;
import entity.Goods;
import pojo.User;

public class SoppingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 626539947655276848L;

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
		PrintWriter out = response.getWriter();
		String opr = request.getParameter("opr");
		if(opr!=null && !opr.equals("")){
			if(opr.equals("addToCart")){
				addToCart(request, response);
			}else if(opr.equals("gobuy")){
				gobuy(request, response);
			}
		}else{
			out.print("获取失败！点击<a href='product-view.jsp'>返回</a>");
		}
		out.flush();
		out.close();
	}
	
	public void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			out.print("<script type='text/javascript'>alert('对不起！请先登录！');location.href='login.html';</script>");
			out.flush();
			out.close();
		}else{
			Integer userId = user.getUserId();
			List<Cart> cartList = new ArrayList<Cart>();
			List<Goods> goodsList = new ArrayList<Goods>();
			cartList = new CartDaoImpl().getUnsettleCartByUserId(userId);
			if(cartList.size()==0){
				String goodsId = request.getParameter("goodsId");
				if(goodsId!=null && !goodsId.equals("")){
					try {
						Integer amount = 1;
						int id = Integer.parseInt(goodsId);
						Cart cart = new Cart(userId, id, amount);
						int num = new CartDaoImpl().save(cart);
						Goods goods = new GoodsDaoImpl().getGoodsById(id);
						//System.out.println(goods);
						if(goods != null && num>0){
							goods.setInventory(amount);
							goodsList.add(goods);
							request.setAttribute("goodsList", goodsList);
							request.getRequestDispatcher("shopping.jsp").forward(request, response);
						}else{
							out.print("获取失败！点击<a href='index.html'>返回首页</a>");
						}
					} catch (Exception e) {
						out.print("获取失败！点击<a href='index.html'>返回首页</a>");
					}
				}else{
					out.print("获取失败！点击<a href='index.html'>返回首页</a>");
				}
			}else{
				List<Integer> ids = new ArrayList<Integer>();
//				for (Cart cart: cartList) {
//					System.out.println(cart);
//				}
				for (int i = 0; i < cartList.size(); i++) {
					Cart cart = cartList.get(i);
					Integer goodsId = cart.getGoodsId();
					String goId = request.getParameter("goodsId");
					Integer id = Integer.parseInt(goId);
					Goods goods = new GoodsDaoImpl().getGoodsById(goodsId);
					if(goodsId == id){
						cart.setAmount(cart.getAmount()+1);
						new CartDaoImpl().updateCart(cart);
					}
					goods.setInventory(cart.getAmount());
					goodsList.add(goods);
					//System.out.println(goods);
					ids.add(goodsId);
				}
				String goodsId = request.getParameter("goodsId");
				Integer id = Integer.parseInt(goodsId);
				if(! ids.contains(id)){
					Cart cart = new Cart(userId, id, 1);
					new CartDaoImpl().save(cart);
					Goods go = new GoodsDaoImpl().getGoodsById(id);
					go.setInventory(1);
					goodsList.add(go);
				}
				
				request.setAttribute("goodsList", goodsList);
				request.getRequestDispatcher("shopping.jsp").forward(request, response);
			}
		}
		out.flush();
		out.close();
	}
	
	public void gobuy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			out.print("<script type='text/javascript'>alert('对不起！请先登录！');location.href='login.html';</script>");
			out.flush();
			out.close();
		}else{
			String goodsId = request.getParameter("goodsId");
			if(goodsId!=null && !goodsId.equals("")){
				try {
					int id = Integer.parseInt(goodsId);
					Goods goods = new GoodsDaoImpl().getGoodsById(id);
					//System.out.println(goods);
					if(goods != null){
						request.setAttribute("goods", goods);
						request.getRequestDispatcher("shopping.jsp").forward(request, response);
					}else{
						out.print("获取失败！点击<a href='index.html'>返回首页</a>");
					}
				} catch (Exception e) {
					out.print("获取失败！点击<a href='index.html'>返回首页</a>");
				}
			}else{
				out.print("获取失败！点击<a href='index.html'>返回首页</a>");
			}
		}
		out.flush();
		out.close();
	}
	
	
}
