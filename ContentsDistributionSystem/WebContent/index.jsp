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

<script type="text/javascript">
<!-- // CSSの切り替え
var ua = navigator.userAgent;
var nameCSS = "";
if(		ua.indexOf('iPhone') > 0
	||	ua.indexOf('iPod') > 0
	||	(ua.indexOf('Android') > 0 && ua.indexOf('Mobile') > 0 )
	||	( ua.indexOf('windows') > 0 && ua.indexOf('phone') > 0 )
	||	( ua.indexOf('firefox') > 0 && ua.indexOf('mobile') > 0 ) )
{	// スマートフォン
	if( window.innerWidth < 400 ) {
		nameCSS = "smartphone.css";
	} else {
		nameCSS = "tablet.css";
	}
} else
	if(	ua.indexOf('iPad') > 0
	||	ua.indexOf('Android') > 0
	||	( ua.indexOf('windows') > 0 && ua.indexOf('touch') > 0 )
	||	( ua.indexOf('firefox') > 0 && ua.indexOf('tablet') > 0 ) )
{	// タブレット
	nameCSS = "tablet.css";
} else
{	// PC
	nameCSS = "pc.css";
}
document.write('<link href="css/'+ nameCSS +'" rel="stylesheet" type="text/css" >');
document.close();
//-->
</script>
<link href="css/index.css" rel="stylesheet" type="text/css" >
<title>ファイルリスト</title>
</head>
<body>
<h1>ファイルリスト</h1>

<form action="ControllerServlet">
	<input type="text" name="keyword" />
	<input type="submit" value="" id="buttonToSearch" />
</form>

<hr />
<%
Map<String,String> mapContentsLink =
(Map<String,String>) request.getAttribute((String)Constants.PARAMETER_MAPFILENAMEDLPATH);

if( mapContentsLink == null ) {
// マップが空＝未検索 再検索
%>
<script type="text/javascript">
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
	int count = 0;
	for( String filename: mapContentsLink.keySet()) {
		String  strEvenOdd;
		if( count%2 == 0 )	strEvenOdd = "even";
		else				strEvenOdd = "odd";
		count++;
%>
	<td class="<%= strEvenOdd %>">
	<a href="<%= mapContentsLink.get(filename) %>"><%= filename %></a>
	</td>
	<tr/>
<%
	}
%>
</table>
<%
}
%>
<p>画面サイズ：<span id="ScrSize"></span></p>
<p>ウィンドウサイズ：<span id="WinSize"></span></p>

<script type="text/javascript">
<!--
//画面サイズの取得
getScreenSize();
//ウィンドウサイズの取得
getWindowSize();

//画面サイズを取得する
function getScreenSize() {
	var s = "横幅 = " + window.parent.screen.width + " / 高さ = " + window.parent.screen.height;
	document.getElementById("ScrSize").innerHTML = s;
}

//ウィンドウサイズを取得する
function getWindowSize() {
	var sW,sH,s;
	sW = window.innerWidth;
	sH = window.innerHeight;

	s = "横幅 = " + sW + " / 高さ = " + sH;

	document.getElementById("WinSize").innerHTML = s;
}
//-->
</script>
</body>
</html>