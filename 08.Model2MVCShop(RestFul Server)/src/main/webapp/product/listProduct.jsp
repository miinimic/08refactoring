<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

function fncGetProductList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<c:choose>
	<c:when test="${search.getMenu() eq 'manage'}">
		<c:choose>
			<c:when test="${ ! empty search.getOrder()}">
				<c:choose>
					<c:when test="${!empty search.getCategory() }">
						<form name="detailForm" action="/product/listProduct?menu=manage&order=${search.getOrder()}&category=${search.getCategory()}" method="post">
					</c:when>
					<c:otherwise>
						<form name="detailForm" action="/product/listProduct?menu=manage&order=${search.getOrder()}" method="post">
					</c:otherwise>
				</c:choose>
				
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty search.getCategory() }">
						<form name="detailForm" action="/product/listProduct?menu=manage&category=${search.getCategory() }" method="post">
					</c:when>
					<c:otherwise>
						<form name="detailForm" action="/product/listProduct?menu=manage" method="post">
					</c:otherwise>			
				</c:choose>			
			</c:otherwise>	
		</c:choose>	
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${ ! empty search.getOrder()}">
				<c:choose>
					<c:when test="${ ! empty search.getCategory()}">
						<form name="detailForm" action="/product/listProduct?menu=search&order=${search.getOrder() }&category=${search.getCategory()}" method="post">
					</c:when>
					<c:otherwise>
						<form name="detailForm" action="/product/listProduct?menu=search&order=${search.getOrder()  }" method="post">
					</c:otherwise>
				</c:choose>					
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${ ! empty search.getCategory()}">
						<form name="detailForm" action="/product/listProduct?menu=search&category=${search.getCategory() }" method="post">
					</c:when>
					<c:otherwise>
						<form name="detailForm" action="/product/listProduct?menu=search" method="post">
					</c:otherwise>
				</c:choose>	
			</c:otherwise>
		</c:choose>		
	</c:otherwise>
</c:choose>

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					<c:choose>
						<c:when test="${search.getMenu() eq 'manage' }" >
							상품관리
						</c:when>
						<c:otherwise>
							상품 목록 조회
						</c:otherwise>
					</c:choose>
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
	<c:choose>
		<c:when test="${search.getMenu() eq 'manage' || user.getRole() eq 'admin' }" >
			<c:choose>
					<c:when test="${ !empty search.getSearchCondition() }" >
						<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width:80px">		
						<c:choose>				
							<c:when test="${search.getSearchCondition() eq '0' }">
									<option value="0" selected>상품번호</option>
									<option value="1">상품명</option>
									<option value="2">상품가격</option>
							</c:when>
							<c:when test="${search.getSearchCondition() eq '1' }">
									<option value="0">상품번호</option>
									<option value="1" selected>상품명</option>
									<option value="2">상품가격</option>
							</c:when>
							<c:when test="${search.getSearchCondition() eq '2' }">
									<option value="0">상품번호</option>
									<option value="1">상품명</option>
									<option value="2" selected>상품가격</option>
							</c:when>
							<c:otherwise>
								<option value="0">상품번호</option>
								<option value="1">상품명</option>
								<option value="2">상품가격</option>					
							</c:otherwise>
							</c:choose>	
							</select>	
						<c:choose>
							<c:when test="${ empty search.getSearchCondition() }">
								<input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" >
							</c:when>
							<c:when test="${ !empty search.getSearchCondition() }">
							<input 	type="text" name="searchKeyword"  value="${search.getSearchKeyword()}" 
										class="ct_input_g" style="width:200px; height:19px" >
							</c:when>
						</c:choose>
					</c:when>		
					<c:otherwise>
						<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width:80px">
							<option value="0">상품번호</option>
							<option value="1">상품명</option>
							<option value="2">상품가격</option>
						</select>
						<input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" >
						</td>			
					</c:otherwise>	
				</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
					<c:when test="${ !empty search.getSearchCondition() }" >
						<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width:80px">		
						<c:choose>				
							<c:when test="${search.getSearchCondition() eq '1' }">
									<option value="1" selected>상품명</option>
									<option value="2">상품가격</option>
							</c:when>
							<c:when test="${search.getSearchCondition() eq '2' }">
									<option value="1">상품명</option>
									<option value="2" selected>상품가격</option>
							</c:when>
							<c:otherwise>
								<option value="1">상품명</option>
								<option value="2">상품가격</option>					
							</c:otherwise>
							</c:choose>	
							</select>	
						<c:choose>
							<c:when test="${ empty search.getSearchCondition() }">
								<input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" >
							</c:when>
							<c:when test="${ !empty search.getSearchCondition() }">
							<input 	type="text" name="searchKeyword"  value="${search.getSearchKeyword()}" 
										class="ct_input_g" style="width:200px; height:19px" >
							</c:when>
						</c:choose>
					</c:when>		
					<c:otherwise>
						<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width:80px">
							<option value="1">상품명</option>
							<option value="2">상품가격</option>
						</select>
						<input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" >
						</td>			
					</c:otherwise>	
				</c:choose>
		</c:otherwise>
	</c:choose>
				
				</td>
					
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  ${ resultPage.getTotalCount() } 건수, 현재 ${resultPage.getCurrentPage() } 페이지 
		<c:choose>
			<c:when test="${search.getMenu() eq 'manage' || user.getRole() eq 'admin'}">
				<c:choose>
					<c:when test="${empty search.getCategory() }">
						<button><a href="/product/listProduct?order=asc&menu=manage">낮은 가격 순</a></button> 
						<button><a href="/product/listProduct?order=desc&menu=manage">높은 가격 순</a></button>		   
					</c:when>
					<c:otherwise>
						<button><a href="/product/listProduct?order=asc&menu=manage&category=${search.getCategory() }">낮은 가격 순</a></button> 
						<button><a href="/product/listProduct?order=desc&menu=manage&category=${search.getCategory() }">높은 가격 순</a></button>		
					</c:otherwise>
				</c:choose>			
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${empty search.getCategory() }">
						<button><a href="/product/listProduct?order=asc&menu=search">낮은 가격 순</a></button> 
						<button><a href="/product/listProduct?order=desc&menu=search">높은 가격 순</a></button>
					</c:when>
				    <c:otherwise>
				    	<button><a href="/product/listProduct?order=asc&menu=search&category=${search.getCategory()}">낮은 가격 순</a></button> 
						<button><a href="/product/listProduct?order=desc&menu=search&category=${search.getCategory() }">높은 가격 순</a></button>			    
				    </c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">카테고리</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">상품현황</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />
			<c:choose>
			    <c:when test="${not empty product.getProTranCode()}">
			        <c:set var="tranCode" value="${fn:trim(product.getProTranCode())}" />
			    </c:when>
			    <c:otherwise>
			        <c:set var="tranCode" value="${null}" />
			    </c:otherwise>
			</c:choose>
		<tr class="ct_list_pop">
		<td align="center">${ i }</td>
		<td></td>
		<td align="left">
		<c:choose>		
			<c:when test="${search.getMenu() eq 'manage' && empty product.getProTranCode()  || user.getRole() eq 'admin' && empty product.getProTranCode()  }" >
				<a href="/product/updateProductView?prodNo=${product.getProdNo()}">${product.getProdName()}</a>
			</c:when>
			<c:when test="${search.getMenu() eq 'search' && empty product.getProTranCode() }">		
				<a href="/product/getProduct?prodNo=${product.getProdNo()}&menu=search">${product.getProdName()}</a>
			</c:when>
			<c:when test="${search.getMenu() eq 'manage' && ! empty product.getProTranCode()  || user.getRole() eq 'admin' && ! empty product.getProTranCode()  }" >
				<a href="/product/getProduct?menu=manage&prodNo=${product.getProdNo()}">${product.getProdName()}</a>
			</c:when>
			<c:otherwise>
				<a href="/product/getProduct?prodNo=${product.getProdNo()}">${product.getProdName()}</a>
			</c:otherwise>
		</c:choose>
		</td>
		<td></td>
		<td align="left">${product.getCategory() }</td> 
		<td></td>
		<td align="left">${product.getPrice() } ( 재고 : ${product.getItem() } 개)</td> 
		<td></td>
		<td align="left">${product.getRegDate() }</td>
		<td></td>	
		<td align="left">
