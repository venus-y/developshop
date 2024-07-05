<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
</body>
<!--===============================================================================================-->	
    <script src="<c:url value='/vendor/jquery/jquery-3.2.1.min.js'/>"></script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/animsition/js/animsition.min.js'/>"></script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/bootstrap/js/popper.js'/>"></script>
    <script src="<c:url value='/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/select2/select2.min.js'/>"></script>
    <script>
        $(".js-select2").each(function(){
            $(this).select2({
                minimumResultsForSearch: 20,
                dropdownParent: $(this).next('.dropDownSelect2')
            });
        })
    </script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/daterangepicker/moment.min.js'/>"></script>
    <script src="<c:url value='/vendor/daterangepicker/daterangepicker.js'/>"></script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/slick/slick.min.js'/>"></script>
    <script src="<c:url value='/js/slick-custom.js'/>"></script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/parallax100/parallax100.js'/>"></script>
    <script>
        $('.parallax100').parallax100();
    </script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/MagnificPopup/jquery.magnific-popup.min.js'/>"></script>
    <script>
        $('.gallery-lb').each(function() { // the containers for all your galleries
            $(this).magnificPopup({
                delegate: 'a', // the selector for gallery item
                type: 'image',
                gallery: {
                    enabled:true
                },
                mainClass: 'mfp-fade'
            });
        });
    </script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/isotope/isotope.pkgd.min.js'/>"></script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/sweetalert/sweetalert.min.js'/>"></script>
    <script>
        $('.js-addwish-b2').on('click', function(e){
            e.preventDefault();
        });

        $('.js-addwish-b2').each(function(){
            var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
            $(this).on('click', function(){
                swal(nameProduct, "is added to wishlist !", "success");

                $(this).addClass('js-addedwish-b2');
                $(this).off('click');
            });
        });

        $('.js-addwish-detail').each(function(){
            var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

            $(this).on('click', function(){
                swal(nameProduct, "is added to wishlist !", "success");

                $(this).addClass('js-addedwish-detail');
                $(this).off('click');
            });
        });

        /*---------------------------------------------*/

        $('.js-addcart-detail').each(function(){
            var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
            $(this).on('click', function(){
                swal(nameProduct, "is added to cart !", "success");
            });
        });
    </script>
<!--===============================================================================================-->
    <script src="<c:url value='/vendor/perfect-scrollbar/perfect-scrollbar.min.js'/>"></script>
    <script>
        $('.js-pscroll').each(function(){
            $(this).css('position','relative');
            $(this).css('overflow','hidden');
            var ps = new PerfectScrollbar(this, {
                wheelSpeed: 1,
                scrollingThreshold: 1000,
                wheelPropagation: false,
            });

            $(window).on('resize', function(){
                ps.update();
            })
        });
        
    
    </script>
<!--===============================================================================================-->
    <script src="<c:url value='/js/main.js'/>"></script>
</html>