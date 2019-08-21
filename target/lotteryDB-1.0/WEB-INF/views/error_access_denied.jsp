<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>Error | Access Denied</title>
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
      <h1>Access Denied</h1>
      <h3>访问权限错误</h3>
      <p>Sorry, access to this resource on the server is denied. Click <a href="/">here</a> to go back to the login page.</p>
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