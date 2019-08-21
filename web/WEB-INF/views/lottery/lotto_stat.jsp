<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="com.code.lottery.OccurrenceStat"%>
<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/head/defaultMeta.jsp" %>
<title>历次奖号统计 | <%@ include file="/WEB-INF/views/includes/site/platform_name.jsp" %></title>
<%@ include file="/WEB-INF/views/includes/head/defaultCSS.jsp" %>
<%@ include file="/WEB-INF/views/includes/head/defaultJS.jsp" %>

<%
List<OccurrenceStat> multiStat = (List<OccurrenceStat>)request.getAttribute("multiStat");
%>

<!-- Google Chart API -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChartMultiStat);

function drawChartMultiStat() {
  var data = google.visualization.arrayToDataTable([
    ['号码', '次数'],
    <% if(multiStat != null && multiStat.size() > 0) {
    	for(int i = 0; i < multiStat.size()-1; i++){
    %>
    ['<%=multiStat.get(i).getNumber() %>', <%=multiStat.get(i).getCount() %>],
    <% }} %>
    ['<%=multiStat.get(multiStat.size()-1).getNumber() %>', <%=multiStat.get(multiStat.size()-1).getCount() %>]
  ]);
  
  var view = new google.visualization.DataView(data);
  view.setColumns([0, 1,
      { 
        sourceColumn: 0,
        type: "string",
        role: "annotation" }
      ]);
  
  var options = {
		  vAxis: {
		        format: '#',
		        title: '次数',
		        titleTextStyle: {
		        	fontSize: 14,
		        	italic: false
		        }
		    },
		    hAxis: {
		        title: "号码",
		        titleTextStyle: {
		        	fontSize: 14,
		        	italic: false
		        }
		    }
  };

  var chart = new google.visualization.ColumnChart(document.getElementById('multi-stat'));
  chart.draw(view, options);
}
</script>
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
  		<h2>历次奖号统计</h2>
  	</div>
  	<!-- /page-header -->
  	<h3 class="chart-title">多选号前十统计</h3>
  	<div class="chart-section" id="multi-stat"></div>	
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