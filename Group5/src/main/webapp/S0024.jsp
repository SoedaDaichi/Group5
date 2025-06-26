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
				<p class="fs-1 fw-bold mx-5 mt-4">売上詳細編集確認</p>
			</div>
		</div>

		<form id="create-task-form" action="S0024.html" method="post">
			<div class="row">
				<div class="col-9 offset-3">
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>販売日</span></h5>
						</div>
						<div class="col-3">
							<input type="date" name="salesDate" class="form-control" value="${salesData.saleDate}" disabled/>
						</div>
					</div>
					
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>担当</span></h5>
						</div>
						<div class="col-3">
							<input type="text" name="name" class="form-control" value="${salesData.name}" disabled/>
						</div>
					</div>
					
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>商品カテゴリー</span></h5>
						</div>
						<div class="col-3">
							<input type="text" name="categoryName" class="form-control" value="${salesData.categoryName}" disabled/>
						</div>
					</div>
					
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>商品名</span></h5>
						</div>
						<div class="col-3">
							<input type="text" name="tradeName" class="form-control" value="${salesData.tradeName}" disabled/>
						</div>
					</div>
					
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>単価</span></h5>
						</div>
						<div class="col-3">
							<input type="text" name="unitPrice" class="form-control" value="${salesData.unitPrice}" disabled/>
						</div>
					</div>
					
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>個数</span></h5>
						</div>
						<div class="col-3">
							<input type="text" name="saleNumber" class="form-control" value="${salesData.saleNumber}" disabled/>
						</div>
					</div>
					
					<div class="row my-4">
						<div class="col-2 text-end">
							<h5><span class>備考</span></h5>
						</div>
						<div class="col-3">
							<textarea name="note" class="form-control" rows="4" disabled>${salesData.note}</textarea>
						</div>
					</div>
					
					<div class="row">
						<div class="col-3 my-3 text-end">
							<button type="submit" name="action" value="update" class="btn btn-primary">✓OK</button>
							<button type="submit" name="action" value="cancel" class="btn btn-outline-secondary">キャンセル</a>
						</div>
					</div>
					
				</div>
			</div>
		</form>
	</div>
</body>
</html>
