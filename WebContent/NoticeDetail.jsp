
<%@page import="dto.CommentDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.MustEatDao"%>
<%@page import="dto.MemberDto"%>
<%@page import="dto.MustEatDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
    
    <%
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");
%>
<%


/* 
String getseq = request.getParameter("seq");
System.out.println(getseq);
int seq = Integer.parseInt(getseq);

String id = request.getParameter("id"); */



//MustEatDto dto = (MustEatDto)request.getAttribute("dedto");
//List<CommentDto> list = (List<CommentDto>)request.getAttribute("Clist");

%>




    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>DETAIL</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="robots" content="" />
	<meta name="description" content="LeMars : Blog HTML Template" />
	<meta property="og:title" content="LeMars : Blog HTML Template" />
	<meta property="og:description" content="LeMars : Blog HTML Template" />
	<meta property="og:image" content="http://lemars.dexignzone.com/xhtml/social-image.png" />
	<meta name="format-detection" content="telephone=no">
	<!-- 모달 부트스트랩 -->
	<!-- 모달 부트스트랩 -->

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<!-- 위에는 부트스트랩 -->

	<!-- 상단 작은 아이콘 -->
	<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" type="image/x-icon" href="images/favicon.png" />
	
	<!-- 페이지 타이틀 -->
	
	
	<!-- 모바일 사이즈 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 스타일 시트 -->
	<link rel="stylesheet" type="text/css" href="css/plugins.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/templete.css">
	<link class="skin" rel="stylesheet" type="text/css" href="css/skin/skin-1.css">
	
	<!-- 이전에 사용했던 시트 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha256-YLGeXaapI0/5IgZopewRJcFXomhRMlYYjugPLSyNjTY=" crossorigin="anonymous" />
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">


<style type="text/css">
.imgsize{ 
	
	width: 250px;
	height: 200px;



}

</style>
	


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <header class="site-header mo-left header style-1">
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
 <!-- header END -->
    <!-- Content -->
