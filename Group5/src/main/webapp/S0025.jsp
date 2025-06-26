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
	<div class="container-fluid">
		<div class="row">
			<div class="col-9 offset-3">
				<p class="fs-1 fw-bold mx-5 mt-4">売上詳細削除</p>
			</div>
		</div>
		<form id="taskForm" method="post" action="S0025.html" class="d-inline">
			<div class="row">
				<div class="col-9 offset-3">
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>販売日</h5>
						</div>
						<div class="col-3">
							<input type="date" name="date" class="form-control"
								value="${salesData.saleDate}" disabled />
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>担当</h5>
						</div>
						<div class="col-3">
							<input type="text" name="name" class="form-control"
								value="${salesData.name}" disabled />
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>商品カテゴリー</h5>
						</div>
						<div class="col-3">
							<input type="text" name="categoryName" class="form-control"
								value="${salesData.categoryName}" disabled />
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>商品名</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" name="tradeName" class="form-control"
								value="${salesData.tradeName}" disabled />
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>単価</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" name="unitPrice" class="form-control"
								value="${salesData.unitPrice}" disabled />
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>個数</span>
							</h5>
						</div>
						<div class="col-3">
							<input type="text" name="saleNumber" class="form-control"
								value="${salesData.saleNumber}" disabled />
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 text-end">
							<h5>
								<span class>備考</span>
							</h5>
						</div>
						<div class="col-3">
							<textarea name="note" class="form-control" rows="4" disabled>${salesData.note}</textarea>
						</div>
					</div>

					<div class="row">
						<div class="col-3 my-3 text-end">
							<!-- OKボタン（削除） -->
							<button type="submit" name="action" value="delete"
								class="btn btn-danger">×OK</button>
							<!-- キャンセルボタン（一覧画面に戻る） -->
							<button type="submit" name="action" value="cancel"
								class="btn btn-outline-secondary">
								キャンセル
								</botton>
						</div>
					</div>
		</form>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script>
		document.getElementById("taskForm").addEventListener(
				"submit",
				function(event) {
					const submitter = event.submitter;
					if (submitter.value === "delete") {
						if (!confirm("本当に削除しますか？")) {
							event.preventDefault();
						}
					}
				});
	</script>
</body>
</html>
