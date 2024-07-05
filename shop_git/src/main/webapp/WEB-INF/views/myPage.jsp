<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- <%@ page session="false" %> --%>
<html>
<head>
	
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<jsp:include page="/WEB-INF/views/link-rel.jsp" />
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    		
		 <!-- basic -->
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>Pluto - Responsive Bootstrap Admin Panel Templates</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- site icon -->
      <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />" />
	  <link rel="stylesheet" href="<c:url value='/style.css' />" />
	  <link rel="stylesheet" href="<c:url value='/css/responsive.css' />" />
	  <link rel="stylesheet" href="<c:url value='/css/color_2.css' />" />
	  <link rel="stylesheet" href="<c:url value='/css/bootstrap-select.css' />" />
	  <link rel="stylesheet" href="<c:url value='/css/perfect-scrollbar.css' />" />
	  <link rel="stylesheet" href="<c:url value='/css/custom.css' />" />
	  <link rel="stylesheet" href="<c:url value='/js/semantic.min.css' />" />
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">	
</head>
<body>
  <jsp:include page="/WEB-INF/views/header.jsp" />
    <!-- dashboard inner -->
               <div class="midde_cont">
                  <div class="container-fluid">
                    <!--  <div class="row column_title">
                        <div class="col-md-12">
                           <div class="page_title">
                              <h2>Profile</h2>
                           </div>
                        </div>
                     </div> -->
                     <!-- row -->
                     <div class="row column1">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                           <div class="white_shd full margin_bottom_30">
                              <div class="full graph_head">
                                 <div class="heading1 margin_0">
                                    <h2>마이 페이지</h2>
                                 </div>
                              </div>
                              <div class="full price_table padding_infor_info">
                                 <div class="row">
                                    <!-- user profile section --> 
                                    <!-- profile image -->
                                    <div class="col-lg-12">
                                       <div class="full dis_flex center_text">
                                          <div class="profile_img"><img width="180" class="rounded-circle" src="<c:url value="/imgUpload/moon.jpg"/>" alt="#" /></div>
                                          <div class="profile_contant">
                                             <div class="contact_inner">
                                                <h3>${sessionScope.user.name}</h3>
                                                <p><strong>About: 회원</strong></p>
                                                <ul class="list-unstyled">
                                                   <li><i class="fas fa-money-bill"></i> 보유금액: ${sessionScope.user.money } </li>
                                                   <li><i class="fas fa-star"></i> : 보유포인트: ${sessionScope.user.point}</li>
                                                </ul>
                                             </div>                    
                                          </div>
                                       </div>
                                       <!-- profile contant section -->
                                       <div class="full inner_elements margin_top_30">
                                          <div class="tab_style2">
                                             <div class="tabbar">
                                                <nav>
                                                   <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                                      <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#recent_activity" role="tab" aria-selected="true">Recent Activity</a>
                                                   </div>
                                                </nav>
                                                <div class="tab-content" id="nav-tabContent">
                                                   <div class="tab-pane fade show active" id="recent_activity" role="tabpanel" aria-labelledby="nav-home-tab">
                                                      <div class="msg_list_main">
                                                         <ul class="msg_list">
                                                            <li>
                                                               <span><img src="<c:url value="/imgUpload/orderInfo.jpg"/>" class="img-responsive" alt="#"></span>
                                                               <span>
                                                               <span class="name_user"><a href="<c:url value="/order/purchase_History?user_id=${sessionScope.user.id}"/>">주문내역</a></span>
                                                               </span>
                                                            </li>
                                                            <li>
                                                               <span><img src="<c:url value="/imgUpload/userInfo.jpg"/>" class="img-responsive" alt="#"></span>
                                                               <span>
                                                               <span class="name_user"><a href="#">회원정보</a></span>
                                                               </span>
                                                            </li>
                                                         </ul>
                                                      </div>
                                                   </div>
                                                   <div class="tab-pane fade" id="project_worked" role="tabpanel" aria-labelledby="nav-profile-tab">
                                                      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et 
                                                         quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos 
                                                         qui ratione voluptatem sequi nesciunt.
                                                      </p>
                                                   </div>
                                                   <div class="tab-pane fade" id="profile_section" role="tabpanel" aria-labelledby="nav-contact-tab">
                                                      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et 
                                                         quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos 
                                                         qui ratione voluptatem sequi nesciunt.
                                                      </p>
                                                   </div>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                       <!-- end user profile section -->
                                    </div>
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-2"></div>
                        </div>
                        <!-- end row -->
                     </div>
                  </div>
                  <!-- end dashboard inner -->
               </div>	  
<script>
</script>
<!-- for commit22   -->
<!-- <div class="container">
	점보트론
	<div class="jumbotron">
		<h2 class="text-center" style="height: 250px;">FashionFlicks
			<br>
			FashionFlicks는 다양한 브랜드들의 상품을 한 곳에서 볼 수 있는 온라인 편집샵입니다.
		</h2>
		<p class="text-center" style="height:200px;"></p>
	</div>
</div>	 -->
    <jsp:include page="/WEB-INF/views/footer.jsp" />					
</body>
  <jsp:include page="/WEB-INF/views/script.jsp" />

</html>