<body>
  <div class="page-content bg-white">
		<div class="content-block">
            <!-- About Us -->
            <div class="section-full content-inner bg-white">
                <div class="container">
					<div class="row">
						<div class="col-xl-9 col-lg-8 col-md-7 col-sm-12 col-12">
							<div class="blog-post blog-single blog-post-style-2 sidebar">
								<div class="section-head text-center">
									
									<h2 class="title-head">${dto.noTitle}</h2>
								</div>
								
								
								
								<div class="dlab-post-info">
									<div class="dlab-post-text text">
										
										
										<p style="font-size: 18px; "><b>Writer</b>&nbsp;&nbsp;${dto.noId}</p>
										<p style="font-size: 18px; "><b>Views</b>&nbsp;&nbsp;&nbsp;&nbsp;${dto.noReadc}</p>
										<p style="font-size: 18px; "><b>date</b>&nbsp;&nbsp;&nbsp;&nbsp;${dto.noDate}</p>
														                  
																                  
																                                  	
										
										
										<p>${dto.noContent}</p>
										
									

										
									</div>
									<div class="blog-card-info style-1 no-bdr">
							
								                         
										<div class="date">
										
										</div>
										<br><br>
										
										
										
										<div class="">
											<!-- detail 하단에 버튼 설정 -->
														<!-- 유저가 버튼을 누를시 맛집 사진 다운 -->
														
													
										</div>
									</div><!-- 날씨 묶음 -->
									
									
									<br><br>
							
									
									
										<div class="dtnline" align="center">
														
														<!-- 글목록으로 다시가기 -->
														&nbsp;&nbsp;&nbsp;
														<button style="font-size: 15px" class="btn btn-secondary" onclick="golist()">GO NOTICE</button>
														
														
														<!-- 로그인아이디와 사용자 아이디가 동일할 시에 버튼 생성 -->
														 <c:if test="${login.auth == 1 }"> 
														
														
															<!-- 수정 -->	
															&nbsp;&nbsp;&nbsp;
															<button style="font-size: 15px" class="btn btn-secondary" onclick = "updateNotice(${dto.noseq})">CONTENT UPDATE</button>
														
															&nbsp;&nbsp;&nbsp;
														
														 	<!-- 삭제 -->	
														  	<!-- Button to Open the Modal -->
														  
														  	<button style="font-size: 15px" type="button" class="btn btn-secondary" data-toggle="modal" data-target="#myModal">
														  	  DELETE
														  	</button>
														
														  <!-- The Modal -->
														  		<div class="modal fade" id="myModal">
														   	 	<div class="modal-dialog">
														     	<div class="modal-content">
														      
														        <!-- Modal Header -->
														        <div class="modal-header">
														          <h4 class="modal-title">Delete message</h4>
														          <button type="button" class="close" data-dismiss="modal">&times;</button>
														        </div>
														        
														        <!-- Modal body -->
														        <div class="modal-body">
														          	<h5>정말로 게시물을 삭제하시겠습니까?</h5>
														          	<h5>삭제 시 복구할 수 없습니다.</h5>
														        </div>
														        
														        <!-- Modal footer -->
														        <div class="modal-footer">
														        	
														          <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteNotice(${dto.noseq})">네, 삭제하겠습니다</button>
														       		
														          <br>
														          <button type="button" class="btn btn-danger" data-dismiss="modal">아니요</button>
														        </div>
														        
														      </div>
														    </div>
														  </div>
														
														
														
														
														</c:if>	 
														</div><!-- <div class="dtnline"> END-->
									
									
								</div>
								</div>
							
								
							<div class="">	
							
				</div>
			</div><!-- row끝 -->
			
						<div class="col-xl-3 col-lg-4 col-md-5 col-sm-12 col-12">	
							<div class="side-bar p-l30 sticky-top">
								<div class="widget widget-newsletter text-center">
									<form class="dzSubscribe dezPlaceAni" action="script/mailchamp.php" method="post">
										<div class="news-back form-style">
											<h1 style="color: #F7FE2E; text-shadow:3px 3px 3px rgb(0,0,0);">Tripia</h1>
											<h1 style="color: #F7FE2E; text-shadow:3px 3px 3px rgb(0,0,0);">today's weather</h1>
											<div class = 'weather'>
											   <div class ='CurrIcon'><img id ="CurrIcon" alt = "" src = ""></div>
											    <div style="font-size: 25px" class = 'CurrTemp'></div>
											    <div style="font-size: 20px" class = 'City'></div>
											    
											    <select  id = "place" style="width:135px;height:35px; font-size: 15px; background-color:#F7FE2E ">
											    	<option value = "">Choose a region </option>
											    	<option value = "Incheon">인천</option>
											    	<option value = "Seoul">서울</option>
											    	<option value = "Busan">부산</option>
											    	<option value = "Jeju">제주</option>
											    	<option value = "Gangneung">강릉</option>
											    	<option value = "Sokcho">속초</option>
											    	<option value = "Gyeongju">경주</option>
											    	<option value = "Yeosu">여수</option>
											    	<option value = "Ulsan">울산</option>
											    	<option value = "Daejeon">대전</option>
											    </select>
											    
											</div>
											
											<!-- <div class="input-group">
												<input name="dzEmail" required="required" type="email" class="form-control" placeholder="Your Email">
												<div class="input-group-append">
											<button name="submit" value="Submit" type="submit" class="btn"><i class="la la-paper-plane"></i></button>
										</div>
											</div> -->
											<div class="dzSubscribeMsg"></div>
										</div>
									</form>
								</div>
								<div class="widget widget_gallery gallery-grid-2">
									<h6 class="widget-title"><span>travel</span></h6>
									<ul>
										<li>
											<a href="#">
												<div class="dlab-post-thum dlab-img-overlay1 dlab-img-effect">
													<img style="width: 107.22px; height: 107.22px;" src="images/gallery/widget-gallery/adsf.jpg" alt="">
												</div>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="dlab-post-thum dlab-img-overlay1 dlab-img-effect">
													<img style="width: 107.22px; height: 107.22px;" src="images/gallery/widget-gallery/jeju.jpg" alt="">
												</div>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="dlab-post-thum dlab-img-overlay1 dlab-img-effect">
													<img style="width: 107.22px; height: 107.22px;" src="images/gallery/widget-gallery/summer.jpg" alt="">
												</div>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="dlab-post-thum dlab-img-overlay1 dlab-img-effect">
													<img style="width: 107.22px; height: 107.22px;" src="images/gallery/widget-gallery/beach.jpg" alt="">
												</div>
											</a>
										</li>
									</ul>
								</div>
							<!-- 	<div class="widget widget-social">
									<h6 class="widget-title"><span>Social</span></h6>
									<ul>
										<li><a href="#"><i class="fa fa-facebook"></i></a></li>
										<li><a href="#"><i class="fa fa-twitter"></i></a></li>
										<li><a href="#"><i class="fa fa-snapchat-ghost"></i></a></li>
										<li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
										<li><a href="#"><i class="fa fa-instagram"></i></a></li>
									</ul>
									<div class="advertise m-b20">
										<img src="images/add.png" alt=""/>
									</div>
									<div class="fb-social relative">
										<img src="images/default-social.jpg"  alt=""/>
										<div class="facebook-like">
											<button class="btn radius-xl">Like on facebook</button>
										</div>
									</div>
								</div> -->
								<!-- <div class="widget widget-stories">
									<h6 class="widget-title"><span>Latest Post</span></h6>
									<div class="post-carousel owl-carousel owl-theme owl-none owl-btn-3">
										<div class="item">
											<img src="images/gallery/widget-gallery/pic5.jpg" alt=""/>
										</div>
										<div class="item">
											<img src="images/gallery/widget-gallery/pic6.jpg" alt=""/>
										</div>
										<div class="item">
											<img src="images/gallery/widget-gallery/pic7.jpg" alt=""/>
										</div>
									</div>
								</div> -->
							<!-- 	<div class="widget widget_tag_cloud">
									<h6 class="widget-title"><span>Tags</span></h6>
									<div class="tagcloud text-center"> 
										<a href="#">Blog</a>
										<a href="#">Photography</a>
										<a href="#">Style</a>
										<a href="#">Instagram</a>
										<a href="#">Trend</a>
										<a href="#">Lifestyle</a>
										<a href="#">Fashion</a>
										<a href="#">Travel</a>
										<a href="#">Europe</a>
									</div>
								</div> -->
							</div>
						</div>
					</div>
				</div>
            </div> 
			<!-- About Us End
			subscribe -->
			
			<!-- subscribe end -->
        </div>
		<!-- contact area END -->
