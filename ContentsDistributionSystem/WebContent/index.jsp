<%@page import="system.distribution.contents.ModelRegisterContents"%>
<%@page import="system.distribution.contents.Constants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
<!--
//閲覧端末によるCSSの場合分け
if (
		(navigator.userAgent.indexOf('iPhone') > 0 && navigator.userAgent.indexOf('iPad') == -1)
		|| navigator.userAgent.indexOf('iPod') > 0
		|| navigator.userAgent.indexOf('Android') > 0
		|| navigator.userAgent.indexOf('Windows Phone') > 0
	) {
	// スマートフォン
	document.write('<link rel="stylesheet" type="text/css" href="css/smartphone.css">');
} else {
	// PC
	document.write('<link rel="stylesheet" type="text/css" href="css/pc.css">');
}
//-->
</script>
<link href="css/index.css" rel="stylesheet" type="text/css" >
<title>ファイルリスト</title>
</head>
<script type="text/javascript" src="js/index.js">
</script>
<body>
<h1>ファイルリスト</h1>

<form name="search">
	<input type="text" name="keyword" />
	<input type="button" id="buttonToSearch" onclick="searchContents()"/>
</form>

<hr />
<%
// コンテンツマップ
Map<String,String> mapContentsLink =
	(Map<String,String>) request.getAttribute((String)Constants.PARAMETER_MAPFILENAMEDLPATH);

if( mapContentsLink == null ) {
	// マップが空＝未検索 再検索
%>
	<script  type="text/javascript">
	window.location.href = "ControllerServlet";
	</script>
<%
} else {
	// マップあり javascriptの変数に登録
%>
	<script type="text/javascript">
<%
	for( Map.Entry<String, String> entry : mapContentsLink.entrySet() ) {
%>
		addContentsToMap("<%= entry.getKey() %>", "<%= entry.getValue() %>");
<%
	}
%>

	var strEvenOdd = '';	// CSSのクラス設定用
	var count = 1;
	document.write('<table>');
	for (var keyString in mapSearchResult) {
		if( count%2 == 0 ) {
			strEvenOdd = 'even';
		} else {
			strEvenOdd = 'odd';
		}
		document.write('<td class="'+ strEvenOdd +'">');
		document.write('	<a href="'+ mapSearchResult[keyString] +'">'+ keyString +'</a>');
		document.write('</td>');
		document.write('<tr/>');
		count++;
	}
	document.write('</table>');
	</script>
<%
}
%>
</body>
</html>/