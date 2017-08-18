// JavaScript Document
function Delete(id)
{
	if(confirm("确定要删除吗？")) {
		location.href = "DelClassifyServlet?id=" + id;
	}
}

function DeleteGoods(id)
{
	if(confirm("确定要删除吗？")) {
		location.href = "ManagerServlet?opr=delGoods&goodsId=" + id;
	}
}

function DeleteComment(id)
{
	if(confirm("确定要删除吗？")) {
		location.href = "ManagerServlet?opr=delComment&id=" + id;
	}
}

function DeleteNews(id)
{
	if(confirm("确定要删除吗？")) {
		location.href = "ManagerServlet?opr=delNews&id=" + id;
	}
}