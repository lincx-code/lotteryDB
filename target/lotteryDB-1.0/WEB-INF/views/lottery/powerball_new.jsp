<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>输入新奖号 | <%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %></title>
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
  		<img src="/resources/images/logos/logo_powerball.png" alt="Powerball" title="Powerball" class="page-img">
  		<h2>输入新奖号</h2>
  	</div>
  	<!-- /page-header -->
  	<fieldset>
  		<form action="/powerball/insert" method="POST" onsubmit="return submitData()">
  			<p class="instruction">请从以下选出5个号码</p>
  			<!-- multi-ball selection -->
  			<div class="multi-selection">
  				<% for(int i = 1; i <= 69; i++){ %>
    			<span class="ball-multi"><%=i %></span>
    			<% } %>
    			<!-- hidden fields -->
    			<input type="hidden" name="ball1">
    			<input type="hidden" name="ball2">
    			<input type="hidden" name="ball3">
    			<input type="hidden" name="ball4">
    			<input type="hidden" name="ball5">
    			<!-- /hidden fields -->
  			</div>
    		<!-- /multi-ball selection -->
    	
    		<!-- single-ball selection -->
    		<p class="instruction">请从以下选出1个号码</p>
    		<div class="single-selection">
    			<% for(int i = 1; i <= 26; i++){ %>
    			<span class="ball-single"><%=i %></span>
    			<% } %>
    			<input type="hidden" name="powerball" id="pick1" value="0">
    		</div>
    		
    		<!-- draw date selection -->
    		<%@ include file="/WEB-INF/views/includes/selection_dates.jsp" %>
    		<!-- /draw date selection -->

    		<input type="submit" value="输入">
    	</form>
  	</fieldset>
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
	  if(multiCount < 5){
		$(this).addClass("active");
      	multiCount++;
	  }
  }
  //Developer note
  //console.log("multiCount: " + multiCount);
});

let singleCount = 0;
$(".ball-single").click(function(){
	if($(this).hasClass("active")){
    $(this).removeClass("active");
    singleCount--;
  }else{
	  if(singleCount < 1){
		  $(this).addClass("active");
	    singleCount++;
	  }
  }
	//Developer note
	//console.log("singleCount: " + singleCount);
});

function submitData(){
	if(multiCount != 5 || singleCount != 1){
		alert("Please select 5 numbers and 1 bonus number.");
		return false;
	}else{
		$("input[name='ball1']").val($(".ball-multi.active").eq(0).text());
		$("input[name='ball2']").val($(".ball-multi.active").eq(1).text());
		$("input[name='ball3']").val($(".ball-multi.active").eq(2).text());
		$("input[name='ball4']").val($(".ball-multi.active").eq(3).text());
		$("input[name='ball5']").val($(".ball-multi.active").eq(4).text());
		$("input[name='powerball']").val($(".ball-single.active").eq(0).text());
    
    	//Developer's notes
		/*alert($("input[name='ball1']").val() + " " + $("input[name='ball2']").val() + " " +
				$("input[name='ball3']").val() + " " + $("input[name='ball4']").val() + " " + $("input[name='ball5']").val() 
				+ " " + $("input[name='powerball']").val());*/
	}
}
</script>
</body>
</html>