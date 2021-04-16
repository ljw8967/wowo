<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

body {
  margin: 0;
  padding: 0;
  background: linear-gradient(#84fab0, #8fd3f4 ) fixed;
  font-family: sans-serif;
}

.progress {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}

.progress-container {
  transform: translateY(-50%);
  top: 50%;
  position: absolute;
  width: calc(100% - 200px);
  color: #FFF;
  padding: 0 100px;
  text-align: center;
}

.progress-container label {
  font-size: 200px;
  opacity: 0;
  display:inline-block;
}

@keyframes anim {
  0% {
    opacity: 0;
    transform: translateX(-300px);
  }
  33% {
    opacity: 1;
    transform: translateX(0px);
  }
  66% {
    opacity: 1;
    transform: translateX(0px);
  }
  100% {
    opacity: 0;
    transform: translateX(300px);
  }
}

@-webkit-keyframes anim {
  0% {
    opacity: 0;
    -webkit-transform: translateX(-300px);
  }
  33% {
    opacity: 1;
    -webkit-transform: translateX(0px);
  }
  66% {
    opacity: 1;
    -webkit-transform: translateX(0px);
  }
  100% {
    opacity: 0;
    -webkit-transform: translateX(300px);
  }
}

</style>

</head>
<body>

<!-- 초반 페이드 초 세팅 2.8초 -->
<meta http-equiv="refresh" content="2.8;url=main.jsp">


<div class="progress">
  <div class="progress-container">
    <label> T</label>
    <label> r</label>
    <label> i</label>
    <label> p</label>
    <label> i</label>
    <label> a</label>
    <label> .</label>
  </div>
</div>


<script type="text/javascript">

$(document).ready(function() {
	  var inputs = $(".progress-container").find($("label") );
	  
	  for(var i =0 ; i< inputs.length; i ++)
	  { 
	       var index = i +1;
	     var time = ((inputs.length)-i ) * 100;
	    $(".progress-container label:nth-child("+ index+")").css( "-webkit-animation", "anim 3s "+time+"ms infinite ease-in-out" );
	    $(".progress-container label:nth-child("+index+")").css( "-animation", "anim 3s "+time+"ms infinite ease-in-out" );
	  }

	})

</script>

</body>
</html>