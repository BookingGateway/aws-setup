<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
</head>
<body>
  <%
    // Servletのデータ受け取り
    request.setCharacterEncoding("UTF8");
    String strName = (String) request.getAttribute("name");
    int timeout = (int) request.getAttribute("timeout");
  %>
  <%= strName %>さん、こんにちわ
  <br>
  timeout = <%= timeout %>
  <br>
  <br>
  <form action="<%= request.getContextPath() %>/Logout" method="post">
    <input type="submit" value="ログアウトする">
  </form>
</body>
</body>
</html>
