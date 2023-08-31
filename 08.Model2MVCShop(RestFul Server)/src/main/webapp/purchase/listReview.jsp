<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>후기 목록 조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetReviewList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listReview.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">후기 목록 조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11" >전체  ${resultPage.getTotalCount() } 건수, 현재 ${resultPage.getCurrentPage() } 페이지</td>
		
	</tr>
	<tr>		
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="250">주문 상품</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="350">후기</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">후기 수정하기</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">후기 삭제하기</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>	
	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
			
			
	<tr class="ct_list_pop">

		<td></td>
		<td align="center">
			<a href="/getProduct.do?menu=purchase&prodNo=${  purchase.getPurchaseProd().getProdNo()}">${ purchase.getPurchaseProd().getProdName()}</a>
		</td>
		<td></td>
		<td align="center">
				${purchase.getReview() }
		</td>
		<td></td>
		<td align="center">
	
			<c:choose>
				<c:when test="${purchase.getBuyer().getUserId() eq user.getUserId() || user.getRole() eq 'admin' }">
					<button><a href="/updateReviewView.do?tranNo=${purchase.getTranNo() }">수정하기</button>
				</c:when>
				<c:otherwise>
					본인이 작성한 후기만 수정 가능합니다.
				</c:otherwise>
			</c:choose>
			
		</td>
		<td></td>
		<td align="center">
			<c:choose>
				<c:when test="${purchase.getBuyer().getUserId() eq user.getUserId() || user.getRole() eq 'admin' }">
					<button><a href="/deleteReview.do?tranNo=${purchase.getTranNo() }">삭제하기</a></button>
				</c:when>
				<c:otherwise>
					본인이 작성한 후기만 삭제 가능합니다.
				</c:otherwise>
			</c:choose>
		</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>			
			<jsp:include page="../common/pageNavigator.jsp">
			    <jsp:param name="pageNavigator" value="listReview" />
			</jsp:include>
		
    	</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>