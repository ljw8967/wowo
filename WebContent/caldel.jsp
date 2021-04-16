<%@page import="cal.CalendarDto"%>
<%@page import="cal.CalendarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>caldel.jsp</title>
</head>
<body>

<%!
public String toOne(String msg){	// 08 -> 8
	return msg.charAt(0)=='0'?msg.charAt(1) + "": msg.trim();
}
%>

<%
int seq = Integer.parseInt(request.getParameter("seq"));

CalendarDao dao = CalendarDao.getInstance();
CalendarDto dto = dao.getDay(seq);
boolean isS = dao.deletecal(seq);

String year = dto.getRdate().substring(0, 4);	// 년도
String month = toOne(dto.getRdate().substring(4, 6));	// 월
String day = toOne(dto.getRdate().substring(6, 8));		// 일

String url = String.format("%s?year=%s&month=%s&day=%s", 
							"calendarlist.jsp", year, month, day);

if(isS){
	%>
	<script type="text/javascript">
	alert("성공적으로 삭제 되었습니다");
	location.href="<%=url %>";
	</script>	
	<%
}else{	
	%>
	<script type="text/javascript">
	alert("삭제하지 못했습니다");
	location.href="<%=url %>";
	</script>
	<%
}	
%>


</body>
</html>




