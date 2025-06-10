<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント登録確認</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="container">

  <div class="content">
    <h1>アカウントを登録してよろしいですか？</h1>

    <form action="S0031Servlet" method="post">

      <div class="mt-3">
        <label class="form-label">氏名</label>
        <p><%= request.getParameter("name") %></p>
        <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
      </div>

      <div class="mt-3">
        <label class="form-label">メールアドレス</label>
        <p><%= request.getParameter("mail") %></p>
        <input type="hidden" name="mail" value="<%= request.getParameter("mail") %>">
      </div>

      <div class="mt-3">
        <label class="form-label">パスワード</label>
        <p>＊＊＊＊＊＊＊</p>
        <input type="hidden" name="pass" value="<%= request.getParameter("pass") %>">
      </div>

      <div class="mt-3">
        <label class="form-label">パスワード（確認）</label>
        <p>＊＊＊＊＊＊＊</p>
        <input type="hidden" name="confirm_pass" value="<%= request.getParameter("confirm_pass") %>">
      </div>

      <div class="mt-3">
        <label class="form-label">権限</label>
        <%
          String role = request.getParameter("role");
          String roleLabel = "不明";
          if ("none".equals(role)) roleLabel = "権限なし";
          else if ("read".equals(role)) roleLabel = "参照";
          else if ("update".equals(role)) roleLabel = "更新";
        %>
        <p><%= roleLabel %></p>
        <input type="hidden" name="role" value="<%= role %>">
      </div>

      <div class="mt-4">
        <button type="submit" class="btn btn-primary">登録</button>
        <a href="register.jsp" class="btn btn-secondary">戻る</a>
      </div>
    </form>
  </div>

</div>
</body>
</html>
