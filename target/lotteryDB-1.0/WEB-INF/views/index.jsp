<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>

<head>
  <%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
  <title>Home | <%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %>
  </title>
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
    <!-- main container -->
    <div class="main-container index">
      <!-- powerball -->
      <div class="nav-block">
        <img src="/resources/images/logos/logo_powerball.png" alt="Powerball" title="Powerball" class="nav-img">
        <ul>
          <li><a href="/powerball/new"><i class="fas fa-plus-circle"></i> 输入新奖号</a></li>
          <li><a href="/powerball/generate"><i class="fas fa-random"></i> 电脑选号</a></li>
          <li><a href="/powerball/collection"><i class="fas fa-th-list"></i> 查看历次奖号</a></li>
          <li><a href="/powerball/stat"><i class="fas fa-chart-bar"></i> 查看奖号统计</a></li>
        </ul>
      </div>
      <!-- /powerball -->

      <!-- lotto -->
      <div class="nav-block">
        <img src="/resources/images/logos/logo_nylotto.png" alt="NY Lotto" title="NY Lotto" class="nav-img">
        <ul>
          <li><a href="/lotto/new"><i class="fas fa-plus-circle"></i> 输入新奖号</a></li>
          <li><a href="/lotto/generate"><i class="fas fa-random"></i> 电脑选号</a></li>
          <li><a href="/lotto/collection"><i class="fas fa-th-list"></i> 查看历次奖号</a></li>
          <li><a href="/lotto/stat"><i class="fas fa-chart-bar"></i> 查看奖号统计</a></li>
        </ul>
      </div>
      <!-- /lotto -->

      <!-- mega millions -->
      <div class="nav-block">
        <img src="/resources/images/logos/logo_mega.png" alt="Mega Millions" title="Mega Millions" class="nav-img">
        <ul>
          <li><a href="/mega/new"><i class="fas fa-plus-circle"></i> 输入新奖号</a></li>
          <li><a href="/mega/generate"><i class="fas fa-random"></i> 电脑选号</a></li>
          <li><a href="/mega/collection"><i class="fas fa-th-list"></i> 查看历次奖号</a></li>
          <li><a href="/mega/stat"><i class="fas fa-chart-bar"></i> 查看奖号统计</a></li>
        </ul>
      </div>
      <!-- /mega millions -->
      
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