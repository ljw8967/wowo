<%@page import="cal.CalendarDto"%>
<%@page import="java.util.List"%>
<%@page import="cal.CalendarDao"%>
<%@page import="util.UtilEx"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){
	%>	
	<script>
	alert('로그인 해 주십시오');
	location.href = "login.jsp";
	</script>	
	<%
}
mem = (MemberDto)ologin;
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<h4 align="right" style="background-color: #f0f0f0">환영합니다 <%=mem.getId() %>님</h4>

<h2>일정관리</h2><a href="main.jsp">로비로가기</a>

<%
	Calendar cal = Calendar.getInstance();
//	Calendar cal = new GregorianCalendar();

	cal.set(Calendar.DATE, 1);	// 2021/03/19 -> 2021/03/01
	
	String syear = request.getParameter("year");
	String smonth = request.getParameter("month");
	
	int year = cal.get(Calendar.YEAR);
	if(UtilEx.nvl(syear) == false){	// parameter가 넘어 와서 syear 값이 있는 경우
		year = Integer.parseInt(syear);
	}
	
	int month = cal.get(Calendar.MONTH) + 1;
	if(UtilEx.nvl(smonth) == false){
		month = Integer.parseInt(smonth);
	}
	
	if(month < 1){
		month = 12;
		year--;
	}
	if(month > 12){
		month = 1;
		year++;
	}
	
	cal.set(year, month - 1, 1);	// 연 월 일이 셋팅 완료
	
	// 요일
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	
	// <<	year--
	String pp = String.format("<a href='%s?year=%d&month=%d'><img src='image/left.gif' width='20px' heiht='20px'></a>", 
								"calendarlist.jsp", year-1, month);
	
	// <	month--
	String p = String.format("<a href='%s?year=%d&month=%d'><img src='image/prec.gif' width='20px' heiht='20px'></a>", 
								"calendarlist.jsp", year, month-1);
	
	// >	month++
	String n = String.format("<a href='%s?year=%d&month=%d'><img src='image/next.gif' width='20px' heiht='20px'></a>", 
								"calendarlist.jsp", year, month+1);
	
	// >>	year++
	String nn = String.format("<a href='%s?year=%d&month=%d'><img src='image/last.gif' width='20px' heiht='20px'></a>", 
								"calendarlist.jsp", year+1, month);
	
	CalendarDao dao = CalendarDao.getInstance();
	List<CalendarDto> list 
		= dao.getCalendarList(mem.getId(), year + UtilEx.two(month + ""));	
	
%>

<div align="center">

<table class="table table-bordered" style="width: 65%">
<col width="100"><col width="100"><col width="100"><col width="100">
<col width="100"><col width="100"><col width="100">

<tr height="120">
	<td colspan="7" align="center" style="padding-top: 20px">
		<%=pp %>&nbsp;&nbsp;<%=p %>&nbsp;&nbsp;
		
		<font color="black" style="font-size: 50px">
			<%=String.format("%d년&nbsp;&nbsp;%d월", year, month) %>
		</font>
		
		&nbsp;<%=n %>&nbsp;&nbsp;<%=nn %>		
	</td>
</tr>

<tr height="50" style="background-color: #f0f0f0;">
	<th class="text-center">일</th>
	<th class="text-center">월</th>
	<th class="text-center">화</th>
	<th class="text-center">수</th>
	<th class="text-center">목</th>
	<th class="text-center">금</th>
	<th class="text-center">토</th>
</tr>

<tr height="160" align="left" valign="top">
<%
// 위쪽 빈칸
for(int i = 1;i < dayOfWeek; i++){
	%>
	<td style="background-color: #cecece">&nbsp;</td>
	<%
}
// 날짜
int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
for(int i = 1;i <= lastday; i++){
	%>
	<td>
		<%=UtilEx.callist(year, month, i) %>&nbsp;&nbsp;<%=UtilEx.showPen(year, month, i) %>
		<%=UtilEx.makeTable(year, month, i, list) %>
	</td>	
	<%
	if( (i + dayOfWeek - 1) % 7 == 0 && i != lastday){
		%>	
		</tr><tr height="160" align="left" valign="top">
		<%
	}	
}
%>

<%--밑의 빈칸 --%>
<%
cal.set(Calendar.DATE, lastday);	// 그 달의 마지막 날짜로 셋팅
int weekday = cal.get(Calendar.DAY_OF_WEEK);
for(int i = 0;i < 7 - weekday; i++){
	%>
	<td style="background-color: #cecece">&nbsp;</td>	
	<%
}
%>

</tr>



</table>

</div>

</body>
</html>

















