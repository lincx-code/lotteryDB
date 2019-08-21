<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<p class="instruction">请选择开奖日期</p>
<!-- date-selector -->
<div class="date-selector">
	<!-- year -->
	<div class="selectWrapper">
		<label>年</label>
		<select class="year">
    		<% for(int i = 2014; i <= 2030; i++){ %>
    		<option value="<%=i%>"><%=i %></option>
    		<% } %>
		</select>
	</div>
	<!-- /year -->

	<!-- month -->
	<div class="selectWrapper">
		<label>月</label>
		<select class="month">
			<% for(int i = 1; i <= 12; i++){ %>
			<% if(i < 10){ %>
			<option value="0<%=i%>">0<%=i %></option>
			<% }else{ %>
    		<option value="<%=i%>"><%=i %></option>
    		<% } } %>
		</select>
	</div>
	<!-- /month -->

	<!-- month -->
	<div class="selectWrapper">
		<label>日</label>
		<select class="day">
			<% for(int i = 1; i <= 31; i++){ %>
    		<% if(i < 10){ %>
			<option value="0<%=i%>">0<%=i %></option>
			<% }else{ %>
    		<option value="<%=i%>"><%=i %></option>
    		<% } } %>
		</select>
	</div>
	<input type="hidden" name="draw_date" id="draw_date" value="0000-00-00">
	<!-- /month -->
</div>
<!-- /date-selector -->

<script>
$(".year, .month, .day").change(function () {
  $("#draw_date").val($(".year").val() + "-" + $(".month").val() + "-" + $(".day").val());
}).change();
</script>