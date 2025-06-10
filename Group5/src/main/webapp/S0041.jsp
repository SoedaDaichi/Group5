<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>アカウント検索結果表示</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
  <%@ include file="navbar.jsp" %>

  <div class="container mt-5">
    <h1 class="mb-4">アカウント検索結果表示</h1>

    
    <table class="table table-bordered table-hover">
      <thead class="table-light">
        <tr>
          <th>操作</th>
          <th>No.</th>
          <th>氏名</th>
          <th>メールアドレス</th>
          <th>権限</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="account" items="${accountList}" varStatus="status">
          <tr>
            <td>
              <a href="EditServlet?id=${account.id}" class="btn btn-sm btn-primary">編集</a>
              <a href="DeleteConfirmServlet?id=${account.id}" class="btn btn-sm btn-danger">削除</a>
            </td>
            <td>${status.index + 1}</td>
            <td>${account.name}</td>
            <td>${account.email}</td>
            <td>
              <c:choose>
                <c:when test="${account.role == 'none'}">権限なし</c:when>
                <c:when test="${account.role == 'read'}">売上登録</c:when>
                <c:when test="${account.role == 'update'}">アカウント登録</c:when>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

   
  </div>
</body>
</html>
