<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>S001</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<body>
	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-10 offset-4 fs-2 fw-bold ">物品売上管理システム</div>
		</div>
	</div>

	<form action="C001.html" method="post">
		<c:choose>
			<c:when test="${not empty errors.account}">
				<div class="alert alert-danger text-center py-1">
					<c:out value="${errors.account}" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-danger text-center
									py-1"
					style="visibility: hidden;">&nbsp;</div>
			</c:otherwise>
		</c:choose>
		<div class="row my-5">
			<div class="col-7 offset-4">
				<div class="col-5">
					<h6>メールアドレス</h6>
				</div>
				<div class="row">
					<div class="col-8 mb-4"> 
						<input type="text"
							class="form-control form-control-lg ${not empty errors ? ' is-invalid' : ''}"
							placeholder="メールアドレス" name="mail" value="${param.mail}" />
						<c:choose>
							<c:when test="${not empty errors.mail}">
								<div class="text-danger small">
									<c:out value="${errors.mail}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="col-5 mt-5">
					<h6>パスワード</h6>
				</div>
				<div class="row">
					<div class="col-8">
						<input type="password"
							class="form-control form-control-lg ${not empty errors ? ' is-invalid' : ''}"
							placeholder="パスワード" name="pass" />
						<c:choose>
							<c:when test="${not empty errors.pass}">
								<div class="text-danger small">
									<c:out value="${errors.pass}" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="small" style="visibility: hidden;">&nbsp;</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="row my-4">
					<div class="col-8">
						<div class="d-grid gap-2">
							<input class="btn btn-primary" type="submit" value="ログイン"></input>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<!--    <div class="container-fluid">-->
	<!--    	<div class="col text-center">-->
	<!--        <div class="row">-->
	<!--            <p>-->
	<!--                <a href="#">パスワードをお忘れの方はこちら</a>-->
	<!--            </p>-->
	<!--        </div>-->
	<!--        <div class="row">-->
	<!--            <p>-->
	<!--                <a href="#">新規登録（無料）</a>-->
	<!--            </p>-->
	<!--        </div>-->
	<!--    </div>-->
</body>
</html>