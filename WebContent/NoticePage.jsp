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

    MustEatDao dao = MustEatDao.getInstance();
    
    //List<MustEatDto> list = (List<MustEatDto>)request.getAttribute("list");
   
    
    //MemberDto mem= (MemberDto)session.getAttribute("login");
    
  
   
   
    %>


<html>
<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mustlist</title>
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
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.css"> 
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


   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


</head>
<body >



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
    
                          <!-- 코로나맵 -->
                      
                          
                          
				<div align="center" class="container">
				
				 <h2 class="title-head" align="left">NOTICE</h2>  
				 <c:if test="${login.auth == 1 }"> 
					<div align="left">
					<a href="notice?param=write">공지사항작성</a>
					</div>
					 </c:if> 
				 
									 <hr style="height: 5px"> 
										<div style="font-size: 20px" class="title-sm">안전한 여행을 위해 확진자 동선을 확인하세요</div>
													<h2 class="title-head">Check movement</h2>
										
											<iframe style="width: 1140px; height: 600px" src = "https://coronamap.site/"></iframe>
					<!-- 공지 -->					
					
					<div align="left">
					<br>
					<h2 class="title-head">&#9989;COVID-19 시기 국내 여행&#127757;</h2>
					
					<h4>&#10004;백신 완전 접종자를 위한 국내 여행 권고사항</h4>
					식품의약국(FDA)이 승인한 백신을 완전히 접종한 사람은  안전하게 여행할 수 있습니다.<br>

					백신 접종을 완전히 완료한 경우에도 여행 시 다음 조치를 취해 다른 사람을 보호하세요.<br>
					<br>
					<h5>&#9726;여행 중</h5>
					<b>코와 입 위에 마스크를 착용하세요. </b><br>
					 <b>이동하는 비행기, 버스, 기차 및 기타 대중교통 수단을 이용할 때는 반드시 마스크를 착용해야 합니다.</b><br>
					사람 많은 곳을 피하고 함께 여행하지 않는 사람과는 6피트/2미터(약 팔 2개 길이) 이상 거리를 유지하세요.<br>
					<p style="color: red">손을 자주 씻거나 손 소독제(60% 이상 알코올 함유한 것)를 사용하세요.</p><br>
					<h5>&#9726;여행 후</h5>
					COVID-19 증상이 나타나는지 자가 관찰을 실시합니다. 증상이 나타나면 격리하고 검사를 받으세요.<br>
					모든  지역의 권고사항 또는 요건을 따르세요.<br>
					백신 접종을 완전히 완료했거나 COVID-19에서 회복된 지 3개월이 지나지 않은 경우에는 검사를 받거나 자가격리를 실시할 필요가 없습니다.<br>
					 그러나 기타 모든 여행 권고사항을 준수해야 합니다.
					<br><br>
					<h4>&#10004;백신 미접종자를 위한 국내 여행 권고사항</h4>
					백신 접종을 완전히 완료하지 않았지만 여행해야 하는 경우에는 다음 조치를 취해 자신과 다른 사람을 COVID-19로부터 보호하세요.<br><br>

					<h5>&#9726;여행 전</h5>
					여행 1-3일 전에 바이러스 검사를 받으세요.<br><br>
					<h5>&#9726;여행 중</h5>
					코와 입 위에 마스크를 착용하세요. <b>이동하는 비행기, 버스, 기차 및 기타 대중교통 수단을 이용할 때와 공항, <br>
					역과 같은 교통 구역에 있을 때에는 반드시 마스크를 착용해야 합니다.</b><br>
					사람이 많은 곳을 피하고 함께 여행하지 않는 사람과 6피트/2미터(약 팔 2개 길이) 이상 거리를 유지하세요.<br>
					손을 자주 씻거나 손 소독제(알코올 함량 60% 이상)를 사용하세요. <br><br>
					<h5>&#9726;여행 후</h5>
					여행 후 3-5일이 지나면 바이러스 검사를 받고 여행 후 7일 동안 집에 머물며 자가격리를 하세요.<br>
					음성 판정을 받더라도 7일 동안 외출하지 말고 집에서 자가격리하세요.<br>
					검사 결과가 양성이면 자가격리하여 다른 사람에게 전염시키지 않도록 합니다.<br>
					검사를 받지 않는 경우라면, 여행 후 10일 동안 집에서 자가격리하세요.<br>
					검사를 받든, 받지 않든 14일 동안은 중증 질환을 앓을 위험이 높은 사람 주위에 있지 마세요.<br>
					COVID-19 증상이 나타나는지 자가 관찰을 실시합니다. 증상이 나타나면 격리하고 검사를 받으세요.<br>
					모든 지역 권고사항 또는 요구사항을 지키세요.
					
					
					</div>
					<br>
					
					<h2 class="title-head" align="left">&#9989;Tripia NOTICE</h2> 
					
					
					
					
					
					<c:if test="${empty Nlist }">
					
					<div align="center">
					<p>아직  게시물이 없습니다.</p>
					</div>
					
					</c:if>
					
					<table>
					
					<c:if test="${not empty Nlist }">
					<col width="200px"><col width="200px"><col width="200px">
					<tr>
						<th>순번</th><th>공지제목</th><th>공지내용</th>
					</tr>
					
					<c:forEach var="dto" items="${Nlist }" varStatus="i">
					<tr>
					<td>${dto.noseq }</td>
					<td><a href="notice?param=detail&seq=${dto.noseq }">${dto.noTitle }</a><td>${dto.noContent }</td>
					</tr>
					</c:forEach>
					</c:if>
					
					</table>
					
					
					
					
					</div>
					
	

    
    
    
   





    
   
<!-- Content END-->
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
</body>
</html>