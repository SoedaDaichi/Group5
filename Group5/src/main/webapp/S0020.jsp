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
	<div class="container-fluid mx-5">
		<div class="row">
			<div class="col-10 offset-3">
				<p class="fs-1 fw-bold mx-5 mt-4">売上検索条件入力</p>
			</div>
		</div>

		<form id="create-task-form" action="S0020.html" method="post">
			<div class="row">
				<div class="col-10 offset-3">
					<div class="row my-4">
						<div class="col-2 text-end my-1">
							<h5>
								<span class>販売日</span>
							</h5>
						</div>
						<div class="col-2">
							<input type="date" id="date" name="first" class="form-control">
						</div>
						<div class="col-1 text-center ">
							<p>～</p>
						</div>
						<div class="col-2">
							<input type="date" id="date" name="last" class="form-control">
						</div>
					</div>
					<div class="row my-3">
						<div class="col-2 text-end ">
							<h5>
								<span class>担当</span>
							</h5>
						</div>
						<div class="col-2">
							<select class="form-select form-select-sm"
								aria-label=".form-select-sm example" name="account_id">
								<option selected value="">選択してください</option>
								<c:forEach var="accounts" items="${accountList}">
									<option value="${accounts.account_id}">${accounts.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row my-3">
						<div class="col-2 text-end ">
							<h5>
								<span class>商品カテゴリー</span>
							</h5>
						</div>
						<div class="col-2">
							<select class="form-select form-select-sm"
								aria-label=".form-select-sm example" name="category_id">
								<option selected value="">選択してください</option>
								<c:forEach var="categories" items="${categoryList}">
									<option value="${categories.category_id}">${categories.category_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>商品名</span> <span class="badge bg-secondary">部分一致</span>
							</h5>
						</div>
						<div class="col-4">
							<input type="text" class="form-control"
								id="exampleFormControlInput1" name="trade" placeholder="商品名">
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>備考</span> <span class="badge bg-secondary">部分一致</span>
							</h5>
						</div>
						<div class="col-4">
							<input type="text" class="form-control"
								id="exampleFormControlInput1" name="note" placeholder="備考">
						</div>
					</div>
					<div class="row">
						<div class="col-2 my-3 text-end">
							<button type="submit" class="btn btn-primary">検索</button>
						</div>
						<div class="col-5 my-3">
							<a href="#" button type="submit"
								class="btn btn-outline-secondary"> クリア </a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>