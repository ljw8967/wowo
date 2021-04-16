<%@page import="cal.CalendarDto"%>
<%@page import="cal.CalendarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>calupdateAf.jsp</title>
</head>
<body>

<%!
public String two(String msg){
	return msg.trim().length()<2?"0"+msg:msg.trim();	// 1 ~ 9 -> 01
}
%>

<%
int seq = Integer.parseInt(request.getParameter("seq"));

String title = request.getParameter("title");
String content = request.getParameter("content");

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
String hour = request.getParameter("hour");
String min = request.getParameter("min");

String rdate = year + two(month) + two(day) + two(hour) + two(min);

CalendarDao dao = CalendarDao.getInstance();

boolean isS = dao.Updatecal(seq, title, content, rdate);

String urllist = String.format("%s?year=%s&month=%s", "calendarlist.jsp", year, month);

if(isS){	
	%>
	<script type="text/javascript">
	alert("성공적으로 일정을 수정했습니다");
	location.href="<%=urllist %>";	
	</script>
	<%
}else{	
	%>
	<script type="text/javascript">
	alert("일정을 수정하지 못했습니다");
	location.href="<%=urllist %>";	
	</script>
	<%
}
%>


</body>
</html>





