<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>电脑选号 | <%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %></title>
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
  		<h2>电脑选号</h2>
  	</div>
  	<!-- /page-header -->
  	
  	<c:choose>
		<c:when test="${mega != null}">
		<!-- recordSection -->
  		<div class="record-section">
  			<p class="data"> 
				<span class="data-multi">${mega.ball1}</span> 
				<span class="data-multi">${mega.ball2}</span> 
				<span class="data-multi">${mega.ball3}</span> 
				<span class="data-multi">${mega.ball4}</span> 
				<span class="data-multi">${mega.ball5}</span> 
				<span class="data-single">${mega.megaball}</span>
			</p>
  		</div>
  		<!-- /recordSection -->
		</c:when>
		<c:otherwise>
		<p>错误！暂无数据。</p> 
		</c:otherwise>
	</c:choose>
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