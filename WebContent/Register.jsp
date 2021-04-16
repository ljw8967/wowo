<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Tripia Register</title>

    <!-- Icons font CSS-->
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="css/main.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
        <div class="wrapper wrapper--w790">
            <div class="card card-5">
                <div class="card-heading">
                    <h2 class="title">Event Registration Form</h2>
                </div>
                <div class="card-body">
                    <form method="post" id="register" action="member?param=regiAf" enctype= multipart/form-data>
                    	<!-- 이쪽에 사진을 넣자 -->
                    	<ul>
 						   <li class="img" style="list-style: none; margin-left : 200px">
                                <div id="image_preview">
                                    <img style="width: 100px; height: 100px; margin-left: 80px;" src="profile.png" alt="프로필사진"  style="width:126px; height:165px;">
                                </div><!-- "/img.png" -->
                                <h4 style="margin-left: 35px;">INSERT YOUR PROFILE</h4>
                                <div class="f_box">
                                        <label for="img"></label>
                                        <input type="file" name="photo-user" id="img"  name="bf_file[]">
                                </div>
                            </li>
				</ul>
                       <script>
                            // 이미지 업로드  
                            $('#img').on('change', function() {
                            ext = $(this).val().split('.').pop().toLowerCase(); //확장자
                            //배열에 추출한 확장자가 존재하는지 체크
                            if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                                resetFormElement($(this)); //폼 초기화
                                window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
                            } else {
                                file = $('#img').prop("files")[0];
                                blobURL = window.URL.createObjectURL(file);
                                $('#image_preview img').attr('src', blobURL);
                                $('#image_preview').slideDown(); //업로드한 이미지 미리보기 
                                $(this).slideUp(); //파일 양식 감춤
                            }
                            });
 						</script>
                    	<br><br>
                    	<!-- 이쪽에 사진을 끝내자 -->
                        <div class="form-row m-b-55">
                            <div class="name">ID</div>
                            <div class="value">
                                <div class="row row-space">
                                    <div class="col-2">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" id="userID" type="text" name="userId">
                                            <label class="label--desc">Create Your Unique ID!</label>
                                        </div>
                                    </div>                                    
                                </div>
                            </div>
                        </div>
                        <!-- 아이디체크 %%% 고장 -->
                        <button style="color:green; padding-left: 0px;" type="button" id="userIdCheck" class="btn btn-outline-info">ID CHECK</button>       
                   		 <a id="idcheck"></a>    
                        <!-- 아이디체크 끝  -->
                        <div class="form-row">
                            <div class="name">PASSWORD</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" id="userPwd" type="text" name="userPwd">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">NICKNAME</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" id="userName" type="text" name="userName">
                                </div>
                            </div>
                        </div>
                        <div class="form-row m-b-55">
                            <div class="name">E-MAIL</div>
                            <div class="value">
                                <div class="row row-refine">                                 
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="email" id="userEmail" name="userEmail">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                     
                        <!-- 구글 중복 -->
                        <div id="google_recaptha">
						<script src='https://www.google.com/recaptcha/api.js'></script>
						<div class="g-recaptcha" data-sitekey="6Lehu58aAAAAAMsxlmaEG8a5TjZw9BEUHmhqHu9X"></div>
						</div>
						<!-- 구글중복끝 -->
<br>
<br>
                        <div>
                            <button class="submit btn btn--radius-2 btn--red" type="submit">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Jquery JS-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/datepicker/moment.min.js"></script>
    <script src="vendor/datepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="js/global.js"></script>
    
    <!-- 저장된 아이디 -->
        <script type="text/javascript">	

        let user_id = $.cookie("user_id");
        if(user_id != null){   // 저장된 id가 있음
      
           $("#_userId").val( user_id );
           $("#chk_save_id").attr("checked", "checked");
       
        }

        $("#chk_save_id").click(function() {
           
           if( $("#chk_save_id").is(":checked") ){
    
              if( $("#_userId").val().trim() == "" ){
                 alert('id를 입력해 주십시오');
                 $("#chk_save_id").prop("checked", false);         
              }else{
                 // 쿠키를 저장
                 $.cookie("user_id", $("#_userId").val().trim(), { expires:7, path:'./'});   
              }
           }
           else{
              $.removeCookie("user_id", { path:'./' });
           }
           
        }); 
     
     /* 중복체크 */
  $(function () {
			
        	$("#userIdCheck").click(function () {
        		//alert($("#userId").val());
        		$.ajax({
        			url: "member",
        			type: "post",
        			data : { "id":$("#userID").val(), "param":"idcheck" },
        			//dataType: "json",
        			success:function(data){
        				
        				if(data.msg == "YES"){
        					$("#idcheck").css("color", "#00BFFF");
        					$("#idcheck").html("available");
        				}else{
        					$("#idcheck").css("color", "#DF0101");
        					$("#idcheck").html("cannot use");
        				}        				
        			},  
        			error:function(){
        				alert("error");
        			}
        			
        		});				
			});        	
		});

		</script>
    
    
    
    

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->