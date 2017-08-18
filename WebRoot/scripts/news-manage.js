$(document).ready(function (){
	getNewsJsonByPage(1, 10);
	
	$("#totalPage").load("../NewsServlet","opr=getTotalNews");
	$("#firstPage").click(function(){
		getNewsJsonByPage(1, 10);
		$("#currentPage").html(1);
	});
	
	$("#lastPage").click(function(){
		var totalPage = $("#totalPage").html();
		getNewsJsonByPage(parseInt(totalPage), 10);
		$("#currentPage").html(totalPage);
	});
	
	$("#beforePage").click(function(){
		var currentPage = $("#currentPage").html();
		var beforePage = parseInt(currentPage)-1;
		var totalPage = parseInt($("#totalPage").html());
		if(beforePage<1) beforePage = 1;
		if(beforePage>totalPage) beforePage = totalPage;
		getNewsJsonByPage(beforePage, 10);
		$("#currentPage").html(beforePage);
	});
	
	$("#nextPage").click(function(){
		var currentPage = $("#currentPage").html();
		var nextPage = parseInt(currentPage)+1;
		var totalPage = parseInt($("#totalPage").html());
		if(nextPage<1) inVal = 1;
		if(nextPage>totalPage) nextPage = totalPage;
		getNewsJsonByPage(nextPage, 10);
		$("#currentPage").html(nextPage);
	});
	
	$("#pageInput").change(function(){
		var inputVal = $(this).val();
		if(inputVal!=null && inputVal!="" && !(isNaN(inputVal))){
			var inVal = parseInt(inputVal);
			var totalPage = parseInt($("#totalPage").html());
			if(inVal<1) inVal = 1;
			if(inVal>totalPage) inVal = totalPage;
			getNewsJsonByPage(inVal, 10);
			$("#currentPage").html(inVal);
		}
	});
});

function getNewsJsonByPage(page, pageSize){
	$.getJSON(
		"../NewsServlet",
		"opr=getNewsJsonByPage&page="+page+"&pageSize="+pageSize,
		function(data){
			$("#newsTable tr:gt(0)").remove();
			for (var i = 0; i < data.length; i++) {
				$("#newsTable").append("<tr><td class='first w4 c'>"+data[i].newsId
					+ "</td><td>"+data[i].newsTitle + "</td>"
					+ "<td class='w1 c'><a href='ManagerServlet?opr=modifyNews&newsId="
					+ data[i].newsId + "'>修改</a> <a href='javascript:DeleteNews("
					+ data[i].newsId + ");'>删除</a></td></tr>");
			}
		}
	);
}