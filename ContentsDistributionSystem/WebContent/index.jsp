<%@page import="system.distribution.contents.ModelRegisterContents"%>
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
Map<String,String> mapContentsLink = (Map<String,String>) request.getAttribute("mapFilenameDLPath");
if(mapContentsLink == null) {
	out.println("-");
} else {
	for( String filename: mapContentsLink.keySet()) {
		out.println("<a href=\""+ mapContentsLink.get(filename) +"\">"+ filename +"</a>");
		out.println("<br/>");
	}	// TODO jsで整形
}

%>
</body>
</html>