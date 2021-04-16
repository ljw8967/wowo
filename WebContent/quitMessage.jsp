
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  
	String msg = request.getParameter("msg");
	
msg = "quitYES";
System.out.println(msg);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if(msg.equals("quitYES")){
%>
	<script type="text/javascript">
	alert('성공적으로 회원 탈퇴되었습니다');
	location.href = 'member?param=main';
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert('삭제되지 않았습니다.');
	location.href = 'member?param=main';
	</script>
<%
}
%>

<%-- 
test
<%=msg %> 
				
	<%
if(msg.equals("quitYES")){
	%>
	<script type="text/javascrispt">
	alert('성공적으로 회원 탈퇴되었습니다');
	location.href = 'member?param=main';
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
	alert('삭제되지 않았습니다.');
	location.href = 'member?param=main';
	</script>
	<%
}
%>
			  --%>
			
			
			
</body>
</html>