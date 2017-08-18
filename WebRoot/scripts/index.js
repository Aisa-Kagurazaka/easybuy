$(document).ready(function(){

	$("#classifyBar").load("ClassifyServlet","opr=initClassify");

	$("#offSaleList").load("ProductServlet","opr=initOffList");

	$("#hotSaleList").load("ProductServlet","opr=initHotList");

	$("#viewHistory").load("ProductServlet","opr=initViewHistory");

	$("#newsList").load("NewsServlet","opr=initNewsList");

	$("#noticeList").load("NoticeServlet","opr=initNoticeList");
	
	$("#isAdmin").load("UserServlet","opr=isAdmin");
	
	$("#navBar").load("ClassifyServlet","opr=initNavBar");
});