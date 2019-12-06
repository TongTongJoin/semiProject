<%@ page language="java" contentType="text/html; charset=utf-8" 
		pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>MVC 게시판</title>

<script language="javascript">
	function addboard() {
		boardform.submit();
	}
</script>
</head>

<body>
	<!-- 게시판 등록 -->
	<!--  enctype 이 파일첨부태그와 같이 있어야 비로소 첨부파일이 서버에 전달된다.-->
	<form action="./BoardAddAction.bo" method="post" enctype="multipart/form-data" name="boardform">
		<table cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle">
				<td colspan="5">MVC 게시판</td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12" height="16">
					<div align="center">글쓴이</div>
				</td>
				<td>
					<input name="BOARD_NAME" type="text" size="10" maxlength="10" value="" />
				</td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12" height="16">
					<div align="center">비밀번호</div>
				</td>
				<td>
					<input name="BOARD_PASS" type="password" size="10" maxlength="10" value="" />
				</td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12" height="16">
					<div align="center">제 목</div>
				</td>
				<td>
					<input name="BOARD_SUBJECT" type="text" size="50" maxlength="100" value="" />
				</td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12">
					<div align="center">내 용</div>
				</td>
				<td>
					<textarea name="BOARD_CONTENT" cols="67" rows="15"></textarea>
				</td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12">
					<div align="center">파일 첨부</div>
				</td>
				<td>
					<input name="BOARD_FILE" type="file" />
				</td>
			</tr>
			<tr bgcolor="cccccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5">
					<a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp; <!-- 이점은 버튼 ui 부터의 탈피라고 하는데.. 의미가 있나? 링크만으로 js 구동은 유의미 -->
					<a href="javascript:history.go(-1)">[뒤로]</a>
				</td>
			</tr>
		</table>
	</form>
	<!-- 게시판 등록 -->
</body>
</html>