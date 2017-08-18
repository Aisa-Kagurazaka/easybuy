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
<script type="text/javascript" src="scripts/index.js"></script>
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
	您现在的位置：<a href="index.html">易买网</a> &gt; <c:if test="${!empty news}"><c:out value="阅读新闻"></c:out></c:if><c:if test="${!empty notice}"><c:out value="阅读公告"></c:out></c:if>
</div>
<div id="main" class="wrap">
	<div class="left-side">
		<div class="news-list">
			<h4>最新公告</h4>
			<ul id="noticeList">
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
			</ul>
		</div>
		<div class="spacer"></div>
		<div class="news-list">
			<h4>新闻动态</h4>
			<ul id="newsList">
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
			</ul>
		</div>
	</div>
	<div id="news" class="right-main">
		<h1><c:if test="${!empty news}">${news.newsTitle}</c:if><c:if test="${!empty notice}">${notice.title}</c:if></h1>
		<div class="content">
			<c:if test="${!empty news}">${requestScope.news.content}</c:if>
			<c:if test="${!empty notice}">${requestScope.notice.content}</c:if>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