</div>
  <!-- Content END -->

 






<script type="text/javascript">

/* $(function () {
	$("#btn").click(function () {
		alert('click');		//comment?param=writeAf
		$("#frm").attr("action", "comment").submit();		
	});
}) */


function deleteNotice(seq) {
	location.href="notice?&param=delete&seq=" + seq;

}

	

function updateNotice(seq) {
	location.href="notice?param=update&seq=" + seq;
}

function golist() {
	location.href="notice?param=list";
}
	


</script>




<br><br>
<!-- Footer -->
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
						<h6 class="m-b30 footer-title"><span>Recent Post</span></h6>
						<div class="widget recent-posts-entry">
							<div class="widget-post-bx">
								<div class="widget-post clearfix">
									<div class="dlab-post-media"> <img src="images/blog/recent-blog/pic1.jpg" alt=""> </div>
									<div class="dlab-post-info">
										<div class="dlab-post-header">
											<h6 class="post-title"><a href="post-quote.html">Winter Fairytale</a></h6>
										</div>
										<div class="dlab-post-meta">
											<ul>
												<li class="post-date">25 January, 2020</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="widget-post-bx">
								<div class="widget-post clearfix">
									<div class="dlab-post-media"> <img src="images/blog/recent-blog/pic2.jpg" alt=""> </div>
									<div class="dlab-post-info">
										<div class="dlab-post-header">
											<h6 class="post-title"><a href="post-link.html">Fruit & Flamingos</a></h6>
										</div>
										<div class="dlab-post-meta">
											<ul>
												<li class="post-date">25 January, 2020</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="widget-post-bx">
								<div class="widget-post clearfix">
									<div class="dlab-post-media"> <img src="images/blog/recent-blog/pic3.jpg" alt=""> </div>
									<div class="dlab-post-info">
										<div class="dlab-post-header">
											<h6 class="post-title"><a href="post-gallery.html">Hawai Beaches</a></h6>
										</div>
										<div class="dlab-post-meta">
											<ul>
												<li class="post-date">25 January, 2020</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6">
						<h6 class="m-b30 footer-title"><span>My Blogs</span></h6>
						<a class="video widget relative popup-youtube overlay-black-middle" href="https://www.youtube.com/watch?v=VjlATH_rzYg">
							<img src="images/footer-video.jpg" alt=""/>
							<span class="play-video"><i class="la la-play"></i></span>
						</a>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-12">
						<h6 class="m-b30 footer-title"><span>Subscribe</span></h6>
						<ul class="d-flex widget-social-ic">
							<li><a href="#" class="site-button-link"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#" class="site-button-link"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#" class="site-button-link"><i class="fa fa-pinterest-p"></i></a></li>
							<li><a href="#" class="site-button-link"><i class="fa fa-instagram"></i></a></li>
						</ul>
					</div>
				
				
				
				
				
				
				<!--여기다넣기  -->
				</div><!-- ROW END -->
			</div>
		</div>
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<div class="col-12 text-center">
						<span>Copyright © 2020 DexignZone.</span>
					</div>
				</div>
			</div>
		</div>
	</footer>
    <!-- Footer END-->
