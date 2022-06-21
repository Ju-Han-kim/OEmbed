<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>OEmbed</title>
<link rel="stylesheet" href="<c:url value="/style/vendor/bootstrap-5.0/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/style/common.css" />">
<script type="text/javascript" src="<c:url value="/script/vendor/bootstrap-5.0/bootstrap.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/script/vendor/jquery/jquery-3.6.0.min.js" />" ></script>
<script type="text/javascript">
	var frameOEmbedSearch = {
		// 검색
		viewOEmbedSearch : function(){
			var data = {
				"url" : $("#url").val(),
				"maxwidth" : frmOEmbedSearch.maxwidth.value,
				"maxheight" : frmOEmbedSearch.maxheight.value,
				"format" : frmOEmbedSearch.format.value
			}
			
			$("#viewOEmbedSearch").load("<c:url value='/oembed/info'/>", data);
		},
		
		// 엔터키 기능 처리
		isEnter : function(keyCode){
			if(keyCode == 13){
				frameOEmbedSearch.viewOEmbedSearch();
			}
		}
	}
</script>
</head>
<form name="frmOEmbedSearch">
	<input type="hidden" name="maxwidth" value="300">
	<input type="hidden" name="maxheight" value="400">
	<input type="hidden" name="format" value="json">
</form>
<body class="bg-lightgray">
	<div class="container bg-header pb-2 mb-3">
		<p class="fs-2 fw-bold text-center">OEmbed Test</p>
		<div class="input-group mb-3">
			<input id="url" type="text" class="form-control" placeholder="URL을 입력해주세요" onKeydown="frameOEmbedSearch.isEnter(event.keyCode);">
			<button class="btn btn-outline-secondary bg-default" type="button" onclick="frameOEmbedSearch.viewOEmbedSearch()">확인</button>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<div class="card-body" id="viewOEmbedSearch">
				<p class="fs-5 fw-bold text-center">검색해주세요</p>
			</div>
		</div>
	</div>
</body>
</html>