<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

<title>Lunch Box</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
/*function history(){
	popWin = window.open("/history.jsp","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}*/

//==> jQuery 적용 추가된 부분
$(function() {
	 
	//==> 개인정보조회 Event 연결처리부분
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$( ".Depth03:contains('개인정보조회')" ).on("click" , function() {
		//Debug..
		//alert(  $( ".Depth03:contains('개인정보조회')" ).html() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/user/getUser?userId=${user.userId}");
	});
	
	$( ".Depth03:contains('카테고리별 상품조회')" ).on("click" , function() {
		//Debug..
		//alert(  $( ".Depth03:contains('개인정보조회')" ).html() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listCategory.jsp");
	});
	
	$( ".Depth03:contains('후기 목록 조회')" ).on("click" , function() {
		//Debug..
		//alert(  $( ".Depth03:contains('개인정보조회')" ).html() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/listReview");
	});
	
	//==> 회원정보조회 Event 연결처리부분
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$( ".Depth03:contains('회원정보조회')" ).on("click" , function() {

		$(window.parent.frames["rightFrame"].document.location).attr("href","/user/listUser");
	}); 
	
	$( ".Depth03:contains('판매상품등록')" ).on("click" , function() {

		$(window.parent.frames["rightFrame"].document.location).attr("href","../product/addProductView.jsp");
	}); 
	
	$( ".Depth03:contains('판매상품관리')" ).on("click" , function() {

		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listProduct?menu=manage"); 
	}); 
	$( ".Depth03:contains('상 품 검 색')" ).on("click" , function() { 

		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listProduct?menu=search"); 
	}); 
	
	$( ".Depth03:contains('주문 내역 조회')" ).on("click" , function() {

		$(window.parent.frames["rightFrame"].document.location).attr("href","/purchase/listPurchase"); 
	}); 
	
	$( ".Depth03:contains('장바구니')" ).on("click" , function() {

		$(window.parent.frames["rightFrame"].document.location).attr("href","/purchase/listCart"); 
	}); 
	
	$( ".Depth03:contains('최근 본 상품')" ).on("click" , function() {

		popWin = window.open("/history.jsp","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
	}); 
});	
</script>
</head>

<body background="/images/left/imgLeftBg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >

<table width="159" border="0" cellspacing="0" cellpadding="0">

<!--menu 01 line-->
<tr>
<td valign="top"> 
	<table  border="0" cellspacing="0" cellpadding="0" width="159" >	
		<tr>
		<c:if test="${ !empty user }">
		<tr>
			<td class="Depth03">
				개인정보조회
			</td>
		</tr>			
		<tr>
			<td class="Depth03">
				카테고리별 상품조회
			</td>
		</tr>
		<tr>
			<td class="Depth03">
				후기 목록 조회
			</td>
		</tr>
		</c:if>	
		
			
		<c:if test="${user.role == 'admin'}">
			<tr>
				<td class="Depth03" >
					회원정보조회
				</td>
			</tr>
		</c:if>
		<tr>
			<td class="DepthEnd">&nbsp;</td>
		</tr>
	</table>
</td>
</tr>

<c:if test="${user.role == 'admin'}">
<!--menu 02 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159">
			<tr>
				<td class="Depth03">
					판매상품등록
				</td>
			</tr>
			<tr>
				<td class="Depth03">
					판매상품관리
				</td>
			</tr>
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
		</table>
	</td>
</tr>
</c:if>

<!--menu 03 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159">
			<c:if test="${ empty user || ! empty user && user.role == 'user'}">
			<tr>
				<td class="Depth03">
					상 품 검 색
				</td>
			</tr>
			</c:if>
			<c:if test="${ ! empty user && user.role == 'user'}">
			<tr>
				<td class="Depth03">
					주문 내역 조회
				</td>
			</tr>	
			<tr>
				<td class="Depth03">
					장바구니
				</td>
			</tr>
			</c:if>

			
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03">
					최근 본 상품
				</td>
			</tr>
		</table>
	</td>
</tr>

</table>

</body>
</html>
