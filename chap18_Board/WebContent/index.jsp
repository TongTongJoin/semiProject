<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지 호출</title>
</head>
<body>
<!--컨셉은 글작성하는 게시판  -->
	<% response.sendRedirect("./BoardWrite.bo"); %> <!-- 게시판에 글을 쓰기 위한 기능 -->

</body>
</html>