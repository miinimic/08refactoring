<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>카테고리별 조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do" method="post">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						상품 카테고리
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">

	<tr>
		<td class="ct_list_b" width="100">카테고리</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>		
	
			<tr>
				<td>
				<a href="/listProduct.do?category=chicken">chicken</a> 
				</td>
				</tr>
				<tr>
				<td colspan="11" bgcolor="808285" height="1"></td>	
				</tr>
				<tr><td><a href="/listProduct.do?category=beef">beef</a> </td></tr>
				<tr>
				<td colspan="11" bgcolor="808285" height="1"></td>	
				</tr>
				<tr><td><a href="/listProduct.do?category=pork">pork</a> </td></tr>
				<tr>
				<td colspan="11" bgcolor="808285" height="1"></td>	
				</tr>
				<tr><td><a href="/listProduct.do?category=egg">egg</a> </td></tr>
				<tr>
				<td colspan="11" bgcolor="808285" height="1"></td>	
				</tr>
				<tr><td><a href="/listProduct.do?category=seafood">seafood</a> </td></tr>
				<tr>
				<td colspan="11" bgcolor="808285" height="1"></td>	
				</tr>
				<tr><td><a href="/listProduct.do?category=vegan">vegan</a> </td></tr>
		</tr>

</table>

<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
