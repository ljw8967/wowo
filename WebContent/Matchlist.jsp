<%@page import="dao.MustEatDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
 <%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){
   %>   
   <script>
   alert('로그인 해 주십시오');
   location.href = "member?param=login";
   </script> 
     
   <%
}
mem = (MemberDto)ologin;
%> 

<%
String choice = request.getParameter("choice");
String search = request.getParameter("search");
if(choice == null){
   choice = "";
}
if(search == null){
   search = "";
}
%>

  <%  

    //MustEatDao dao = MustEatDao.getInstance();
    
    //List<MustEatDto> list = (List<MustEatDto>)request.getAttribute("list");
   
    
    //MemberDto mem= (MemberDto)session.getAttribute("login");
    
  
   
   
    %>


<html>
<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Match</title>
    <style>

.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}

</style>
    
    
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css" />
    <link rel="stylesheet" href="cards-gallery/cards-gallery.css">
     <link rel="stylesheet" href="cards-gallery/compact-gallery.css">
    <!-- 위까지 카드 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="keywords" content="" />
   <meta name="author" content="" />
   <meta name="robots" content="" />
   <meta name="description" content="LeMars : Blog HTML Template" />
   <meta property="og:title" content="LeMars : Blog HTML Template" />
   <meta property="og:description" content="LeMars : Blog HTML Template" />
   <meta property="og:image" content="http://lemars.dexignzone.com/xhtml/social-image.png" />
   <meta name="format-detection" content="telephone=no">
   
   <link rel = "stylesheet" href="./css/list.css">
   <style type="text/css">
   
   
   </style>
   
<!-- 위에는 부트스트랩 -->

   <!-- 상단 작은 아이콘 -->
   <link rel="icon" href="images/favicon.ico" type="image/x-icon" />
   <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png" />
   
   <!-- 페이지 타이틀 -->
   <title>Tripia</title>
   
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

   <script type="text/javascript">
   $(document).ready(function() {
   // 검색어 있는 경우   
   let search = ${search };   // Java Script에서 EL tag는 접근이 가능합니다.
   if(search == "") return;
   
   // select
   let obj = document.getElementById("choice");
   obj.value = ${choice };
   obj.setAttribute("selected", "selected");
});
</script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


</head>
<body style="background-color: #e8eef7">
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

                            
                            <!-- 사이드바 오른쪽 -->
<div class="page-content bg-white">

	<div class="content-block">
	
		<!-- About Us -->
       	<div class="section-full content-inner bg-white">
       	
           <div class="container">
           
			<div class="row">
			
			
             
			<!-- 카드 넣기 -->
			
				<div style="background-color: #faf9f9" class="col-xl-9 col-lg-8 col-md-7 col-sm-12 col-12" >
							<div class="section-head text-center">
								<div class="title-sm">With Tripia</div>
								<h2 class="title-head">Find Mate</h2>
								<h6 class="title-head">
								<a style="font-size: 30px" href = "match?param=matchWrite">Looking for a friend to travel with</a>
								</h6>
							</div>
							
							
							
							<div class="blog-post blog-single blog-post-style-2 sidebar">
							
								
							<hr>
							<!-- 메인 게시글 요이땅 -->

<%-- <%
List<TpListDto> list = (List<TpListDto>)request.getAttribute("list");

MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
	response.sendRedirect("login_resist_form.jsp");
	%>
	<script type="text/javascript">
	location.href = "";
	</script>
	<%
}
%> --%>

        
        


							<div class="" style="margin-bottom: 0px">
								<div class="aufx blog-user " style="border-radius: 15px; padding: 0px">
								<section class="gallery-block cards-gallery" >
								    <div class="container">
								    
								    <div class="heading">
								          <!-- <h2>Cards Gallery</h2> -->
								          <h2 style="color: #53100d"> Mate와 함께 할 여행지를 검색해보세요 </h2>
								        </div>
								

								<div class="map_wrap">
								    <div id="map" style="width:800px;height:450px;position:relative;overflow:hidden;"></div>
								
												    <div style="width:250px;height:400px;" id="menu_wrap" class="bg_white">
												        <div class="option">
												            <div>
												                <form onsubmit="searchPlaces(); return false;">
												                    키워드 : <input type="text" value="부산" id="keyword" size="15"> 
												                    <button type="submit">검색하기</button> 
												                </form>
												            </div>
												        </div>
												        <hr>
												        <ul id="placesList"></ul>
												        <div id="pagination"></div>
				   										 </div>
															</div>
								    
								    
								    
								    
								    	
								        <div class="heading">
								          <!-- <h2>Cards Gallery</h2> -->
								          <h2 style="color: #53100d"> Find Mate </h2>
								        </div>
									
										<div class="row">
												
										<c:if test="${empty list }">		
												<div align="center">
												<p>아직  게시물이 없습니다.</p>
												</div>
										
										</c:if>		
													
													<c:if test="${not empty list }">
													<c:forEach var="dto" items="${list}" varStatus="i">
													
													<div class="col-md-6 col-lg-4" style="width: 250px; height: 400px; margin-bottom: 100px"  >
														<div class="card border-0 transform-on-hover" style="width: 250px; height: 450px;">
															<a class="lightbox" href="./upload/${dto.newFilename}">
									                			<img  style="width: 250px; height: 200px;" src="./upload/${dto.newFilename}" alt="Card Image" class="card-img-top">
									                		</a>
									                		<div class="card-body">
									                		<!-- 카드 상단 위치 -->
									                		<div style="color: #CE2200; font-size: 14px;">
									                		<img style="width: 12px; height: 17px; box-shadow: none; font-style: red; color:reds" alt = "" src = "./images/where3.png">${dto.place}</div>
										                        <br>
										                        <h6><!-- <p style="color:#f87567"> -->
											                        <a href="match?param=matchdetail&seq=${dto.seq}">
																		${dto.title}
											                        </a>
										                        </h6>
										                        <c:set var="content" value = "${dto.content}"/>
										                        <p class="text-muted card-text">${fn:substring(content,0,30)}..</p>
										                        <p><b>Writer</b>&nbsp;&nbsp;${dto.name}</p>
	                          
								                                  	<c:set var="firDate" value = "${dto.firDate}"/>
								                                  	<c:set var="lasDate" value = "${dto.lasDate}"/>
								                                  	
								                                   <p><b>date</b>&nbsp;&nbsp;${fn:substring(firDate,0,11)}
								                                   <b>~</b>${fn:substring(lasDate,0,11)}</p>
								                                   <%-- <p>${i.index + 1 + (pageNumber * 10)}</p> --%>
										                        
										                        
										                        
										                        
										                    </div>
														</div>
													</div><br><br><br><br>
													</c:forEach>
													</c:if> 											
	
								    	</div>
									</div>
								</section>
								
							</div>
							<br><br>
							<br><br>
							<br><br>
																						
												<div align="center" style="margin-left: 280px;">																	
						<div align="center" class="container">
						<ul class="pagination">
						 <li class="page-item"><a class="page-link" href="#">Previous</a></li>
						<c:if test="${bbsPage != 0}">
						
							<c:forEach begin="0" end="${bbsPage -1}" varStatus="i">
							   <c:choose>
							       <c:when test="${pageNumber == i.index}">
							       <!-- 첫번째 페이징 --> <li class="page-item"><a class="page-link">${i.index + 1}</a> </li>
							         
							       </c:when>
							       <c:when test="${pageNumber != i.index}">
							         <!-- 다음페이징 --><li class="page-item"><a href="#none"  onclick="goPage(${i.index})">${i.index + 1}</a></li>
							       </c:when>
							    </c:choose>
							</c:forEach>
							
							</c:if>
							<li class="page-item"><a class="page-link" href="#">Next</a></li>
							</ul>
						</div>
						</div>

												<br><br>

												<script type="text/javascript">
													function searchBbs() {
														let choice = document.getElementById("choice").value;
														let search = document.getElementById("search").value;
													//	alert(choice);
													//	alert(search);
														
														location.href = "match?param=Matchlist&choice=" + choice + "&search=" + search;
													}
													function goPage( pageNumber ) {
														let choice = document.getElementById("choice").value;
														let search = document.getElementById("search").value;
														
													//	alert(choice);
													//	alert(search);
														
														location.href = "match?param=Matchlist&choice=" + choice + "&search=" + search + "&pageNumber=" + pageNumber;	
													}
													</script>
						</div>										
					</div>
				</div>
				
					<div class="col-xl-3 col-lg-4 col-md-5 col-sm-12 col-12">	
							<div class="side-bar p-l30 sticky-top">
							<h6 class="widget-title"><span>search</span></h6>
										 <div class="form-group">
						                    <label for="exampleSelect1"></label>
						                    <select id="choice" class="form-control" id="exampleSelect1">
						                      <option value="title" >제목</option>
						                      <option value="content">내용</option>
						                      <option value="writer" >작성자</option>
						                     
						                    </select>
                  							</div>
						
										
							
										<form class="form-inline my-2 my-lg-0">
						                    <input style="width: 128px" class="form-control mr-sm-2" id="search" type="text" placeholder="Search"
						                    value="${search }">
						                    <button type="button" class="btn btn-secondary my-2 my-sm-0"  onclick="searchBbs()">Search</button>
						                  </form>
											<br><br>
							
							
								<div class="widget widget-newsletter text-center">
									<form class="dzSubscribe dezPlaceAni" action="script/mailchamp.php" method="post">
										<div class="news-back form-style">
											<h4 style="color: #F7FE2E; text-shadow:3px 3px 3px rgb(0,0,0);">Tripia</h4>
											<h4 style="color: #F7FE2E; text-shadow:3px 3px 3px rgb(0,0,0);">today's weather</h4>
											<div class = 'weather'>
											   <div class ='CurrIcon'><img id ="CurrIcon" alt = "" src = ""></div>
											    <div style="font-size: 20px" class = 'CurrTemp'></div>
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
								</div>
								<div class="widget widget-stories">
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
								
							</div>
						</div>
					
					
					
						
					
