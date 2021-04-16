<%@page import="cal.CalendarDto"%>
<%@page import="cal.CalendarDao"%>
<%@page import="util.UtilEx"%>
<%@page import="com.sun.corba.se.impl.javax.rmi.CORBA.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
String hour = request.getParameter("hour");
String min = request.getParameter("min");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
// rdate	202103190323
String rdate = year + UtilEx.two(month) + UtilEx.two(day) + UtilEx.two(hour) + UtilEx.two(min);  
System.out.println("rdate");
CalendarDao dao = CalendarDao.getInstance();

boolean isS = dao.addCalendar(new CalendarDto(id, title, content, rdate));
if(isS){
	%>
	<script type="text/javascript">
	alert('일정이 추가 되었습니다');
	location.href = "calendarlist.jsp";
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
	alert('일정이 추가되지 않았습니다');
	location.href = "calendarlist.jsp";
	</script>
	<%	
}
%>
</body>
</html>







