$(document).ready(function(){
	function initShow(){
		$.ajax({
			"url"		:	"GuestBookServlet",
			"type"		:	"POST",
			"data"		:	"opr=initShow&page=1&pageSize=5",
			"dataType"	:	"json",
			"success"	:	processComList
		})
	};
	
	function processComList(data){
		var $commList = $(".guestbook>ul").empty();
		for (var i = 0; i < data.length; i++) {
			$commList.append("<li><dl><dt>" + data[i].title + "</dt><dd class='author'>网友："
					+ data[i].author+ "<span class='timer'>"+ data[i].time + "</span>"
					+ "</dd><dd>"+data[i].content+"</dd></dl></li>");
		}
	};
	
	function initPage(){
		$.ajax({
			"url"		:	"GuestBookServlet",
			"type"		:	"POST",
			"data"		:	"opr=initPage&page=1&pageSize=5",
			"dataType"	:	"json",
			"success"	:	processPage
		})
	};
	
	function processPage(data){
		var $pageList = $(".pager>ul").empty();
		$pageList.append("<li>当前页/总页数：1/"+data+"</li><li><a href='javascript:;'>上一页</a></li>"
				+ "<li class='current'>1</li>");
		if(data<5){
			for(var i=2; i<=data; i++){
				$pageList.append("<li onclick='javascript:toPage("+i+");'><a href='javascript:;'>"+i+"</a></li>");
			}
		}else{
			for(var i=2; i<=5; i++){
				$pageList.append("<li onclick='javascript:toPage("+i+");'><a href='javascript:;'>"+i+"</a></li>");
			}
			$pageList.append("<li>...</li>");
		}
		$pageList.append("<li onclick='javascript:toPage(2);'><a href='javascript:;'>下一页</a></li>");
	};
	
	$("#newComment").click(function(){
		var guestName = $("#guestName").val();
		var guestTitle = $("#guestTitle").val();
		var guestContent = $("#guestContent").val();
		if(guestName==null || guestName=="" || guestTitle==null || guestTitle=="" || guestContent==null ||guestContent==""){
			alert("填写内容不能为空");
		}else{
			$.ajax({
				"url"		:	"GuestBookServlet",
				"type"		:	"POST",
				"data"		:	"opr=insert&author="+guestName+"&title="
								+ guestTitle+"&content="+guestContent,
				"dataType"	:	"json",
				"success"	:	processInsert
			});
		}
	});
	
	function processInsert(data){
		if(data>0){
			alert("更新成功！");
			initShow();
			initPage();
			$("#guestName").val("");
			$("#guestTitle").val("");
			$("#guestContent").val("");
		}else{
			alert("更新失败！");
		}
	}
	initShow();
	initPage();
});
