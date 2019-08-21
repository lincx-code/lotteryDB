<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>历次奖号 | <%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %></title>
<%@ include file="/WEB-INF/views/includes/head/defaultCSS.jsp" %>
<%@ include file="/WEB-INF/views/includes/head/defaultJS.jsp" %>
</head>
<body>

<!-- header -->
<header>
	<%@ include file="/WEB-INF/views/includes/header_public.jsp" %>
</header>
<!-- /header -->

<!-- main -->
<main> 
  <!-- control-panel -->
  <div class="control-panel">
    <%@ include file="/WEB-INF/views/includes/control_panel.jsp" %>
  </div>
  <!-- /control-panel --> 
  
  <!-- main container -->
  <div class="main-container">
  	<!-- page-header -->
  	<div class="page-header">
  		<img src="/resources/images/logos/logo_mega.png" alt="MegaMillions" title="MegaMillions" class="page-img">
  		<h2>查看历次奖号</h2>
  	</div>
  	<!-- /page-header -->
  	
  	<c:choose>
    	<c:when test="${not empty collection}">
			<ul class="list-data">
				<c:forEach var="mega" items="${collection}">
				<li><a href="/mega/${mega.id}" title="修改"><i class="fas fa-pen-square"></i></a> 
				<span class="data-date">${mega.drawDate}</span> 
				<span class="data-multi">${mega.ball1}</span> 
				<span class="data-multi">${mega.ball2}</span> 
				<span class="data-multi">${mega.ball3}</span> 
				<span class="data-multi">${mega.ball4}</span> 
				<span class="data-multi">${mega.ball5}</span> 
				<span class="data-single">${mega.megaball}</span></li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
		<p>暂无数据!</p>
		</c:otherwise>
	</c:choose>
	
	<!-- pagination -->
	<c:if test="${pages > 0}">
	<div class="pagination-container">页数： 
		<c:forEach var="i" begin="0" end="${pages-1}">
			<c:choose>
				<c:when test="${current == pages}">
					<a href="/mega/collection?pg=${i+1}" class="current">${i+1}</a> 
				</c:when>
				<c:otherwise>
					<a href="/mega/collection?pg=${i+1}">${i+1}</a> 
				</c:otherwise>
			</c:choose>
        </c:forEach>
    </div>
	</c:if>
	<!-- /pagination -->
  </div>
  <!-- /main container --> 
</main>
<!-- /main --> 

<!-- footer -->
<footer>
  <%@ include file="/WEB-INF/views/includes/footer_all.jsp" %>
</footer>
<!-- /footer -->
</body>
</html>