package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.Tool;
import dao.impl.GoodsDaoImpl;
import entity.Goods;

public class ProductServlet extends HttpServlet {

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
			if(opr.equals("add")){
				addProduct(request, response);
			}else if(opr.equals("showByClassify")){
				showByClassify(request, response);
			}else if(opr.equals("change")){
				changePage(request, response);
			} else if(opr.equals("initOffList")){
				initOffList(request, response);
			} else if(opr.equals("initHotList")){
				initHotList(request, response);
			}else if(opr.equals("initViewHistory")){
				initViewHistory(request, response);
			}else if(opr.equals("modify")){
				modify(request, response);
			} 
		}
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String goodsIdStr = request.getParameter("goodsId");
		int goodsId = Integer.parseInt(goodsIdStr);
		String goodsName = request.getParameter("productName");
		String classifyIdStr = request.getParameter("secondId");
		int classifyId = Integer.parseInt(classifyIdStr);
		String priceStr = request.getParameter("productPrice");
		double price = Double.parseDouble(priceStr);
		String brand = request.getParameter("productBrand");
		String inventoryStr = request.getParameter("productInventory");
		int inventory = Integer.parseInt(inventoryStr);
		String describe = request.getParameter("productDescribe");
		Goods goods = new Goods(goodsId, goodsName, classifyId, price, brand, inventory, describe);
		int num = new GoodsDaoImpl().updateButNotUpdatePic(goods);
		if(num>0){
			out.print("<script type='text/javascript'>location.href='manage/manage-result.html;'</script>");
		}else{
			out.print("修改失败！");
		}
	}

	public void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//上传文件的存储路径
		String upPath = request.getSession().getServletContext().getRealPath("/images/product/");
		//System.out.println(upPath);
		//临时文件目录路径
		String tempPath = request.getSession().getServletContext().getRealPath("/images/temp/");
		//临时文件目录
		File tempFile = new File(tempPath);
		//1.请求信息中的内容是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		boolean isUploadSucce = false;
		String goodsName = null;
		int classifyId = 0;
		double price = 0.0;
		String brand = null;
		String path = null;
		int inventory = 0;
		String describe = null;
		if(isMultipart){
	  		//2.得到文件选项工厂类
	  		DiskFileItemFactory factory = new DiskFileItemFactory();
	  		factory.setSizeThreshold(4096);  //设置缓冲区大小为4KB
	  		//设置上传文件的临时存放路径
	  		factory.setRepository(tempFile);
	 		//3.得到一个文件上传类
	 		ServletFileUpload upload = new ServletFileUpload(factory);
	 		//设置允许最大文件大小1MB(1*1024**1024 bytes)
	 		upload.setSizeMax(1*1024*1024);
	 		try{
	 			//4.得到表单是所有上传的文件
		 		@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(request);
				//5.循环取上传的文件
				Iterator<FileItem> its = items.iterator();
				while(its.hasNext()){
					FileItem item = its.next();
		 			//6.判断上传的文件是否为普通表单还是文件对象
					if(item.isFormField()){ //如果是普通的表单字段
						String nameAttr = item.getFieldName(); //获得表单字段的name属性值
						//得到表单字段的value值
						if("productName".equals(nameAttr)) goodsName = item.getString("UTF-8");
						if("secondId".equals(nameAttr)){
							//System.out.println(item.getString("UTF-8"));
							classifyId = Integer.parseInt(item.getString("UTF-8"));
						}
						if("productPrice".equals(nameAttr)) price = Double.parseDouble(item.getString("UTF-8"));
						if("productBrand".equals(nameAttr)) brand = item.getString("UTF-8");
						if("productInventory".equals(nameAttr)) inventory = Integer.parseInt(item.getString("UTF-8"));
						if("productDescribe".equals(nameAttr)) describe = item.getString("UTF-8");
					}else{  //如果是文件表单字段
						String fileName = item.getName(); //获取文件名
						List<String> fileTypes = Arrays.asList("gif","bmp","jpg","png");
						//获取文件后缀名
						String ext = fileName.substring(fileName.lastIndexOf(".")+1);
						if(fileTypes.contains(ext)){ //根据后缀名判断是否在允许范围内
							if(fileName!=null && !fileName.equals("")){
								//创建和保存文件
								File fullFile = new File(item.getName());
								File saveFile = new File(upPath,fullFile.getName());
								path = fullFile.getName();
								item.write(saveFile);
								isUploadSucce = true;
								out.print("上传成功，文件名："+fullFile.getName()+"，文件大小是："
									+item.getSize()+" bytes = "+(item.getSize())/1024+"KB!");
							}
						}else{
							out.print("上传失败，只能上传gif,bmp,jpg和png格式的图片");
						}
					}
				}
	 		}catch(FileUploadBase.SizeLimitExceededException ex){
	 			out.print("上传失败，文件太大，全部文件的最大限制是："+upload.getSizeMax()+" bytes = "
	 				+(upload.getSizeMax())/1024+"KB = " +(upload.getSizeMax())/(1024*1024)+"MB");
	 		}catch(Exception e){
	 			out.print("未知异常，上传失败！");
	 			e.printStackTrace();
	 		}
	 		if(isUploadSucce){
	 			Goods goods = new Goods(goodsName, classifyId, price, brand, path, inventory, describe);
	 			int num = new GoodsDaoImpl().addGoods(goods);
	 			if(num>0){
	 				out.print("<script type='text/javascript'>location.href='manage/manage-result.html;'</script>");
	 			}else{
	 				out.print("保存失败！");
	 			}
	 		}
	}else{
		out.print("表单内容不是multipart类型");
	}
		out.flush();
		out.close();
	}
	
	public void showByClassify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String classifyIdStr = request.getParameter("classifyId");
		if(classifyIdStr != null){
			int classifyId = Integer.parseInt(classifyIdStr);
			request.setAttribute("classifyId", classifyId);
			List<Goods> list = new GoodsDaoImpl().getByClassifyIdAndPage(classifyId, 1);
			request.setAttribute("goodsList", list);
			int totalPage = new GoodsDaoImpl().getTotalPagesByClassifyId(classifyId);
			request.setAttribute("totalPage", totalPage);
			int currentPage = 1;
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("product-list.jsp").forward(request, response);
		}else{
			out.print("提交失败！");
		}
		out.flush();
		out.close();
	}
	
	public void changePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String classifyIdStr = request.getParameter("classifyId");
		String pageStr = request.getParameter("page");
		if(classifyIdStr != null && pageStr!=null){
			int classifyId = Integer.parseInt(classifyIdStr);
			request.setAttribute("classifyId", classifyId);
			int page = Integer.parseInt(pageStr);
			int totalPage = new GoodsDaoImpl().getTotalPagesByClassifyId(classifyId);
			if(page<1){
				page = 1;
			}
			if(page>totalPage){
				page = totalPage;
			}
			List<Goods> list = new GoodsDaoImpl().getByClassifyIdAndPage(classifyId,page);
			request.setAttribute("goodsList", list);
			request.setAttribute("totalPage", totalPage);
			int currentPage = page;
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("product-list.jsp").forward(request, response);
		}else{
			out.print("提交失败！");
		}
		out.flush();
		out.close();
	}
	
	public void initOffList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Goods> list = new GoodsDaoImpl().getTop8OffGoods();
		list = Tool.goodsListToRandom(list);
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Goods go = list.get(i);
			strBuf.append("<li><dl><dt><a href='ProductViewServlet?goodsId="+go.getGoodsId()+"'");
			strBuf.append(" target='_blank'><img src='images/product/"+go.getPath()+"' /></a>");
			strBuf.append("</dt><dd class='title'><a href='ProductViewServlet?goodsId="+go.getGoodsId()+"'");
			strBuf.append("target='_blank'>"+go.getGoodsName()+"</a></dd><dd class='price'>");
			strBuf.append("￥"+go.getPrice()+"</dd></dl></li>");
		}
		String goodsList = strBuf.toString();
		out.print(goodsList);
		out.flush();
		out.close();
	}
	
	public void initHotList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Goods> list = new GoodsDaoImpl().getTop12HotGoods();
		list = Tool.goodsListToRandom(list);
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Goods go = list.get(i);
			strBuf.append("<li><dl><dt><a href='ProductViewServlet?goodsId="+go.getGoodsId()+"'");
			strBuf.append(" target='_blank'><img src='images/product/"+go.getPath()+"' /></a>");
			strBuf.append("</dt><dd class='title'><a href='ProductViewServlet?goodsId="+go.getGoodsId()+"'");
			strBuf.append("target='_blank'>"+go.getGoodsName()+"</a></dd><dd class='price'>");
			strBuf.append("￥"+go.getPrice()+"</dd></dl></li>");
		}
		String goodsList = strBuf.toString();
		out.print(goodsList);
		out.flush();
		out.close();
	}
	
	public void initViewHistory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Goods> list = new ArrayList<Goods>();
		StringBuffer strBuf = new StringBuffer();
		Cookie[] cookies = request.getCookies();
		boolean haveHistory = false;
		if(cookies != null){
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName().trim();
				String value = cookies[i].getValue().trim();
				if(name.equals("goodsId")){
					haveHistory = true;
					String[] ids = value.split(",");
					for (int j = 0; j < ids.length; j++) {
						String idStr = ids[j].trim();
						int id = Integer.parseInt(idStr);
						Goods goods = new GoodsDaoImpl().getGoodsById(id);
						/* 如果产品已经从数据库中删除，但是cookie中还有该产品的记录，则查询结果为null
						 * 如果查询结果为null，则跳过
						 */
						if(goods == null){
							j--;
							continue;
						}
						list.add(goods);
						if(j>=3){
							break;
						}
					}
				}
			}
		}
		if(haveHistory){
			for (int i = 0; i < list.size(); i++) {
				Goods go = list.get(i);
				strBuf.append("<dt><img width='55px' src='images/product/").append(go.getPath()).append("' /></dt>");
				strBuf.append("<dd><a href='ProductViewServlet?goodsId=").append(go.getGoodsId()).append("'>");
				strBuf.append(Tool.subStringOf20(go.getGoodsName())).append("</a></dd>");
			}
			String goodsList = strBuf.toString();
			out.print(goodsList);
		}else{
			strBuf.append("没有可显示的历史记录");
			out.print(strBuf.toString());
		}
		out.flush();
		out.close();
	}
}
