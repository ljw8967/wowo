<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%
String msg = request.getParameter("msg");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if(msg.equals("pwdNull")){
%>
<script type="text/javascript">
alert("정확한 비밀번호를 입력해주십시오.");
location.href = "member?param=mapageAf&userID=${login.id}";
</script>

<%
}
%>

<%
if(msg.equals("OK")){
	%>
	<script type="text/javascript">
	alert('성공적으로 추가되었습니다');
	location.href = 'main.jsp';
	</script>
	<%
}else if(msg.equals("NO")){
	%>
	<script type="text/javascript">
	alert('추가되지 않았습니다. 다시 작성해 주십시오');
	location.href = 'member?param=regi';
	</script>
	<%
}else if(msg.equals("YESUPDATE")){
%>	
	<script type="text/javascript">
	alert('내용이 변경되었습니다');
	location.href = 'MustEat?param=MustEatlist';
	</script>
<%
System.out.println(msg);
}else if(msg.equals("YESMATCHUPDATE")){
%>
<script type="text/javascript">
	alert('내용이 변경되었습니다');
	location.href = 'match?param=Matchlist';
	</script>
<%

}else if(msg.equals("DELETEMATCH")){
%>
<script type="text/javascript">
	alert('성공적으로 삭제 되었습니다');
	location.href = 'match?param=Matchlist';
	</script>
	<%
}else if(msg.equals("notEqual")){
	%>
	<script type="text/javascript">
	alert("정보가 일치하지 않습니다. 다시 입력해주세요")
	location.href = "login_resist_form.jsp";
	</script>
	
<%	
}
else if(msg.equals("updateInfo")){
	System.out.println("~~~~~~~~~~~~~~msg:" + msg);
%>
	 <script type="text/javascript">
	alert('회원정보 수정이 되었습니다.');
//	 session.invalidate("login");
	location.href = 'main.jsp';	
	</script> 

<% 
}
else if(msg.equals("DELETE")){
%>
   <script type="text/javascript">
   alert('삭제되었습니다.');
   location.href = 'member?param=memlist';
   </script>
<%
} 
	if(msg.equals("NICE")){
%>
<script type="text/javascript">
	alert('성공적으로 추가되었습니다');
//	location.href = 'bbslist.jsp';
	location.href = 'bbs?param=bbslist';
	</script>
<%
	}else{
%>
<script type="text/javascript">
	alert('실패하였습니다');
	location.href = 'bbs?param=bbslist';
	</script>
<%
	}
%>


</body>
</html>