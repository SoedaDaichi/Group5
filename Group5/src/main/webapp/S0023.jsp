<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>S0022</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid mx-5">
		<div class="row">
			<div class="col-10 offset-3">
				<p class="fs-1 fw-bold mx-5 mt-4">売上詳細編集</p>
			</div>
		</div>

		<form id="create-task-form" action="S0023.html" method="post">
			<div class="row">
				<div class="col-10 offset-3">
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>販売日</span>
							</h5>
						</div>

						<div class="col-3">
							<input type="date" id="date" name="sale_date"
								class="form-control" value="${salesdata.sale_date}" />
							<c:choose>
								<c:when test="${not empty errors.sale_date}">
									<div class="text-danger small">
										<c:out value="${errors.sale_date}" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="small" style="visibility: hidden;">&nbsp;</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>担当</span>
							</h5>
						</div>
						<div class="col-3">
							<select class="form-select form-select-sm"
								aria-label=".form-select-sm example" name="account_id">
								<option value="${salesdata.account_id}" selected>${salesdata.name}</option>
								<c:forEach var="account" items="${accountList}">
									<c:if test="${account.account_id != salesdata.account_id}">
										<option value="${account.account_id}">${account.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>商品カテゴリー</span>
							</h5>
						</div>
						<div class="col-3">
							<select class="form-select form-select-sm"
								aria-label=".form-select-sm example" name="category_id">
								<option value="${salesdata.category_id}" selected>${salesdata.category_name}</option>
								<c:forEach var="categories" items="${categoryList}">
									<c:if test="${categories.category_id != salesdata.category_id}">
										<option value="${categories.category_id}">${categories.category_name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>商品名</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" id="text" name="trade_name"
								class="form-control" value="${salesdata.trade_name}" />
							<c:choose>
								<c:when test="${not empty errors.trade_name}">
									<div class="text-danger small">
										<c:out value="${errors.trade_name}" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="small" style="visibility: hidden;">&nbsp;</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>単価</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" id="text" name="unit_price"
								class="form-control" value="${salesdata.unit_price}" />
							<c:choose>
								<c:when test="${not empty errors.unit_price}">
									<div class="text-danger small">
										<c:out value="${errors.unit_price}" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="small" style="visibility: hidden;">&nbsp;</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>個数</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" id="text" name="sale_number"
								class="form-control" value="${salesdata.sale_number}" />
							<c:choose>
								<c:when test="${not empty errors.sale_number}">
									<div class="text-danger small">
										<c:out value="${errors.sale_number}" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="small" style="visibility: hidden;">&nbsp;</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>備考</span>
							</h5>
						</div>
						<div class="col-3">
							<textarea input type="text" id="text" name="note"
								class="form-control" rows="4" />${salesdata.note}</textarea>
						</div>
					</div>

					<div class="row">
						<div class="col-3 my-3 text-end">
							<button type="submit" class="btn btn-primary">✓更新</button>
							<a href="S0021.html" button type="submit"
								class="btn btn-outline-secondary"> キャンセル </a>
						</div>
					</div>
				</div>
			</div>
		</form>
</body>
</html>