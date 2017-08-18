<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="scripts/jquery-2.2.4.js"></script>
<script type="text/javascript" src="scripts/login.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#minus").click(function(){
		var amountStr = $("#amount").val();
		if(isNaN(amountStr)){
			alert("请正确输入数量！");
			$("#amount").val("1");
			return;
		}
		var amount = parseInt(amountStr);
		amount =amount -1;
		if(amount<1){
			alert("不能再减了！");
			amount = 1;
		}
		$("#amount").val(amount);
		countTotal();
	});
	$("#plus").click(function(){
		var amountStr = $("#amount").val();
		if(isNaN(amountStr)){
			alert("请正确输入数量！");
			$("#amount").val("1");
			return;
		}
		var amount = parseInt(amountStr);
		amount = amount + 1;
		$("#amount").val(amount);
		countTotal();
	});
	$("#amount").change(function(){
		var amountStr = $("#amount").val();
		if(isNaN(amountStr)){
			alert("请正确输入数量！");
			$("#amount").val("1");
			return;
		}
		countTotal();
	});
	function countTotal(){
		var amountStr = $("#amount").val();
		var amount = parseInt(amountStr);
		var singlePriceStr = $("#singlePrice").html().substring(1);
		var singlePrice = parseFloat(singlePriceStr);
		$("#totalPrice").html("￥"+(amount*singlePrice).toFixed(2));
		$("#totalVar").val((amount*singlePrice).toFixed(2));
	}
	$("#shoppingForm").submit(function(){
		var totalVar = $("#totalVar").val();
		if(totalVar==null || totalVar==""){
			alert("购物车空空如也！不能结算！");
			return false;
		}
	});
});
</script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help"><a href="#" class="shopping">购物车</a><span id="loginOrNot"><a href="login.html">登录</a></span><a href="register.html">注册</a><a href="guestbook.html">留言</a><span id="isAdmin"></span></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="index.html">首页</a></li>
			<li><a href="#">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="#">音乐</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">少儿</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">小说</a></li>
			<li><a href="#">外语</a></li>
			<li><a href="#">数码相机</a></li>
			<li><a href="#">笔记本</a></li>
			<li><a href="#">羽绒服</a></li>
			<li><a href="#">秋冬靴</a></li>
			<li><a href="#">运动鞋</a></li>
			<li><a href="#">美容护肤</a></li>
			<li><a href="#">家纺用品</a></li>
			<li><a href="#">婴幼奶粉</a></li>
			<li><a href="#">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="OrderSevlet?opr=add" id="shoppingForm" method="post">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品单格</th>
					<th>购买数量</th>
					<th>合计</th>
					<th>操作</th>
				</tr>
				<c:forEach var="goods" items="${requestScope.goodsList}">
					<tr id="product_id_${goods.goodsId}">
						<td class="thumb"><img src="images/product/${goods.path}" width="65"/><a href="ProductViewServlet?goodsId=${goods.goodsId}">${goods.goodsName}</a></td>
						<td class="price" id="price_id_1">
							<span id="singlePrice">￥${goods.price}</span>
							<input type="hidden" value="${goods.price}" />
						</td>
						<td class="number">
							<input type="button" id="minus" value="-" style="display:inline-block;width:15px" />
							<input type="text" name="amount" id="amount" value="${goods.inventory}" style="display:inline-block;width:25px" />
							<input type="button" id="plus" value="+" style="display:inline-block;width:15px" />
						</td>
						<td class="price">
							<span id="totalPrice">￥${(goods.price)*(goods.inventory)}</span>
							<input type="hidden" value="${goods.goodsId}" name="goodsId"/>
							<input type="hidden" value="${(goods.price)*(goods.inventory)}" id="totalVar" name="total"/>
						</td>
						<td class="delete"><a href="javascript:delShopping(1);">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
	<script type="text/javascript">
		document.write("Cookie中记录的购物车商品ID："+ getCookie("product") + "，可以在动态页面中进行读取");
	</script>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
