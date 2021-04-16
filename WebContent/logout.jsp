<!-- 로그아웃 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>

<%
//HttpSession userSession = request.getSession();
session.invalidate();
%>

<script type="text/javascript">
alert("로그아웃 되었습니다.");
location.href="main.jsp";
</script>

</body>
</html>