<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>Error | Method Not Supported</title>
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
      <h1>Method Not Allowed</h1>
      <h3>网页请求错误</h3>
      <p>The page you are looking for cannot be displayed because an invalid method is being used. For questions, please contact the <a href="mailto:<%@ include file="/WEB-INF/views/includes/site/help_desk.jsp"%>?subject=Method Not Supported"><%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %> Help Desk</a>.</p>
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