<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント検索</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>

	<div class="container-fluid">
		<div class="col-9 offset-3">
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント検索条件入力</h1>


			<form id="create-task-form" action="S0040.html" method="post">

				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<label for="name" class="form-label">氏名 <span
								class="badge bg-secondary ms-2">部分一致</span></label>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="text" id="name" name="name" class="form-control"
							placeholder="氏名">
					</div>
				</div>

				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<label for="adless" class="form-label">メールアドレス </label>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="email" id="adless" name="mail" class="form-control"
							placeholder="メールアドレス">
					</div>
				</div>

				<div class="row align-items-center">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5 class="custom-control-label">権限</h5>
					</div>
					<div class="col-2 my-3">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" name="role0"
								id="role-none" value="0"> <label
								class="form-check-label" for="role-none">権限なし</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" name="role1"
								id="role-sales" value="1"> <label
								class="form-check-label" for="role-sales">売上登録</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" name="role10"
								id="role-account" value="2"> <label
								class="form-check-label" for="role-account">アカウント登録</label>
						</div>
					</div>
				</div>

				<div class="row my-2">
					<div class="col-2 offset-2 my-3">
						<button type="submit" class="btn btn-primary">🔍検索</button>
						<button type="submit" class="btn btn-outline-secondary">クリア</button>
					</div>
				</div>

			</form>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
