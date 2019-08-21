<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>修改奖号 | <%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %></title>
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
  		<img src="/resources/images/logos/logo_nylotto.png" alt="NY Lotto" title="NY Lotto" class="page-img">
  		<h2>修改奖号</h2>
  	</div>
  	<!-- /page-header -->
  	
  	<c:choose>
		<c:when test="${lotto != null}">
		<!-- recordSection -->
  		<div class="record-section">
  			<h3>旧奖号 <i class="fas fa-arrow-down"></i></h3>
  			<p class="data"> 
				<span class="data-date">${lotto.drawDate}</span> 
				<span class="data-multi">${lotto.num1}</span> 
				<span class="data-multi">${lotto.num2}</span> 
				<span class="data-multi">${lotto.num3}</span> 
				<span class="data-multi">${lotto.num4}</span> 
				<span class="data-multi">${lotto.num5}</span> 
				<span class="data-multi">${lotto.num6}</span>
			</p>
  			<h3>请选出新奖号 <i class="fas fa-arrow-down"></i></h3>
  		</div>
  		<!-- /recordSection -->
  	
  		<fieldset>
  			<form action="/lotto/update/${lotto.id}" method="POST" onsubmit="return submitData()">
  				<p class="instruction">请从以下选出6个号码</p>
  				<!-- multi-ball selection -->
  				<div class="multi-selection">
  				<% for(int i = 1; i <= 59; i++){ %>
    			<span class="ball-multi"><%=i %></span>
    			<% } %>
    			<!-- hidden fields -->
    			<input type="hidden" name="num1">
    			<input type="hidden" name="num2">
    			<input type="hidden" name="num3">
    			<input type="hidden" name="num4">
    			<input type="hidden" name="num5">
    			<input type="hidden" name="num6">
    			<!-- /hidden fields -->
  				</div>
    			<!-- /multi-ball selection -->
    	
    			<!-- draw date selection -->
    			<%@ include file="/WEB-INF/views/includes/selection_dates.jsp" %>
    			<!-- /draw date selection -->
    			<input type="submit" value="修改">
    	</form>
  	</fieldset>		
		</c:when>
		<c:otherwise>
		<p>错误！此ID暂无数据。</p> 
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

<script>
let multiCount = 0;
$(".ball-multi").on('click', function(){
  if($(this).hasClass("active")){
    $(this).removeClass("active");
    multiCount--;
  }else{
	  if(multiCount < 6){
		$(this).addClass("active");
      	multiCount++;
	  }
  }
  //Developer note
  //console.log("multiCount: " + multiCount);
});

function submitData(){
	if(multiCount != 6){
		alert("Please select 6 numbers.");
		return false;
	}else{
		$("input[name='num1']").val($(".ball-multi.active").eq(0).text());
		$("input[name='num2']").val($(".ball-multi.active").eq(1).text());
		$("input[name='num3']").val($(".ball-multi.active").eq(2).text());
		$("input[name='num4']").val($(".ball-multi.active").eq(3).text());
		$("input[name='num5']").val($(".ball-multi.active").eq(4).text());
		$("input[name='num6']").val($(".ball-multi.active").eq(5).text());
    
    	//Developer's notes
		/*alert($("input[name='num1']").val() + " " + $("input[name='num2']").val() + " " +
				$("input[name='num3']").val() + " " + $("input[name='num4']").val() + " " + $("input[name='num5']").val() 
				+ " " + $("input[name='num6']").val());*/
	}
}
</script>
</body>
</html>