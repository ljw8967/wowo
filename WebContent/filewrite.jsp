<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dao.PdsDao"%>
<%@page import="dto.PdsDto"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
    
    
    
    
   <%
    MemberDto mem = (MemberDto)session.getAttribute("login");
   PdsDto pds = new PdsDto();
    %>
    
    
    
   
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="robots" content="" />
	<meta name="description" content="LeMars : Blog HTML Template" />
	<meta property="og:title" content="LeMars : Blog HTML Template" />
	<meta property="og:description" content="LeMars : Blog HTML Template" />
	<meta property="og:image" content="http://lemars.dexignzone.com/xhtml/social-image.png" />
	<meta name="format-detection" content="telephone=no">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
<!-- 위에는 부트스트랩 -->
	
	<!-- 상단 작은 아이콘 -->
	<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" type="image/x-icon" href="images/favicon.png" />
	
	<!-- 페이지 타이틀 -->
	<title>Tripia</title>
	
	<!-- 모바일 사이즈 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 스타일 시트 -->
	
	
	<style type="text/css">
	
	/* 페이드 타이머 */
	#test {
    animation: fadein 1.5s;
    -moz-animation: fadein 2s; /* Firefox */
    -webkit-animation: fadein 1.5s; /* Safari and Chrome */
    -o-animation: fadein 2s; /* Opera */
    
}
@keyframes fadein {
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-moz-keyframes fadein { /* Firefox */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-webkit-keyframes fadein { /* Safari and Chrome */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-o-keyframes fadein { /* Opera */
    from {
        opacity:0;
    }
    to {
        opacity: 1;
    }
}


	</style>
	
	
	<link rel="stylesheet" type="text/css" href="css/plugins.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/templete.css">
	<link class="skin" rel="stylesheet" type="text/css" href="css/skin/skin-1.css">
	

	
	
	
<title>tripia</title>
</head>






<body id="test">


<div class="page-wraper">

<div id="loading-area"></div>
	<!-- header -->
    <header class="site-header mo-left header style-1">
		<!-- main header -->
        <div class="sticky-header main-bar-wraper navbar-expand-lg">
        	<!-- 상단을 담는 큰 박스-->
            <div class="main-bar clearfix" style="padding-top: 15px;padding-bottom: 15px;">
            	<!-- 상단바 전체 -->
                <div class="container" style="margin-left : 240px">
					<div class="header-content-bx no-bdr" style="width: 1390px; padding: 0px">
						<!-- 웹사이트 로고 -->
						<div class="logo-header"  style="margin-right: 50px">
							<a href="index.jsp"><img src="images/logo.png" alt=""/></a>
						</div>
						<!-- nav toggle button 버튼 -->
						<button class="navbar-toggler collapsed navicon justify-content-end" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
							<span></span>
							<span></span>
							<span></span>
						</button>
						<!-- main nav 메인 상단바-->
						<div class="header-nav navbar-collapse collapse justify-content-center" id="navbarNavDropdown" style=" margine-right : 50px " >
							<div class="logo-header">
								<a href="index.jsp"><img src="" alt="images/logo-white.png"/></a>
							</div>
							<ul class="nav navbar-nav">	
								<!-- 추천 여행지 -->
								<li>
									<a href="TpList?param=Tplistr">Recommended <i class="fa fa-chevron-down"></i></a>
								</li>
								<!-- 자유게시판  -->
								<li class="has-mega-menu post-slider life-style">
									<a href="bbs?param=bbslist">Forum<i class="fa fa-chevron-down"></i></a>
									</li>
								<!-- 여행지 목록  -->	
								<li>
									<a href="TpList?param=Tplist">Destination<i class="fa fa-chevron-down"></i></a>
									<ul class="sub-menu">
										<li><a href="TpList?param=Tplist1">서울</a></li>
										<li><a href="TpList?param=Tplist2">제주</a></li>
										<li><a href="TpList?param=Tplist3">인천</a></li>
										<li><a href="TpList?param=Tplist4">부산</a></li>
										<li><a href="TpList?param=Tplist5">강원도</a></li>
									</ul>
								
								</li>
								<!-- 트리피아는? -->
								<li>
									<a href="about-us.jsp">Tripia?</a>
									<!-- 공지사항 -->
									<ul class="sub-menu">
										<li><a href="notice?param=list">Notice</a></li>
										
									</ul>
								</li>
								<li>
									<a href="match?param=Matchlist">Find Mate</a> <!-- 매칭 -->
								</li>

								<!-- 로그인 -->
							
								<li>
									
										
									<c:choose>
									<c:when test="${login.id == null}">  
									
									<a href="login_resist_form.jsp">Login</a>
									</c:when>
									
									<c:when test="${login.id != null}">
									${login.id}님 반갑습니다.
									<a href="logout.jsp">Logout</a>
									<a href="member?param=mapageAf&userID=${login.id}">My page</a>
									<a href="qna?param=qnalist">Q&A</a>									
									</c:when>	
									</c:choose>
									
								</li>
							</ul>

					
						
						

				</div>
            </div>
        </div>
	</div>
</div>
		<!-- 전체 공통 header 끝 -->

        <!-- main header END -->
    </header>
					
</div>

<h1 align="center">
</h1>
<div align="center" class = "container" border="1">

<form action="fileuse?param=filewrite" method="post" enctype="multipart/form-data" >

<input type="hidden" name="seq" value="<%=mem.getSeq()%>">

<input type="hidden" name="id" value="<%=mem.getId() %>" readonly="readonly" > 


<div align="center" style="padding-top: 30px;" >

<h2>write</h2>
<br>
<table class="table table-bordered" style="width: 700px" >
	
	<thead class="thead-dark">
	
	<!-- 
	<tr>
		 <th align="right">사진 업로드</th>
		<th>사진올리기</th>
		<td align="center">
			<input type="file" name="filename" style="width: 400px" >
		</td>
	</tr> -->
		
	<tr>
		<th>닉네임</th>
		<td>
			<input type="text" name="name" value="<%=mem.getName() %>" readonly="readonly">
		</td>
	</tr>
	
	<tr >
		<th>제목</th>
		<td>
			<input type="text" name="title" size="60px">
		</td>
	</tr>
	
	
	<tr>
		<th>내용</th>
		<td >
		<textarea rows="10" cols="60" name="content" ></textarea>
		</td>
	</tr>

	<tr>
		<th align="center">사진</th>
		<td >
			<input type="file" class="btn btn-light" name="fileload" style="width:400px" >
		</td>
	</tr>
	
	
<%-- 
<% 		
Date d = new Date();
       	  
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      	 
String time = df.format(d);
   		
session.setAttribute("time", time);      
         %> --%>




	
</thead>
</table>
</div>
		<input type="submit" class="btn btn-light"  value="올리기">
	<br><br>



</form> 
</div>
<footer class="site-footer">
		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-xl-3 col-lg-2 col-md-6 d-md-none d-lg-block">
						<div class="widget">
							<img src="images/logo-white.png" alt=""/>
						</div>
					</div>
					<div class="col-xl-3 col-lg-4 col-md-6">
						<!-- footer 최근에 올라온 여행지 Main -->
						<h6 class="m-b30 footer-title"><span>최근에 올라온 여행지</span></h6>
						<div class="widget recent-posts-entry">
						
						<!-- footer 최근에 올라온 여행지 1 -->
							<div class="widget-post-bx">
								<div class="widget-post clearfix">
									<div class="dlab-post-media"> <img src="images/blog/recent-blog/pic1.jpg" alt=""> </div>
									<div class="dlab-post-info">
										<div class="dlab-post-header">
											<h6 class="post-title"><a href="#">전주 한옥마을</a></h6>
										</div>
										<div class="dlab-post-meta">
											<ul>
												<li class="post-date">27 May, 2021</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							
							<!-- footer 최근에 올라온 여행지 2 -->
							<div class="widget-post-bx">
								<div class="widget-post clearfix">
									<div class="dlab-post-media"> <img src="images/blog/recent-blog/pic2.jpg" alt=""> </div>
									<div class="dlab-post-info">
										<div class="dlab-post-header">
											<h6 class="post-title"><a href="#">여수 오동도</a></h6>
										</div>
										<div class="dlab-post-meta">
											<ul>
												<li class="post-date">25 May, 2021</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							
							<!-- footer 최근에 올라온 여행지 3 -->
							<div class="widget-post-bx">
								<div class="widget-post clearfix">
									<div class="dlab-post-media"> <img src="images/blog/recent-blog/pic3.jpg" alt=""> </div>
									<div class="dlab-post-info">
										<div class="dlab-post-header">
											<h6 class="post-title"><a href="#">강릉 안목해변</a></h6>
										</div>
										<div class="dlab-post-meta">
											<ul>
												<li class="post-date">21 May, 2021</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6">
						
						<!-- footer 비디오 재생 부분 -->
						<h6 class="m-b30 footer-title"><span>여행 비디오</span></h6>
						<a class="video widget relative popup-youtube overlay-black-middle" href="https://youtu.be/3P1CnWI62Ik">
							<img src="images/footer-video.jpg" alt=""/>
							<span class="play-video"><i class="la la-play"></i></span>
						</a>
					</div>
					
					<!-- footer 구독 버튼 (페이스북 트위터 등등) -->
					<div class="col-xl-3 col-lg-3 col-md-12">
						<h6 class="m-b30 footer-title"><span>구독</span></h6>
						<ul class="d-flex widget-social-ic">
							<li><a href="#" class="site-button-link"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#" class="site-button-link"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#" class="site-button-link"><i class="fa fa-pinterest-p"></i></a></li>
							<li><a href="#" class="site-button-link"><i class="fa fa-instagram"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<div class="col-12 text-center">
						<span>Copyright © 2021 Tripia.</span> <!-- 맨하단 footer 글씨 -->
					</div>
				</div>
			</div>
		</div>
	</div>
	</footer>
    <!-- Footer END-->
    <button class="scroltop fa fa-chevron-up" ></button>
</div>
<!-- JAVASCRIPT FILES ========================================= -->
<script src="js/jquery.min.js"></script><!-- JQUERY.MIN JS -->
<script src="plugins/bootstrap/js/popper.min.js"></script><!-- BOOTSTRAP.MIN JS -->
<script src="plugins/bootstrap/js/bootstrap.min.js"></script><!-- BOOTSTRAP.MIN JS -->
<script src="plugins/bootstrap-select/bootstrap-select.min.js"></script><!-- FORM JS -->
<script src="plugins/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script><!-- FORM JS -->
<script src="plugins/magnific-popup/magnific-popup.js"></script><!-- MAGNIFIC POPUP JS -->
<script src="plugins/counter/waypoints-min.js"></script><!-- WAYPOINTS JS -->
<script src="plugins/counter/counterup.min.js"></script><!-- COUNTERUP JS -->
<script src="plugins/imagesloaded/imagesloaded.js"></script><!-- IMAGESLOADED -->
<script src="plugins/masonry/masonry-3.1.4.js"></script><!-- MASONRY -->
<script src="plugins/masonry/masonry.filter.js"></script><!-- MASONRY -->
<script src="plugins/owl-carousel/owl.carousel.js"></script><!-- OWL SLIDER -->
<script src="js/custom.js"></script><!-- CUSTOM FUCTIONS  -->
<script src="js/dz.carousel.js"></script><!-- SORTCODE FUCTIONS -->
<script src="js/dz.ajax.js"></script><!-- CONTACT JS  -->
</body>
</html>


