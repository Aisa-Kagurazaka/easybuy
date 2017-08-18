<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
<script type="text/javascript" src="../scripts/jquery-2.2.4.js"></script>
<script type="text/javascript" src="../scripts/manage.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#fatherClass>select").change(function(){
		var $selectedFather = $(this).find("option:selected");
		var fatherId = $selectedFather.val();
		$.getJSON({
			"url"	:	"../ClassifyServlet",
			"data"	:	"opr=changeList&fatherId="+fatherId,
			"success"	:	changeSecListSucc
		});
		function changeSecListSucc(data){
			var $fatherList = $("#secondClass>select").empty();
			for(var i=0; i<data.length; i++){
				$fatherList.append("<option id='"+data[i].classifyId+"' value='"+data[i].classifyId
						+"' fathertId='"+data[i].fatherId+"'>"+data[i].classifyName+"</option>");
			}
		}
		
	});
});
</script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.gif" /></div>
	<div class="help"><a href="../index.html">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.html">首页</a></li>
			<li><a href="user.html">用户</a></li>
			<li class="current"><a href="product.html">商品</a></li>
			<li><a href="order.html">订单</a></li>
			<li><a href="guestbook.html">留言</a></li>
			<li><a href="news.html">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员<span id="managerName">pillys</span>您好，今天是<span id="today">2012-12-21</span>，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><em><a href="user-add.html">新增</a></em><a href="user.html">用户管理</a></dd>
				<dt>商品信息</dt>
				<dd><em><a href="productClass-add.html">新增</a></em><a href="productClass.html">分类管理</a></dd>
				<dd><em><a href="product-add.html">新增</a></em><a href="product.html">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.html">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.html">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.html">新增</a></em><a href="news.html">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>修改商品</h2>
		<div class="manage">
			<form action="../ProductServlet?opr=modify" id="formAddProduct" method="post">
				<input type="hidden" name="goodsId" value="${goods.goodsId}"/>
				<table class="form">
					<tr>
						<td class="field">商品名称：</td>
						<td><input type="text" class="text" name="productName" value="${goods.goodsName}" /></td>
					</tr>
					<tr>
						<td class="field">父级分类：</td>
						<td id="fatherClass">
							<select name="parentId">
							<c:forEach var="fa" items="${fatherClassify}">
								<option value="${fa.classifyId}" <c:if test="${fa.classifyId==fatherId}">selected="selected"</c:if>>
									${fa.classifyName}
								</option>
							</c:forEach>								
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">次级分类：</td>
						<td id="secondClass">
							<select name="secondId">
							<c:forEach var="ch" items="${secondClassify}">
								<option value="${ch.classifyId}"<c:if test="${ch.classifyId==requestScope.classifyId}">selected="selected"</c:if>>
									${ch.classifyName}
								</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">商品图片：</td>
						<td><input type="file" class="text" name="photo" /></td>
					</tr>
					<tr>
						<td class="field">商品价格：</td>
						<td><input type="text" class="text tiny" name="productPrice" value="${goods.price}"/> 元</td>
					</tr>
					<tr>
						<td class="field">品牌：</td>
						<td><input type="text" class="text" name="productBrand" value="${goods.brand}"/></td>
					</tr>
					<tr>
						<td class="field">库存：</td>
						<td><input type="text" class="text tiny" name="productInventory" value="${goods.inventory}"/></td>
					</tr>
					<tr>
						<td class="field">描述：</td>
						<td><input type="text" class="text" name="productDescribe" value="${goods.describe}"/></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="修改" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
