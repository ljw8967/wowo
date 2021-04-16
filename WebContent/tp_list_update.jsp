<%@page import="dto.MemberDto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dto.TpListDto"%>
<%@page import="dao.TpListDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%
//System.out.println("jsp확인");
%>
<!DOCTYPE html>
<html lang="en">
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
	
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:100,700;);
.snip1585 {
  background-color: rgb(41, 46, 57);
  color: #fff;
  display: inline-block;
  font-family: 'Roboto', sans-serif;
  font-size: 24px;
  margin: 10px;
  max-width: 315px;
  min-width: 230px;
  overflow: hidden;
  position: relative;
  text-align: center;
  width: 100%;
}

.snip1585 * {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-transition: all 0.45s ease;
  transition: all 0.45s ease;
}

.snip1585:before,
.snip1585:after {
  background-color: rgba(46, 52, 64,  0.5);
  border-top: 50px solid rgba(46, 52, 64, 0.5);
  border-bottom: 50px solid rgba(46, 52, 64, 0.5);
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  content: '';
  -webkit-transition: all 0.3s ease;
  transition: all 0.3s ease;
  z-index: 1;
  opacity: 0;
}

.snip1585:before {
  -webkit-transform: scaleY(2);
  transform: scaleY(2);
}

.snip1585:after {
  -webkit-transform: scaleY(2);
  transform: scaleY(2);
}

.snip1585 img {
  vertical-align: top;
  max-width: 100%;
  backface-visibility: hidden;
}

.snip1585 figcaption {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  align-items: center;
  z-index: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  line-height: 1.1em;
  opacity: 0;
  z-index: 2;
  -webkit-transition-delay: 0s;
  transition-delay: 0s;
}

.snip1585 h3 {
  color:white;
  font-size: 1em;
  font-weight: 400;
  letter-spacing: 1px;
  margin: 0;
  text-transform: uppercase;
}

.snip1585 h3 span {
  display: block;
  font-weight: 700;
}

.snip1585 a {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 3;
}

.snip1585:hover > img,
.snip1585.hover > img {
  opacity: 0.7;
}

.snip1585:hover:before,
.snip1585.hover:before,
.snip1585:hover:after,
.snip1585.hover:after {
  -webkit-transform: scale(1);
  transform: scale(1);
  opacity: 1;
}

