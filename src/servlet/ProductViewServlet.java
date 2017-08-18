package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Tool;
import dao.impl.GoodsDaoImpl;
import entity.Goods;

public class ProductViewServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		String goodsIdStr = request.getParameter("goodsId");
		if(goodsIdStr!=null){
			int goodsId = Integer.parseInt(goodsIdStr);
			Goods goods = new GoodsDaoImpl().getGoodsById(goodsId);
			Cookie[] cookies  = request.getCookies();
			if(cookies!=null){
				Cookie newCookie = null;
				for (int i = 0; i < cookies.length; i++) {
					String name = cookies[i].getName().trim();
					String value = cookies[i].getValue().trim();
//					System.out.println("cookie: "+(i+1)+" \tcookieName: "+name+" \tcookieValue: "+value);
					if(name.equals("goodsId")){
						String[] ids = value.split(",");
//						for (int j = 0; j < ids.length; j++) {
//							System.out.println(ids[j]);
//						}
						String val = goodsId+","+value;
						if(Tool.isArrayContainsElem(ids, goodsIdStr)){
//							System.out.println("yes");
							val = val.replace((","+goodsIdStr), "");
						}
						newCookie = new Cookie("goodsId", val);
						break;
					}else{
						newCookie = new Cookie("goodsId",Integer.toString(goodsId));
					}
				}
				newCookie.setMaxAge(5*24*60*60);
				response.addCookie(newCookie);
			}else{
				Cookie cookie = new Cookie("goodsId",Integer.toString(goodsId));
				cookie.setMaxAge(5*24*60*60);
				response.addCookie(cookie);
			}
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("product-view.jsp").forward(request, response);
		}else{
			out.print("»ñÈ¡Ê§°Ü£¡");
		}
		out.flush();
		out.close();
	}

}
