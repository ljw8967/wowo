<%@page import="cal.CalendarDto"%>
<%@page import="cal.CalendarDao"%>
<%@page import="java.util.Calendar"%>
<%@page import="cal.CalendarDto"%>
<%@page import="cal.CalendarDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

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

System.out.println("오류 확인");


%>


<%
String getseq = request.getParameter("seq");
System.out.println("오류 확인");
int seq = Integer.parseInt(getseq);

String id = request.getParameter("id");


CalendarDao dao = CalendarDao.getInstance();

CalendarDto dto = dao.caldetail(seq);

%>

</head>
<body>


<%
String sdate = dto.getRdate();
String year = sdate.substring(0, 4);
String month = sdate.substring(4, 6);
String day = sdate.substring(6, 8);
String hour = sdate.substring(8, 10);
String min = sdate.substring(10, 12);

%>

<h1>일정 디테일 페이지</h1>
<div class="container">
<table class="table table-hover">
<col width="200"><col width="400">
<tr>
<td>작성자id</td>
<td><%=dto.getId() %></td>
</tr>

<tr>
<td>제목</td>
<td><input type = "text" value = "<%=dto.getTitle() %>"></td>

</tr>
<tr>
<th>일정</th>
<td><input type = "text" value = "<%=year %>년<%=month%>월<%=day%>일<%=hour%>시<%=min%>분"></td>
</tr>		

<tr>
<td>내용</td>
<td><textarea rows="20" cols="50px" ><%=dto.getContent() %></textarea></td>
</tr>



</table>

<br><br>
<%
if(dto.getId().equals(mem.getId())) {
%>
<button onclick = "deletebbs(<%=dto.getSeq() %>)">일정삭제</button>
<button onclick = "updatee(<%=dto.getSeq() %>)">일정수정</button>
<%
}
%>

<button onclick = "calenlist()">일정관리</button>

</div>

<script type="text/javascript">

function updatee(seq){
//	alert("수정확인");
	location.href ="calupdate.jsp?seq=" + seq;


	/* + "&year=" + year + "&month=" + month + "&day=" + day +"&hour=" + hour + "&min=" + min; */
}

function deletebbs(seq){
	location.href = "caldel.jsp?seq=" + seq;
}

function calenlist(){
	location.href = "calendarlist.jsp"
}



</script>


</body>
</html>