<%-- <%
if(dto.getId().equals(dto.getId())) {
%>
		
<input type="button" name ="btndown" value = "파일다운로드"
				onclick = "location.href='filedown?filename=<%=dto.getNewFilename()%>&seq=<%=dto.getSeq()%>'">
		
<button onclick = "deletepds(<%=dto.getSeq() %>)">파일 삭제</button>
<button onclick = "location.href='pdsupdate.jsp?seq=<%=dto.getSeq()%>'">파일 수정</button>
<%


}
%>

<button onclick = "location.href='pdslist.jsp'">자료실로 가기</button>

</div>

<script type="text/javascript">
function deletepds(seq){
	alert("삭제하겠습니다");
	location.href = "pdsdelete.jsp?seq="  +seq + "&filename=" + <%=dto.getFilename()%> + "";
}

</script> --%>
<script type="text/javascript">

 var place;/// = $("#place option:selected").val();

$(document).ready(function(){
	
	$("#place").change(function(){
	
	//	alert($("#place option:selected").val());
	    place = $("#place option:selected").val();
		let weather = 'http://api.openweathermap.org/data/2.5/weather?q='+ place +'&appid=0da614b3fe6226c81e152090ea42602a&units=metric'
	
	    $.ajax({
	        url: weather,
	        dataType: 'json',
	        type: 'GET',
	        success: function(data){
	        	
	            var $Icon = (data.weather[0].icon);
	            var $Temp = Math.floor(data.main.temp) + 'º';
	            var $city = data.name;
	
	             $('#CurrIcon').attr("src",'http://openweathermap.org/img/wn/'+ $Icon + '@2x.png');
	             $('.CurrTemp').html($Temp);
	             $('.City').html($city);
	             
	             //prepand, append
	        }
	    });
	});
});

</script>

</body>
</html>