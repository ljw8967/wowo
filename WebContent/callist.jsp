<%@page import="cal.CalendarDto"%>
<%@page import="cal.CalendarDao"%>
<%@page import="cal.CalendarDto"%>
<%@page import="cal.CalendarDao"%>
<%@page import="util.UtilEx"%>
<%@page import="java.util.List"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!

// Date -> String	
// String -> Date   yyyy-mm-dd
public String toDates(String mdate){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
	
	// 201810021607	-> 2018-10-02 16:07 
	String s = mdate.substring(0, 4) + "-"	// yyyy 
			+ mdate.substring(4, 6) + "-"	// MM
			+ mdate.substring(6, 8) + " "	// dd
			+ mdate.substring(8, 10) + ":"	// hh
			+ mdate.substring(10, 12) + ":00";
	
	Timestamp d = Timestamp.valueOf(s);
	
	return sdf.format(d);
}
%>        

<%
MemberDto user = (MemberDto)session.getAttribute("login");

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");

String dates = year + UtilEx.two(month + "") + UtilEx.two(day + "");

CalendarDao dao = CalendarDao.getInstance(); 
List<CalendarDto> list = dao.getDayList(user.getId(), dates);
/* 
for(int i = 0;i < list.size(); i++){
	CalendarDto c = list.get(i);
	System.out.println(c.toString());	
}
 */
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>callist.jsp</title>
</head>
<body>

<h2><%=year %>년 <%=month %>월 <%=day %>일 일정</h2>

<hr>

<div align="center">

<table border="1">
<col width="100"><col width="300"><col width="450"><col width="50">

<tr bgcolor="#09bbaa">
<td>번호</td><td>시간</td><td>제목</td><td>삭제</td>
</tr>

<%
for(int i = 0;i < list.size(); i++ ){
	CalendarDto dto = list.get(i);
	%>
	<tr>	
		<td><%=i + 1 %></td>
		<td><%=toDates(dto.getRdate()) %></td>
		<td>
			<a href="caldetail.jsp?seq=<%=dto.getSeq() %>">
				<%=dto.getTitle() %>
			</a>
		</td>
		<td>
			<form action="caldel.jsp" method="post">
				<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
				<input type="submit" value="일정삭제">
			</form>
		</td>	
	</tr>
	<%
}
%>
</table>

</div>


<a href="<%=String.format("%s?year=%s&month=%s", "calendarlist.jsp", year, month) %>">일정보기</a>



</body>
</html>








