<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
    <title>회원탈퇴</title>
    
    <style type="text/css">
        table{
            margin-left:; 
            margin-right:;
            margin-bottom: 30px;
            border:5px solid skyblue;
        }
        
        td{
            border:1px solid skyblue
        }
        
        #title{
            background-color:skyblue
        }
    </style>
    
    <style type="text/css">
    
html {
font-size: 18px;
}
body {
  color:black;
  font-family: "Questrial", sans-serif;
  background-color: #ffec63;
  background-image: linear-gradient(
      45deg,
      #ffd966 25%,
      transparent 25%,
      transparent 75%,
      #ffd966 75%,
      #ffd966
    ),
    linear-gradient(
      -45deg,
      #ffd966 25%,
      transparent 25%,
      transparent 75%,
      #ffd966 75%,
      #ffd966
    );
  background-size: 60px 60px;
  background-position: 0 0;
  animation: slide 4s infinite linear;
}

@keyframes slide {
  from {
    background-position: 0 0;
  }

  to {
    background-position: -120px 60px;
  }
}

.modal {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: auto;
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  padding: 1.6rem 3rem;
  border: 3px solid black;
  border-radius: 5px;
  background: #FAFAD2;
  box-shadow: 8px 8px 0 rgba(0, 0, 0, 0.2);
}
.message {
  font-size:1.1rem;
  margin-bottom: 1.6rem;
  margin-top: 0;
}
.btn {
  color:inherit;
    font-family:inherit;
  font-size: inherit;
  background: white;
  padding: 0.3rem 3.4rem;
  border: 3px solid black;
  margin-right: 2.6rem;
  box-shadow: 0 0 0 black;
  transition: all 0.2s;
}

.btn:last-child {
  margin: 0;
}

.btn:hover {
  box-shadow: 0.4rem 0.4rem 0 black;
  transform: translate(-0.4rem, -0.4rem);
}

.btn:active {
  box-shadow: 0 0 0 black;
  transform: translate(0, 0);
}

.options {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
    
</style>
    



<script type="text/javascript">
        // 비밀번호 미입력시 경고창
        function checkValue(){
            if(!document.deleteform.password.value){
                alert("비밀번호를 입력하지 않았습니다.");
                return false;
            }
        }
</script>
    
</head>
<body>
 	<div align="center">
    <br><br>
    <b><font size="6" color="gray">개인 정보 인증</font></b>
    <br><br><br>
 

 
<!--  <form name="deleteform" method="post">  -->
    
    <div class="modal">
    <table>
 			<tr>
                <td>Enter Password</td>
                <br><br>
               	<td><input type="password" id="userPwd" maxlength="50"></td>
            </tr>
	</table>
	<br>
  <div class="options">
    <button class="btn" onclick="javascript:window.location='main.jsp'">로비로 이동</button>
    <button  id="userPwdCheck" class="btn">탈퇴하기</button>
  </div>
  
  </div>
<!-- </form> -->


  

<script type="text/javascript">
alert("check");
$(function () {
    $("#userPwdCheck").click(function () {
    	
    	//alert("확인");
    	//alert($("#userPwd").val());
    	
       $.ajax({
          url: "member?param=Pwdcheck",
          type: "post",
          data : { "pwd":$("#userPwd").val()},
       //   dataType: "json",
          success:function(data){
             alert('success');
             if(data.msg == "noPwd"){
               location.href="message.jsp?msg=" + data.msg;
             }else if(data.msg == "YESPwd"){
            	 location.href="member?param=deleteMember";
             }                    
          },  
          error:function(){
             alert("error");
          }
          
       });  //ajax          
  }); //userPwdCheck          
});//function

</script>
    
</body>
</html>