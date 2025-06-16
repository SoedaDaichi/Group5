<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント詳細編集</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>

	<div class="container-fluid mx-5">
	
		<div class="col-10 offset-3">
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント詳細編集</h1>


			<c:if test="${not empty sessionScope.success}">
				<div class="alert alert-success mx-5">${sessionScope.success}</div>
				<c:remove var="success" scope="session" />
			</c:if>

			
			<c:if test="${not empty sessionScope.error}">
				<div class="alert alert-danger mx-5">${sessionScope.error}</div>
				<c:remove var="error" scope="session" />
			</c:if>
			
			



			<form id="create-task-form" action="S0042.html" method="post">




				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>氏名</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="text" required="required" id="name" name="name"
							class="form-control" placeholder="氏名" value="${account.name}"
							required>
					</div>
				</div>



				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>メールアドレス</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="email" required="required" id="adless" name="mail"
							class="form-control" placeholder="メールアドレス"
							value="${account.mail}" required>
					</div>
				</div>





				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>パスワード</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="password" required="required" id="password"
							name="pass" class="form-control" placeholder="パスワード"
							value="${account.pass}" required>
					</div>
				</div>




				<div class=row>
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5>
							<span class>パスワード(確認)</span> <span class="badge bg-secondary">必須</span>
						</h5>
					</div>
					<div class="col-4 my-3">
						<input type="password" required="required" id="confirm_password"
							name="confirm_pass" class="form-control" placeholder="パスワード(確認)"
							value="${account.confirm_pass}" required>
					</div>
				</div>






				<div class="row">
					<div
						class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
						<h5 class="mb-0">
							権限 <span class="badge bg-secondary ms-2">必須</span>
						</h5>
					</div>
					<div class="col-2 my-3">
						<div class="form-check  form-check-inline">
							<input class="form-check-input" type="radio" name="role"
								id="role-none" value="0"
								${account.authority == 0 ? 'checked' : ''} required> <label
								class="form-check-label" for="role-none">権限なし</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="role"
								id="role-read"
								value="1" ${account.authority == 1 ? 'checked' : ''}"> <label
								class="form-check-label" for="role-read">売上登録</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="role"
								id="role-update"
								value="2" ${account.authority == 2 ? 'checked' : ''}"> <label
								class="form-check-label" for="role-update">アカウント登録</label>
						</div>
					</div>
				</div>





				<div class="row my-2">
					<div class="col-2 offset-2 my-3">
						<button type="submit" class="btn btn-primary">✓更新</button>
						<button type="button" class="btn btn-outline-secondary">キャンセル</button>
					</div>
				</div>

			</form>
		</div>
	</div>
	
	

</body>
</html>
