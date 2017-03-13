<%@page import="system.distribution.contents.ModelRegisterContents"%>
<%@page import="system.distribution.contents.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルリスト</title>
</head>
<body>
<h1>ファイルリスト</h1>

<form action="ControllerServlet">
	<input type="text" name="keyword" />
	<input type="submit" />
</form>
<hr />
<%
Map<String,String> mapContentsLink = (Map<String,String>) request.getAttribute((String)Constants.PARAMETER_MAPFILENAMEDLPATH);

if( mapContentsLink == null ) {
// マップが空＝未検索 再検索
%>
<script>
	window.location.href = "ControllerServlet";
</script>
<%
} else if( mapContentsLink.size() < 1 ) {
	// 結果がないとき 見つからなかった
%>
<p>見つかりませんでした。</p>
<%
} else {
%>
<table>
<%
	for( String filename: mapContentsLink.keySet()) {
%>
		<td>
		<a href="<%=mapContentsLink.get(filename)%>"><%=filename%></a>
		</td>
		<tr/>
<%
	}
%>
</table>
<%
}
%>
</body>
</html>