<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>Error | General Error</title>
<%@ include file="/WEB-INF/views/includes/head/defaultCSS.jsp" %>
</head>
<body>
<!-- header -->
<header>
  <%@ include file="/WEB-INF/views/includes/header_public.jsp" %>
</header>
<!-- /header --> 

<!-- main -->
<main> 
  <!-- main container -->
  <div class="main-container"> 
    <!-- message-container -->
    <div class="message-container">
	  <h1>General Error</h1>
	  <h3>常规错误</h3>
	  <p>An error has occurred while processing the page you requested. For questions, please contact the <a href="mailto:<%@ include file="/WEB-INF/views/includes/site/help_desk.jsp"%>?subject=General Error"><%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %> Help Desk</a>.</p>
	  </div>
    <!-- message-container --> 
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