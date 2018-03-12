<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title></title>
  </head>
   <frameset rows="70,*" cols="*" frameborder="no" border="0" framespacing="0">
    	<!-- <frame name="topFrame" scrolling="No" noresize="noresize" id="topFrame" src="page/console/top.jsp"/>
    	<frame name="mainFrame" noresize="noresize" id="mainFrame" src="page/console/mainFrame.jsp"/> 
    	<frame name="topFrame" scrolling="No" noresize="noresize" id="topFrame" src="jump/topage.do?pagePath=page/console&pageName=top"/>
    	<frame name="mainFrame" noresize="noresize" id="mainFrame" src="jump/topage.do?pagePath=page/console&pageName=mainFrame"/>-->
    	
    	<frame name="topFrame" scrolling="No" noresize="noresize" id="topFrame" src="jump/topage.do?pagePath=page/console&pageName=top"/>
    	<frame name="mainFrame" noresize="noresize" id="mainFrame" src="jump/topage.do?pagePath=page/console&pageName=mainFrame"/>
	</frameset>
	
</html>