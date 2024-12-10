<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Hello World!</title>
  <script src="/js/jquery-3.7.1.js"></script>
</head>
<body>
<h1>Hello World!</h1>

<%
out.println(new java.util.Date());
out.println("<br />");
out.println(out.getClass().toString());
%>
<br />
<%= web.JspTools.escapeHtml("<br />\r\n&amp;\n").replaceAll("\\n", "<br />") %>
<%= out.getClass().toString() %>

<br />
<%= System.getProperty("os.name") %>
<br />
<%= System.getenv("HOME") %>

<p><a href="./MyDate">MyDate</a></p>
<p><a href="./Aisatsu">Aisatsu</a></p>
<p><a href="./Home">Home</a></p>

<button>

<script>
$("button").text("OMG!!");
</script>

</body>
</html>