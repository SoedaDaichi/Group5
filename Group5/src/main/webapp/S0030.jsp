<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント登録</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<!--	<div class="container">-->

	<!--		<div class="content">-->

	<div class="container-fluid mx-5">
		<div class="col-10 offset-3">
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント登録</h1>


			<form id="create-task-form" action="S0030.html" method="post">
				<c:if test="${not empty error}">
					<div class="alert alert-danger text-center py-2">
						<c:out value="${error}" />
					</div>
				</c:if>
				<c:if test="${not empty success}">
					<div class="alert alert-success text-center py-2">
						<c:out value="${success}" />
					</div>
				</c:if>

				<!--				<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label for="name" class="form-label">氏名 <span-->
<!--							class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->

<!--					<div class="col-2 my-3">-->
<!--						<input type="text" required="required" id="name" name="name"-->
<!--							class="form-control ${not empty error ? ' is-invalid' : ''} my-3 px-2"-->
<!--							placeholder="氏名" required>-->
<!--					</div>-->
<!--				</div>-->
				
				
				
				<div class=row>
				<div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
					<h5>
      <label for="name" class="form-label">氏名 <span class="badge bg-secondary ms-2">必須</span></label>
    </h5>
				</div>
				<div class="col-4 my-3">
					<input type="text" required="required" id="name" name="name" class="form-control ${not empty error ? ' is-invalid' : ''}" placeholder="氏名" required>
				</div>
			</div>
				
				


				<!--						<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label for="adless" class="form-label">メールアドレス <span-->
<!--							class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->

<!--					<div class="col-2 my-3">-->
<!--						<input type="email" required="required" id="adless" name="mail"-->
<!--							class="form-control ${not empty error ? ' is-invalid' : ''} my-3 px-2"-->
<!--							placeholder="メールアドレス" required>-->
<!--					</div>-->
<!--				</div>-->

<div class=row>
				<div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
					<h5>
      <label for="adless" class="form-label">メールアドレス <span class="badge bg-secondary ms-2">必須</span></label>
    </h5>
				</div>
				<div class="col-4 my-3">
					<input type="email" required="required" id="adless" name="mail" class="form-control ${not empty error ? ' is-invalid' : ''}" placeholder="メールアドレス" required>
				</div>
			</div>





				<!--						<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label for="password" class="form-label">パスワード <span-->
<!--							class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->

<!--					<div class="col-2 my-3">-->
<!--						<input type="password" required="required" id="password"-->
<!--							name="pass"-->
<!--							class="form-control ${not empty error ? ' is-invalid' : ''} my-3 px-2"-->
<!--							placeholder="パスワード" required>-->
<!--					</div>-->
<!--				</div>-->
				
				
				<div class=row>
				<div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
					<h5>
      <label for="adless" class="form-label">パスワード<span class="badge bg-secondary ms-2">必須</span></label>
    </h5>
				</div>
				<div class="col-4 my-3">
					<input type="password" required="required" id="password" name="pass" class="form-control ${not empty error ? ' is-invalid' : ''}" placeholder="パスワード" required>
				</div>
			</div>
				
				
				
				
				


				<!--					<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label for="confirm-password" class="form-label">パスワード（確認）-->
<!--							<span class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->

<!--					<div class="col-2 my-3">-->
<!--						<input type="password" required="required" id="confirm_password"-->
<!--							name="confirm_pass"-->
<!--							class="form-control ${not empty error ? ' is-invalid' : ''} my-3 px-2"-->
<!--							placeholder="パスワード（確認）" required>-->
<!--					</div>-->
<!--				</div>-->
				
				
				<div class=row>
				<div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
					<h5>
      <span class>パスワード(確認)</span> <span class="badge bg-secondary" >必須</span>
    </h5>
				</div>
				<div class="col-4 my-3">
					<input type="password" required="required" id="confirm_password" name="confirm_pass" class="form-control ${not empty error ? ' is-invalid' : ''}" placeholder="パスワード（確認）" required>
				</div>
			</div>
				
				



				<!--					<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label class="form-label">権限 <span-->
<!--							class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->

<!--					<div class="col-2 my-3">-->
<!--						<div class="form-check">-->
<!--							<input class="form-check-input" type="radio" name="role"-->
<!--								id="role-none" value="0" required> <label-->
<!--								class="form-check-label" for="role-none">権限なし</label>-->
<!--						</div>-->
<!--						<div class="form-check">-->
<!--							<input class="form-check-input" type="radio" name="role"-->
<!--								id="role-read" value="1"> <label-->
<!--								class="form-check-label" for="role-read">売上登録</label>-->
<!--						</div>-->
<!--						<div class="form-check">-->
<!--							<input class="form-check-input" type="radio" name="role"-->
<!--								id="role-update" value="2"> <label-->
<!--								class="form-check-label" for="role-update">アカウント登録</label>-->
<!--						</div>-->
<!--					</div>-->
<!--				</div>-->


<div class="row">
  <div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
    <h5 class="form-label">
      権限 <span class="badge bg-secondary ms-2">必須</span>
    </h5>
  </div>

  <div class="col-4 my-3">
    <div class="form-check form-check form-check-inline">
      <input class="form-check-input" type="radio" name="role" id="role-none" value="0" required>
      <label class="form-check-label" for="role-none">権限なし</label>
    </div>
    <div class="form-check form-check form-check-inline">
      <input class="form-check-input" type="radio" name="role" id="role-read" value="1">
      <label class="form-check-label" for="role-read">売上登録</label>
    </div>
    <div class="form-check form-check form-check-inline">
      <input class="form-check-input" type="radio" name="role" id="role-update" value="2">
      <label class="form-check-label" for="role-update">アカウント登録</label>
    </div>
  </div>
</div>





<!--				<div class="row my-5">-->
<!--					<div class="col-3 text-end">-->
<div class="row my-2" >
				<div class="col-2 offset-2 my-3">
						<button type="submit" class="btn btn-primary">✓登録</button>

					</div>
				</div>
			</form>

		</div>
		<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
