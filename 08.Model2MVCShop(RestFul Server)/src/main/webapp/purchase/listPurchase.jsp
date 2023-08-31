<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetPurchaseList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">주문 내역 조회</td>
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
		<td class="ct_list_b" width="150">주문 상품</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="250">배송 현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">주문 현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="250">주문내역 상세보기</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>	
	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		
			<c:choose>
			    <c:when test="${not empty purchase.getTranCode()}">
			        <c:set var="tranCode" value="${purchase.getTranCode()}" />
			    </c:when>
			    <c:otherwise>
			        <c:set var="tranCode" value="${null}" />
			    </c:otherwise>
			</c:choose>
			
			
	<tr class="ct_list_pop">

		<td></td>
		<td align="center">
			<a href="/getProduct.do?menu=purchase&prodNo=${  purchase.getPurchaseProd().getProdNo()}">${ purchase.getPurchaseProd().getProdName()}</a>
		</td>
		<td></td>
		<td align="center">
		<c:choose>
			<c:when test="${tranCode eq '2'}" >
				배송 준비 중
			</c:when>
			<c:when test="${ tranCode eq '3' }">
				배송중
			</c:when>
			<c:when test="${ tranCode eq '4' }">
				배송완료
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
		</td>
		<td></td>
		<td align="left">
		
		<c:choose>
			<c:when test="${tranCode eq '3' }">
				<button>
				<a href="updateTranCode.do?tranNo=${ purchase.getTranNo()}&menu=purchase&prodNo=${purchase.getPurchaseProd().getProdNo()  }&tranCode=4&currentPage=${resultPage.getCurrentPage()}">상품도착</a>
				</button>		
			</c:when>
			<c:when test="${tranCode eq '4' && empty purchase.getReviewCode()}">
				<button><a href="updateTranCode.do?tranNo=${ purchase.getTranNo()}&menu=purchase&prodNo=${purchase.getPurchaseProd().getProdNo()  }&tranCode=5&currentPage=${resultPage.getCurrentPage()}">구매확정</a></button>
			</c:when>
			<c:when test="${tranCode eq '4' && purchase.getReviewCode() eq '5'}">
				<button>
				<a href="/addReviewView.do?tranNo=${purchase.getTranNo() }&prodNo=${purchase.getPurchaseProd().getProdNo()  }&tranCode=6" >후기쓰기</a>
				</button>		
			</c:when>
			<c:when test="${ tranCode eq '4' && purchase.getReviewCode() eq '6'}">
			
			<button><a href="/listReview.do" >후기 작성 완료</a></button>
						
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
		
		</td>
		<td></td>
		<td align="center">
			<button><a href="/getPurchase.do?tranNo=${purchase.getTranNo()}">상세보기</a></button>
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
			    <jsp:param name="pageNavigator" value="listPurchase" />
			</jsp:include>
		
    	</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>