$(document).ready(function(){
	
	function initFatherClass(){
		$.getJSON({
			"url"	:	"../ClassifyServlet",
			"data"	:	"opr=initFatherList",
			"success"	:	initFathListSucc
		});
		function initFathListSucc(data){
			var $fatherList = $("#fatherClass>select").empty();
			for(var i=0; i<data.length; i++){
				$fatherList.append("<option id='"+data[i].classifyId+"' value='"+data[i].classifyId
						+"' fathertId='"+data[i].fatherId+"'>"+data[i].classifyName+"</option>");
			}
		}
	}
	
	function initSecondClass(){
		$.getJSON({
			"url"	:	"../ClassifyServlet",
			"data"	:	"opr=initSecondList",
			"success"	:	initSecListSucc
		});
		function initSecListSucc(data){
			var $fatherList = $("#secondClass>select").empty();
			for(var i=0; i<data.length; i++){
				$fatherList.append("<option id='"+data[i].classifyId+"' value='"+data[i].classifyId
						+"' fathertId='"+data[i].fatherId+"'>"+data[i].classifyName+"</option>");
			}
		}
	}
	initFatherClass();
	initSecondClass();
	
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
	
	$("#formAddProduct").submit(function(){
		return checkForm();
	});
	
	function checkForm(){
		var name = $("#productName").val();
		if(name==null || name==""){
			alert("商品名不能为空");
			return false;
		}
		var price = $("#productPrice").val();
		if(price==null || price==""){
			alert("价格不能为空！");
			return false;
		}
		if(isNaN(price)){
			alert("请输入正确的价格！");
			return false;
		}
		var inventory = $("#productInventory").val();
		if(inventory==null || inventory==""){
			alert("库存不能为空！");
			return false;
		}
		if(isNaN(inventory)){
			alert("请输入正确的库存！");
			return false;
		}
		return true;
	}
});