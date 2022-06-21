<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<c:choose>
	<c:when test="${OEmbedInfo eq null}">
		<p class="fs-5 fw-bold text-center">${message}</p>
	</c:when>
	<c:otherwise>
		<div>
			<c:if test="${OEmbedInfo.title ne null}">
				<div class="row p-1">
					<div class="col-3">
						<strong>title</strong>
					</div>
					<div class="col-9">
						<strong>${OEmbedInfo.title}</strong>
					</div>
				</div>
			</c:if>
			<div class="row p-1 border">
				<div class="col-3">
					type
				</div>
				<div class="col-9">
					${OEmbedInfo.type}
				</div>
			</div>
			<div class="row p-1 border">
				<div class="col-3">
					version
				</div>
				<div class="col-9">
					${OEmbedInfo.version}
				</div>
			</div>
			<c:if test="${OEmbedInfo.provider_name ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						provider_name
					</div>
					<div class="col-9">
						${OEmbedInfo.provider_name}
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.provider_url ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						provider_url
					</div>
					<div class="col-9">
						${OEmbedInfo.provider_url}
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.author_name ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						author_name
					</div>
					<div class="col-9">
						${OEmbedInfo.author_name}
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.author_url ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						author_url
					</div>
					<div class="col-9">
						${OEmbedInfo.author_url}
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.html ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						html
					</div>
					<div class="col-9">
						${fn:replace(OEmbedInfo.html, '<', '&lt;')}
						<br>
						${OEmbedInfo.html}
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.width ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						width
					</div>
					<div class="col-9">
						${OEmbedInfo.width}
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.height ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						height
					</div>
					<div class="col-9">
						${OEmbedInfo.height}
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.thumbnail_url ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						thumbnail_url
					</div>
					<div class="col-9">
						${OEmbedInfo.thumbnail_url}<br>
						<img src="${OEmbedInfo.thumbnail_url}" />
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.thumbnail_width ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						thumbnail_width
					</div>
					<div class="col-9">
						${OEmbedInfo.thumbnail_width}
					</div>
				</div>
			</c:if>
			<c:if test="${OEmbedInfo.thumbnail_height ne null}">
				<div class="row p-1 border">
					<div class="col-3">
						thumbnail_height
					</div>
					<div class="col-9">
						${OEmbedInfo.thumbnail_height}
					</div>
				</div>
			</c:if>
		</div>
	</c:otherwise>
</c:choose>