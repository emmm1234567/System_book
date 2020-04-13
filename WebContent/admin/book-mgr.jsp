<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML>
<html>
  <head>
    <title>图书网后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mgr.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header"><h1>智远教育--图书网后台管理系统</h1></div>
    	<div id="info">管理员：[
    			<c:if test="${!empty bookUser }" var="res">${showUser }</c:if>
    			<c:if test="${!res }"><a href="user-login.html">请登录</a></c:if>
    		]</div>
    	<div class="menu">
    		<ul>
    			<li><a href="admin-home.jsp">首页</a></li>
    			<li><a href="category-mgr.jsp">图书分类管理</a></li>
    			<li><a href="book-mgr.jsp">图书管理</a></li>
    			<li><a href="#">购书订单管理</a></li>
    		</ul>	
    	</div>
    	<div id="main">
			<div class="section-left">    	
				<h2>图书信息列表</h2>		
				<table class="table" cellspacing="0" style="font-size: 12px;">
			    	<tr>
			    		<td class="header" width="100">书名</td>
			    		<td class="header" width="60">作者</td>
			    		<td class="header" width="60">类型</td>
			    		<td class="header" width="60">售价</td>
			    		<td class="header" width="60">操作</td>
			    	</tr>
			    	<c:forEach items= "${ilist }" var="info">
			    	<tr>
			    		<td>${info.bookName }</td>
			    		<td>${info.author }</td>
			    		<td>${info.cate.category }</td>
			    		<td>${info.price }</td>
			    		<td>
			    		<a href="${pageContext.request.contextPath}/BookInfoController?op=delete&id=${info.id }">删除</a>
			    		&nbsp;<a href="${pageContext.request.contextPath}/BookInfoController?op=findupdate&id=${info.id }">编辑</a></td>
			    	</tr>
			    	</c:forEach>
			    </table>
			</div>
			<div class="section-right">
				<h2>添加图书信息</h2>
				<form action="${pageContext.request.contextPath }/BookInfoController?op=add" method="post" enctype="Multipart/form-data">
					<p>
						图书书名：<input type="text" name="bookName" />
					</p>
					<p>
						图书作者：<input type="text" name="author" />
					</p>
					<p>
						图书分类： <select name="categoryId">		
								<c:forEach items="${list }" var="cat">
									      <option value="${cat.id}">${cat.category}</option>	
							    </c:forEach>
						    </select>
					</p>
					<p>
						图书售价：<input type="text" name="price" />
					</p>
					<p>
						图书出版社：<input type="text" name="publisher" />
					</p>
					<p>
						图书图片：<input type="file" name="photo" />
					</p>
					<p>
						<input type="submit" value=" 保 存 " />
					</p>
				</form>
			</div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
  </body>
</html>