</div>

</div>

      
      
   
               
               

              
    
							
							
			
						
					</div>
					</div>
					</div>

    
    
    
   





    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script>
        baguetteBox.run('.cards-gallery', { animation: 'slideIn'});
    </script>
    
  
<!-- Content END-->
	<!-- Footer -->
    <footer class="site-footer">
      <div class="footer-top" style="padding-bottom:0px; padding-top:50px">
         <div class="container" style="height:350px;">
            <div class="row">
               <div class="col-xl-3 col-lg-2 col-md-6 d-md-none d-lg-block">
                  <div class="widget">
                     <img src="images/logo-white.png" style="width: 144px; height: 75px; alt="" />
                  </div>
               </div>
               <div class="col-xl-3 col-lg-4 col-md-6">
                  <h6 class="m-b30 footer-title"><span>🤝 Partnership</span></h6>
                  <div class="widget recent-posts-entry">
                     <div class="widget-post-bx">
                        <div class="widget-post clearfix">
                           <div class="dlab-post-media"> <img src="images/blog/recent-blog/pic1.png" alt=""> </div>
                           <div class="dlab-post-info">
                              <div class="dlab-post-header">
                                 <h6 class="post-title"><a href="https://www.airbnb.co.kr/">Airbnb</a></h6>
                              </div>
                              <div class="dlab-post-meta">
                                 <ul>
                                    <li class="post-date">Convenient shared housing.</li>
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
                                 <h6 class="post-title"><a href="https://www.tripadvisor.co.kr/">Tripadvisor</a></h6>
                              </div>
                              <div class="dlab-post-meta">
                                 <ul>
                                    <li class="post-date">Friendly travel assistant.</li>
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
                                 <h6 class="post-title"><a href="pic3.jpg">Hotels Combined</a></h6>
                              </div>
                              <div class="dlab-post-meta">
                                 <ul>
                                    <li class="post-date">Worldwide hotel price comparison.</li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-xl-3 col-lg-3 col-md-6">
                  <h6 class="m-b30 footer-title"><span>TRIPIA VIDEO🎞️</span></h6>
                  <a class="video widget relative popup-youtube overlay-black-middle" href="images/footer-video.mp4">
                     <img src="images/footer-video.gif" alt=""/>
                     <span class="play-video"><i class="la la-play"></i></span>
                  </a>
               </div>
               <div class="col-xl-3 col-lg-3 col-md-12">
                  <h6 class="m-b30 footer-title"><span>Subscribe</span></h6>
                  <ul class="d-flex widget-social-ic">
                     <li><a href="https://www.facebook.com/profile.php?id=100065663813068" class="site-button-link"><i class="fa fa-facebook"></i></a></li>
                     <li><a href="https://www.instagram.com/travel_with_tripia/" class="site-button-link"><i class="fa fa-instagram"></i></a></li>
    					<br>
    				
                  </ul>
                  <br>
                  Service number : 02-483-1983 / 010-5473-1843 <br><br>
                  Company : 23, Baekbeom-ro, Mapo-gu, Seoul, Republic of Korea, Plaza 3F <br><br>
             	  CEO : Su Bin Lee 
               </div>
            </div>
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
   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c765d356014b8164374e21a1abcc0ed8&libraries=services"></script>
<script>
// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 키워드로 장소를 검색합니다
searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

            itemEl.onmouseover =  function () {
                displayInfowindow(marker, title);
            };

            itemEl.onmouseout =  function () {
                infowindow.close();
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}
</script>
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