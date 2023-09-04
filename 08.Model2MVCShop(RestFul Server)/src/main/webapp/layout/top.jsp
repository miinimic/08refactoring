<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="com.model2.mvc.service.domain.User" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="EUC-KR">
<title>Lunch Box</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	
	//==> jQuery 적용 추가된 부분
	 $(function() {
		 
		//==> login Event 연결처리부분
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	 	$( "td[width='115']:contains('login')" ).on("click" , function() {
			//Debug..
			//alert(  $( "td[width='115']:contains('login')" ).html() );
			$(window.parent.frames["rightFrame"].document.location).attr("href","/user/login");
		});
		
		
		//==> login Event 연결처리부분
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	 	$( "td[width='56']:contains('logout')" ).on("click" , function() {
			//Debug..
			//alert(  $( "td[width='56']:contains('logout')" ).html() );
			$(window.parent.document.location).attr("href","/user/logout");
		}); 
	});	
	 </script>	
</head>

<body topmargin="0" leftmargin="0">
 
<table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
	<td height="10"></td>
	<td height="10" >&nbsp;</td>
  </tr>
  <tr>
    <td width="800" height="30"><h2>Lunch Box</h2></td>
  </tr>
  <tr>
    <td height="20" align="right" background="/images/img_bg.gif">
	    <table width="200" border="0" cellspacing="0" cellpadding="0">
	        <tr> 
	          <td width="115">
		          <c:if test="${ empty user }">
						login
		           </c:if>         
	          </td>
	          <td width="14">&nbsp;</td>
	          <td width="56">
		          <c:if test="${ ! empty user }">
						logout
		           </c:if>
	          </td>
	        </tr>
	      </table>
      </td>
    <td height="20" background="/images/img_bg.gif">&nbsp;</td>
  </tr>
</table>

</body>
</html>