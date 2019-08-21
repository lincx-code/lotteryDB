<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>Error | Database Error</title>
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
      <h1>Database Error</h1>
      <h3>数据库错误</h3>
      <p>An error occurred when trying to perform a database operation. This can happen if the database is very busy, if the database service is stopped, or if the connection to the database is lost. If problem persists, please contact the <a href="mailto:<%@ include file="/WEB-INF/views/includes/site/help_desk.jsp"%>?subject=Database Error">
        <%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %> Help Desk</a>. </p>
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