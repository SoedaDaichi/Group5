<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>S0020</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-9 offset-3">
				<p class="fs-1 fw-bold mx-5 mt-4">売上検索条件入力</p>
			</div>
		</div>

		<form id="create-task-form" action="S0020.html" method="post" novalidate>
			<c:choose>
				<c:when test="${not empty notFound.salesNotFound}">
					<div class="alert alert-danger text-center py-1">
						<c:out value="${notFound.salesNotFound}" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger text-center py-1" style="visibility: hidden;">&nbsp;</div>
				</c:otherwise>
			</c:choose>

			<div class="row">
				<div class="col-9 offset-3">
					<div class="row my-4">
						<div class="col-2 text-end my-1">
							<h5><span class>販売日</span></h5>
						</div>
						<div class="col-2">
							<input type="date" name="first"
								value="${ssForm.first}" class="form-control">
							<c:choose>
								<c:when test="${not empty errors.first}">
									<div class="text-danger small">
										<c:out value="${errors.first}" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="small" style="visibility: hidden;">&nbsp;</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-1 text-center ">
							<p>～</p>
						</div>
						<div class="col-2">
							<input type="date" name="last"
								value="${ssForm.last}" class="form-control">
							<c:choose>
								<c:when test="${not empty errors.last}">
									<div class="text-danger small">
										<c:out value="${errors.last}" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="small" style="visibility: hidden;">&nbsp;</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="row my-3">
						<div class="col-2 text-end ">
							<h5><span class>担当</span></h5>
						</div>
						<div class="col-2">
							<select class="form-select form-select-sm"
								aria-label=".form-select-sm example" name="accountId">
								<option selected value=""
									${empty ssForm.accountId ? 'selected' : ''}>選択してください</option>
								<c:forEach var="accounts" items="${accountList}">
									<option value="${accounts.accountId}"
										${ssForm.accountId eq accounts.accountId ? 'selected' : ''}>${accounts.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="row my-3">
						<div class="col-2 text-end ">
							<h5><span class>商品カテゴリー</span></h5>
						</div>
						<div class="col-2">
							<select class="form-select form-select-sm"
								aria-label=".form-select-sm example" name="categoryId">
								<option selected value=""
									${empty ssForm.categoryId ? 'selected' : ''}>選択してください</option>
								<c:forEach var="categories" items="${categoryList}">
									<option value="${categories.categoryId}"
										${ssForm.categoryId eq categories.categoryId ? 'selected' : ''}>${categories.categoryName}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>商品名</span> <span class="badge bg-secondary">部分一致</span></h5>
						</div>
						<div class="col-4">
							<input type="text" class="form-control"
								 name="tradeName"
								value="${ssForm.tradeName}" placeholder="商品名">
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>備考</span> <span class="badge bg-secondary">部分一致</span></h5>
						</div>
						<div class="col-4">
							<input type="text" class="form-control"
								name="note"
								value="${ssForm.note}" placeholder="備考">
						</div>
					</div>

					<div class="row">
						<div class="col-2 my-3 text-end">
							<button type="submit" class="btn btn-primary">検索</button>
						</div>
						<div class="col-5 my-3">
							<a href="S0020.html" button class="btn btn-outline-secondary">クリア</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>