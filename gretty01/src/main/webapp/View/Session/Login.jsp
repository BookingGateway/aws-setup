<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
  <form action="<%= request.getContextPath() %>/Login" method="post">
    <h2>ログイン名</h2>
    <input type="text" name="name" />
    <input type="submit" value="ログイン">
  </form>
</body>
</html>