.snip1585:hover figcaption,
.snip1585.hover figcaption {
  opacity: 1;
  -webkit-transition-delay: 0.1s;
  transition-delay: 0.1s;
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
	
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css" />
<link rel="stylesheet" href="./css/cards-gallery.css">
	
<!-- 위에는 부트스트랩 -->
	
	<!-- 상단 작은 아이콘 -->
	<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" type="image/x-icon" href="images/favicon.png" />
	
	<!-- 페이지 타이틀 -->
	<title>Tripialistupdate</title>
	
	<!-- 모바일 사이즈 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 스타일 시트 -->
	<link rel="stylesheet" type="text/css" href="css/plugins.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/templete.css">
	<link class="skin" rel="stylesheet" type="text/css" href="css/skin/skin-1.css">

<%
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");
%>
<%
String sseq = request.getParameter("seq").trim();
int seq = Integer.parseInt(sseq);
%>
<%
TpListDao dao = TpListDao.getInstance();
TpListDto dto = dao.getTplistdetail(seq);
%>

</head>

<body id="bg">
<div class="page-wraper">
<div id="loading-area"></div>
	<!-- header -->
    <header class="site-header mo-left header style-1">
		<!-- 전체 공통 header -->
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
									<a href="#">Recommended <i class="fa fa-chevron-down"></i></a>
								</li>
								<!-- 자유게시판  -->
								<li class="has-mega-menu post-slider life-style">
									<a href="#">Forum<i class="fa fa-chevron-down"></i></a>
									</li>
								<!-- 여행지 목록  -->	
								<li>
									<a href="TpList?param=Tplist">Destination<i class="fa fa-chevron-down"></i></a>
									<ul class="sub-menu">
										<li><a href="#">여행지1</a></li>
										<li><a href="#">여행지2</a></li>
										<li><a href="#">여행지3</a></li>
										<li><a href="#">여행지4</a></li>
										<li><a href="#">여행지5</a></li>
										<li><a href="#">여행지6</a></li>
										<li><a href="#">여행지7</a></li>
									</ul>
								
								</li>
								<!-- 트리피아는? -->
								<li>
									<a href="about-us.jsp">Tripia?</a>
								</li>
								<li>
									<a href="match?param=Matchlist">Find Mate</a>
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
    <div class="page-content bg-white">        
		<!-- Slider Banner -->
		<div class="content-block">
            <!-- About Us -->
            <div class="section-full bg-white content-inner-2">
                <div class="container">
                
						
					<div class="row">
					
					
				
						<div class="col-xl-9 col-lg-8 col-md-7 col-sm-12 col-12" >
							<div class="section-head text-center">
								<div class="title-sm">With Tripia</div>
								<h2 class="title-head">Modify Destination</h2>
							</div>
							
							
							
							<div class="blog-post blog-single blog-post-style-2 sidebar">
								<div class="dlab-post-info">
									<div class="dlab-post-text text">
									
		
										<script type="text/javascript">
										
										/* Demo purposes only */
										var snippet = [].slice.call(document.querySelectorAll('.hover'));
										if (snippet.length) {
										  snippet.forEach(function (snippet) {
										    snippet.addEventListener('mouseout', function (event) {
										      if (event.target.parentNode.tagName === 'figure') {
										        event.target.parentNode.classList.remove('hover')
										      } else {
										        event.target.parentNode.classList.remove('hover')
										      }
										    });
										  });
										}
										
										</script>
						
									</div>
									
								</div>
							</div>
							<div class="container">
								<div class="aut">
									<div class="autho">
										<div class="auth">
											<h6>Update Details</h6>
												<form action="fileupdate?param=listupdate" method="post" enctype="multipart/form-data">
													
													<table class="table table-hover">
													<col width="100"><col width="800">
													<tr style=" text-align:center;">
														<th >#  <%=dto.getSeq() %></th>
														<th>Fix Detail?</th>
													</tr>
													
													<tr>
														<th style="text-align: center;" >Place:</th>
														<td>
															<select name="place">
																<option value="서울"<%="서울".equals(dto.getPlace())?"selected":"" %>>Seoul</option>
																<option value="제주"<%="제주".equals(dto.getPlace())?"selected":"" %>>JeJu-island</option>
																<option value="부산"<%="부산".equals(dto.getPlace())?"selected":"" %>>Busan</option>
																<option value="강원도"<%="강원도".equals(dto.getPlace())?"selected":"" %>>Gangwon-do</option>
																<option value="인천"<%="인천".equals(dto.getPlace())?"selected":"" %>>Incheon</option>	
															</select>
														</td>
													</tr>
													
													<tr>
														<th style="text-align: center;">ID:</th>
														<td>
															<input type="text" name="id" size="50px" value="<%=mem.getId() %>" readonly="readonly">
															<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
															
														</td>
													</tr>
													
													<tr>
														<th style="text-align: center;" >Name:</th>
														<td align="left">
													 	<input type="text" name="name" size="50px" value="<%=dto.getName()%>">
													 	</td>
													</tr>
													
													<tr>
														<th style="text-align: center;">Title:</th>
													 	<td align="left">
													 	<input type="text" name="title" size="50px" value="<%=dto.getTitle()%>">
													 	</td>
													</tr>
													
													<tr>
														<th style="text-align: center;">Content:</th>
														<td align="left">
														<textarea  rows="4" cols="50px" name="content"><%=dto.getContent() %></textarea>
														</td>
													</tr>
													
													<%-- <%
														Date d = new Date();
														SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//HH:mm:ss
														String time = df.format(d);
														session.setAttribute("time", time);      
													%> --%>
													
													<tr>
														<th style="text-align: center;">File:</th>
														<td>
															<input type="text" name="oldfile" size="50" value="${dto.filename}" >
															<input type="hidden" name="oldnewfile" size="50" value="${dto.newFilename}">
															<img style="width: 500px; height: 500px" alt="" src="./upload/${dto.newFilename}">
														</td>
													</tr>
													<tr>
														<th style="text-align: center;">NewFile:</th>
														<td>
															<input type="file" name="fileloader" style="widows: 400px">
														</td>	
													</tr>
													
													<tr>
														<th style="text-align: center;" >Concept:</th>
														<td>
															<select name="concept">
																<option value="캠핑"<%="캠핑".equals(dto.getConcept())?"selected":"" %>>캠핑</option>
																<option value="여행"<%="여행".equals(dto.getConcept())?"selected":"" %>>여행</option>
																<option value="벚꽃"<%="벚꽃".equals(dto.getConcept())?"selected":"" %>>벚꽃</option>
															</select>
														</td>
													</tr>
													
													<tr>
														<td colspan="2" align="center">
															<input type="submit" name="sub" value="Update"> 
															<input type="reset" name="sub" value="reset">
														</td>
													</tr>
													
													
													
													</table>
													</form>	
																								
												
												<br>
												<div style="text-align: center; color: black">
												<a href="TpList?param=Tplist" >글목록</a>
												<hr>
												</div>

										</div>
									</div>
								</div>

								
							</div>
						</div>

						
				<!-- 카카오지도 스크립트 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=87360543de15c8447313d97212c373f9&libraries=services,clusterer,drawing"></script>
				<!-- 카카오지도 스크립트 끝 -->
					
					<div class="col-xl-3 col-lg-4 col-md-5 col-sm-12 col-12" style="float: right;">	
							<div class="side-bar p-l30 sticky-top">
								<div class="widget widget-social">
									<h6 class="widget-title"><span>Maps</span></h6>
									
									<!-- 카카오지도 배치 시작 -->
										<div id="map" style="width:230px;height:480px;"></div>
									<!-- 카카오지도 배치 끝 -->
								</div>
								
								<div class="widget widget_tag_cloud">
									<h6 class="widget-title"><span>Developers</span></h6>
									<div class="tagcloud text-center"> 
										<a href="#">Woojh</a>
										<a href="https://www.instagram.com/jjong_ta/">Wonjh</a>
										<a href="https://aesupbin.tistory.com/">Leesb</a>
										<a href="#">Kimyn</a>
										<a href="https://www.instagram.com/2j_woo/">Leejw</a>
									</div>
								</div>
							</div>
							
							<script>
								var cnt = document.getElementById('map');
								var opt = {
									center: new kakao.maps.LatLng(37.552331, 126.937783),
									level: 3
									
								};
								var map = new kakao.maps.Map(cnt, opt);
								var control = new kakao.maps.ZoomControl();
								map.addControl(control, kakao.maps.ControlPosition.TOPRIGHT); 
								var markerPosition  = new kakao.maps.LatLng(37.552331, 126.937783); 
								var marker = new kakao.maps.Marker({
						            position: markerPosition
						        });
								marker.setMap(map);
							</script>
							
							
						</div>
					</div>
                </div>
			</div>
			<!-- About Us End -->
			
        </div>
		<!-- contact area END -->
    </div>
    <!-- Content END-->
	<!-- Footer -->
   <footer class="site-footer">
		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-xl-3 col-lg-2 col-md-6">
						<div class="widget">
							<img src="images/tripia1.png" alt=""/>
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
						<div class="video widget relative overlay-black-middle">
							<img src="images/footer-video.jpg" alt=""/>
							<a href="https://www.youtube.com/watch?v=UMX6eWoMXAM" class="popup-youtube video play-video"><i class="la la-play"></i></a>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6">
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
