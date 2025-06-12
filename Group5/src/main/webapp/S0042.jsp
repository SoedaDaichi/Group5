<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<!--	<div class="container">-->

	<!--		<div class="content">-->

	<div class="container-fluid mx-5">
		<div class="col-10 offset-3">
			<h1 class="fs-1 fw-bold mx-5 mt-4">アカウント詳細編集</h1>


			<form id="create-task-form" action="S0040.html" method="post">

				<!--				<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label for="name" class="form-label">氏名 <span-->
<!--							class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->
<!--					<div class="col-2 my-3">-->
<!--						<input type="text" required="required" id="name" name="name"-->
<!--							class="form-control my-3 px-2" placeholder="氏名" required>-->
<!--					</div>-->
<!--				</div>-->

<div class=row>
				<div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
					<h5>
						<span class>氏名</span> <span class="badge bg-secondary" >必須</span>
					</h5>
				</div>
				<div class="col-2 my-3">
					<input type="text" required="required" id="name" name="name" class="form-control" placeholder="氏名" required>
				</div>
			</div>





				<!--				<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label for="adless" class="form-label">メールアドレス <span-->
<!--							class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->
<!--					<div class="col-2 my-3">-->
<!--						<input type="email" required="required" id="adless" name="mail"-->
<!--							class="form-control my-3 px-2" placeholder="メールアドレス" required>-->
<!--					</div>-->
<!--				</div>-->
				
				
				<div class=row>
				<div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
					<h5>
						<span class>メールアドレス</span> <span class="badge bg-secondary" >必須</span>
					</h5>
				</div>
				<div class="col-2 my-3">
					<input type="email" required="required" id="adless" name="mail" class="form-control" placeholder="メールアドレス" required>
				</div>
			</div>
				
				

				<!--				<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label for="password" class="form-label">パスワード <span-->
<!--							class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->
<!--					<div class="col-2 my-3">-->
<!--						<input type="password" id="password" name="pass"-->
<!--							class="form-control my-3 px-2" value="${pass}" readonly>-->
<!--					</div>-->
<!--				</div>-->
				
				
				<div class=row>
				<div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
					<h5>
						<span class>パスワード</span> <span class="badge bg-secondary" >必須</span>
					</h5>
				</div>
				<div class="col-2 my-3">
					<input type="password" required="required" id="password" name="pass" class="form-control" placeholder="パスワード" required>
				</div>
			</div>
				
				

				<!--				<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label for="confirm-password" class="form-label">パスワード（確認）-->
<!--							<span class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->
<!--					<div class="col-2 my-3">-->
<!--						<input type="password" id="confirm_password" name="confirm_pass"-->
<!--							class="form-control my-3 px-2" value="${confirm_pass}" readonly>-->
<!--					</div>-->
<!--				</div>-->

<div class=row>
				<div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
					<h5>
						<span class>パスワード(確認)</span> <span class="badge bg-secondary" >必須</span>
					</h5>
				</div>
				<div class="col-2 my-3">
					<input type="password" required="required" id="confirm_password" name="confirm_pass" class="form-control" placeholder="パスワード(確認)" required>
				</div>
			</div>




				<!--				<div class="mt-3">-->
<!--				<div class=row>-->
<!--					<div class="col-2 text-end my-3">-->
<!--						<label class="form-label">権限 <span-->
<!--							class="badge bg-secondary">必須</span>-->
<!--						</label>-->
<!--					</div>-->
<!--					<div class="col-2 my-3">-->
<!--						<div class="form-check">-->
<!--							<input class="form-check-input" type="radio" name="role"-->
<!--								id="role-none" value="none" required> <label-->
<!--								class="form-check-label" for="role-none">権限なし</label>-->
<!--						</div>-->
<!--						<div class="form-check">-->
<!--							<input class="form-check-input" type="radio" name="role"-->
<!--								id="role-read" value="read"> <label-->
<!--								class="form-check-label" for="role-read">売上登録</label>-->
<!--						</div>-->
<!--						<div class="form-check">-->
<!--							<input class="form-check-input" type="radio" name="role"-->
<!--								id="role-update" value="update"> <label-->
<!--								class="form-check-label" for="role-update">アカウント登録</label>-->
<!--						</div>-->
<!--					</div>-->
<!--				</div>-->

<div class="row">
  <div class="col-2 text-end my-3 d-flex align-items-center justify-content-end">
    <h5 class="mb-0">
      権限 <span class="badge bg-secondary ms-2">必須</span>
    </h5>
  </div>
  <div class="col-2 my-3">
    <div class="form-check">
      <input class="form-check-input" type="radio" name="role" id="role-none" value="none" required>
      <label class="form-check-label" for="role-none">権限なし</label>
    </div>
    <div class="form-check">
      <input class="form-check-input" type="radio" name="role" id="role-read" value="read">
      <label class="form-check-label" for="role-read">売上登録</label>
    </div>
    <div class="form-check">
      <input class="form-check-input" type="radio" name="role" id="role-update" value="update">
      <label class="form-check-label" for="role-update">アカウント登録</label>
    </div>
  </div>
</div>





				<!--				<div class="row my-5">-->
				<!--					<div class="col-3 text-end">-->
				<div class="row my-5">
					<div class="col-2 offset-2 my-3">
						<button type="submit" class="btn btn-primary">更新</button>
						<button type="submit" class="btn btn-primary">キャンセル</button>
					</div>
				</div>

			</form>
		</div>
	</div>

</body>
</html>
