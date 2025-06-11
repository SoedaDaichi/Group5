<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>S0010</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container-fluid mx-5">
		<p class="fs-1 fw-bold mx-5 mt-4">売上登録</p>

		<form id="create-task-form" action="S0030Servlet" method="post">
			<div class="row my-3">
				<div class="col-2 text-end">
					<h5>
						<span class>販売日</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-3">
					<input type="date" id="date" name="date" class="form-control">
				</div>
			</div>
			<div class="row my-3">
				<div class="col-2 text-end">
					<h5>
						<span class>担当</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-5">
					<select class="form-select form-select-sm"
						aria-label=".form-select-sm example">
						<option selected>選択してください</option>
						<option value="1">#</option>
					</select>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-2 text-end">
					<h5>
						<span class>商品カテゴリー</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-5">
					<select class="form-select form-select-sm"
						aria-label=".form-select-sm example">
						<option selected>選択してください</option>
						<option value="1">#</option>
					</select>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-2 text-end">
					<h5>
						<span class>商品名</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-5">
					<input type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="商品名">
				</div>
			</div>
			<div class="row my-3">
				<div class="col-2 text-end">
					<h5>
						<span class>単価</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-2">
					<input type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="単価">
				</div>
			</div>
			<div class="row my-3">
				<div class="col-2 text-end">
					<h5>
						<span class>個数</span> <span class="badge bg-secondary">必須</span>
					</h5>
				</div>
				<div class="col-2">
					<input type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="個数">
				</div>
			</div>
			<div class="row my-3">
				<div class="col-2 text-end">
					<h5>
						<span class>備考</span>
					</h5>
				</div>
				<div class="col-8">
					<textarea class="form-control" id="exampleFormControlTextarea1"
						rows="3" placeholder="備考"></textarea>
				</div>
			</div>
			<div class="row my-5">
				<div class="col-3 text-end">
					<button type="submit" class="btn btn-primary">✓OK</button>
					<a href="#" button type="submit" class="btn btn-outline-secondary">キャンセル</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>