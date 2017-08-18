$(document).ready(function (){
	function isLogin(){
		$("#loginOrNot").load("UserServlet","opr=loginOrNot");
	}
	isLogin();
});