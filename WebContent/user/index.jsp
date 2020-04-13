<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE HTML>
<html>
  <head>
    <title>智远图书网</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header">
    		<div id="tool-bar">&nbsp;&nbsp; 欢迎光临智远图书网，[
    			<c:if test="${!empty bookUser }" var="res">${showUser }</c:if>
    			<c:if test="${!res }"><a href="user-login.html">登录</a></c:if>
    		]&nbsp;[<a href="user-regist.html">注册</a>]&nbsp;&nbsp;&nbsp;<a href="index.html">首页</a>&nbsp;|&nbsp;<a href="${pageContext.request.contextPath}/OrdersController?op=showCar">购物车</a>&nbsp;|&nbsp;<a href="#">订单</a>&nbsp;|&nbsp;
<a href="${pageContext.request.contextPath}/UserBookController?op=longinout">退出</a>
    		
    	</div>
    	<form id="search-bar" action="${pageContext.request.contextPath}/BookInfoController?op=info&pageIndex=1" method="post">
    		书名：<input type="text" class="txt" name="bookName" />
    		<input id="search-btn" type="submit" value=" 搜索图书 " />
    	</form>
    	<div id="main">
    		<div class="section-left">
    			<div class="box-left">
    				<div class="box-title">分类畅销榜</div>
    				
    				<div class="box-content">
    				<p>·<a href="${pageContext.request.contextPath}/BookInfoController?op=findbyid&id=0">全部</a></p>
    				<c:forEach items="${list }" var="category">
						<p>·<a href="${pageContext.request.contextPath}/BookInfoController?op=findbyid&id=${category.id}">${category.category}</a></p> 					
    				</c:forEach>
    				</div>
    				
    			</div>
    		</div>
    		<div class="section-right">
    			<div class="box-right">
    				<div class="box-title">您目前浏览的图书【搜索条件——分类：全部；书名：无】</div>
    				<div class="paging" style="border-bottom: 1px solid  #64A26F; color: #006666; ">
    					 <span class="fr"><a href="#">首页</a>&nbsp;<a href="#">上一页</a>&nbsp;<a href="#">下一页</a>&nbsp;<a href="#">尾页</a>&nbsp;</span>
    					 共有图书1000种，分50页显示，每页显示20个商品
    				</div>
    				<c:forEach items="${pg.pageLists }" var="info" varStatus = "status">
    				<div class="box-item">
    					<div class="img-box"><img src="${pageContext.request.contextPath}/static/photo/${info.photo}" /></div>
    					<div class="info-box">
    						<span style="font-size: 14px; "><a href="#">${info.bookName }</a></span><br />
							作者：${info.author }&nbsp;&nbsp;著<br />
							分类：${info.cate.category}<br />
							出版社：${info.publisher }<br />							
							售价：￥<span style="color: #990000;">${info.price }</span>		<br />					
    					</div>
    					<a href="javascript:addCar(${info.id },${info.price })" class="btn-buy">购&nbsp;&nbsp;买</a>    					
    					<div class="cf"></div>
    				</div> 
    				</c:forEach>    				
    				<div class="paging">
    					 <span class="fr">【第${pg.currPage }页/共${pg.totalPages}页】
    					 <a href="${pageContext.request.contextPath}/BookInfoController?op=show&pageIndex=1">首页</a>
    					 &nbsp;<a href="${pageContext.request.contextPath}/BookInfoController?op=show&pageIndex=${pg.currPage-1}">上一页</a>
    					 &nbsp;<a href="${pageContext.request.contextPath}/BookInfoController?op=show&pageIndex=${pg.currPage+1}">下一页</a>
    					 &nbsp;<a href="${pageContext.request.contextPath}/BookInfoController?op=show&pageIndex=${pg.totalPages}">尾页</a>
    					 &nbsp;</span>  
    				</div>
    			</div>
    		</div>
    		<div class="cf"></div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		function addCar(id,price){
			// 将数据添加到购物车中
			$.post("${pageContext.request.contextPath}/OrdersController?op=add",
					{id:id,price:price},function(res){
				if(res.result=="true"){
					alert("增加成功");
				}else{
					alert("增加失败");
				}
			});
		}
	</script>
  </body>
</html>