<c:choose>
    <c:when test="${search.getMenu() eq 'manage'}">
        <c:choose>
            <c:when test="${empty product.getProTranCode()}">
                판매중 
                <c:choose>
                	<c:when test="${ product.getCartNo() == '0'}">
                		<button><a href="/product/deleteProduct?currentPage=${resultPage.currentPage}&prodNo=${product.getProdNo()}&menu=manage">삭제하기</a></button>
                	</c:when>
                	<c:otherwise>
                	
                	</c:otherwise>
                </c:choose>               
            </c:when>
            <c:when test="${tranCode eq '2'}">
                구매완료 <button><a href="/product/updateTranCode?tranNo=${product.getTranNo() }&menu=manage&prodNo=${product.getProdNo()}&tranCode=3&currentPage=${resultPage.currentPage}">배송하기</a></button>
            </c:when>
            <c:when test="${tranCode eq '3'}">
                배송중
            </c:when>
            <c:otherwise>
                배송완료
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:when test="${(userId eq 'admin' || userId eq 'manager') && search.getMenu() eq 'search'}">
        <c:choose>
            <c:when test="${empty product.getProTranCode()}">
                판매중
            </c:when>
            <c:when test="${product.getProTranCode() eq '2'}">
                구매완료
            </c:when>
            <c:when test="${product.getProTranCode() eq '3'}">
                배송중
            </c:when>
            <c:otherwise>
                배송완료
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${product.getItem() > '0'}">
                판매중
                		<c:choose>
                			 <c:when test="${product.getCartNo() == '0'}">
                				<button><a href="/product/addCart?prodNo=${ product.getProdNo() }&userId=${user.getUserId()}&currentPage=${resultPage.currentPage}">장바구니에 넣기</a></button>
                			</c:when>
                			<c:otherwise>
                				<button><a href="/product/listCart">장바구니 확인하기</a></button>		
                			</c:otherwise>             		
                		</c:choose>                    	
            </c:when>
            <c:otherwise>
                재고없음
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

 		
			</td>		
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
			    <jsp:param name="pageNavigator" value="listProduct" />
			</jsp:include>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
