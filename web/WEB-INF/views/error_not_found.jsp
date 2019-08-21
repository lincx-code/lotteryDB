<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>Error | Page Not Found</title>
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
      <h1>Page Not Found</h1>
      <h3>页面不存在</h3>
      <p>Sorry, the page you tried cannot be found. For questions, please contact the <a href="mailto:<%@ include file="/WEB-INF/views/includes/site/help_desk.jsp"%>?subject=Page Not Found">
        <%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %>
        </a> Help Desk. Click <a href="/login">here</a> to go back to the login page.</p>
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