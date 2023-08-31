<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
<c:choose>
	<c:when test="${param.pageNavigator == 'listUser' }">
		<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
				◀ 이전
		</c:if>
		<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
				<a href="javascript:fncGetUserList('${ resultPage.currentPage-1}')">◀ 이전</a>
		</c:if>
		
		<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
			<a href="javascript:fncGetUserList('${ i }');">${ i }</a>
		</c:forEach>
		
		<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
				이후 ▶
		</c:if>
		<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
				<a href="javascript:fncGetUserList('${resultPage.endUnitPage+1}')">이후 ▶</a>
		</c:if>	
	</c:when>
	<c:when test="${param.pageNavigator == 'listProduct' }">
		<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
				◀ 이전
		</c:if>
		<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
				<a href="javascript:fncGetProductList('${ resultPage.currentPage-1}')">◀ 이전</a>
		</c:if>
		
		<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
			<a href="javascript:fncGetProductList('${ i }');">${ i }</a>
		</c:forEach>
		
		<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
				이후 ▶
		</c:if>
		<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
				<a href="javascript:fncGetProductList('${resultPage.endUnitPage+1}')">이후 ▶</a>
		</c:if>			
	</c:when>
		<c:when test="${param.pageNavigator == 'listPurchase' }">
		<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
				◀ 이전
		</c:if>
		<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
				<a href="javascript:fncGetPurchaseList('${ resultPage.currentPage-1}')">◀ 이전</a>
		</c:if>
		
		<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
			<a href="javascript:fncGetPurchaseList('${ i }');">${ i }</a>
		</c:forEach>
		
		<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
				이후 ▶
		</c:if>
		<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
				<a href="javascript:fncGetPurchaseList('${resultPage.endUnitPage+1}')">이후 ▶</a>
		</c:if>			
	</c:when>
	
	<c:when test="${param.pageNavigator == 'listSale' }">
		<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
				◀ 이전
		</c:if>
		<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
				<a href="javascript:fncGetSaleList('${ resultPage.currentPage-1}')">◀ 이전</a>
		</c:if>
		
		<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
			<a href="javascript:fncGetSaleList('${ i }');">${ i }</a>
		</c:forEach>
		
		<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
				이후 ▶
		</c:if>
		<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
				<a href="javascript:fncGetSaleList('${resultPage.endUnitPage+1}')">이후 ▶</a>
		</c:if>			
	
	</c:when>
	
		<c:when test="${param.pageNavigator == 'listCart' }">
		<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
				◀ 이전
		</c:if>
		<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
				<a href="javascript:fncGetCartList('${ resultPage.currentPage-1}')">◀ 이전</a>
		</c:if>
		
		<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
			<a href="javascript:fncGetCartList('${ i }');">${ i }</a>
		</c:forEach>
		
		<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
				이후 ▶
		</c:if>
		<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
				<a href="javascript:fncGetCartList('${resultPage.endUnitPage+1}')">이후 ▶</a>
		</c:if>			
	
	</c:when>
</c:choose>	