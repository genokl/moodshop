<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'demo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="js/uploadifive/falsh/uploadify.css">
	
	<script type="text/javascript"  src="js/uploadifive/jquery.min.js"></script>
	<script type="text/javascript"  src="js/uploadifive/falsh/jquery.uploadify.min.js"></script>

  </head>
  
  <body>
    
    <h1>UploadiFive Demo</h1>
	<form>
		<div id="queue"></div>
		<input id="file_upload" name="file_upload" type="file" multiple="true">
	 	<a style="position: relative; top: 8px;" href="javascript:$('#file_upload').uploadifive('upload')">Upload Files</a>	<!-- -->
	</form>
	<div style="height: 50px;width: 50px;">
		<img id="show1" alt="" src="" style="height: 30px; width: 30px;">
	</div>
	<script type="text/javascript">
		$(function() {
			$(function() {
			    $("#file_upload").uploadify({
					'fileTypeDesc' : 'Image Files',
					'fileTypeExts' : '*.gif; *.jpg; *.png',
			        height        : 30,
			        swf           : 'js/uploadifive/falsh/uploadify.swf',
			        uploader      : 'up/upload',
			        width         : 120
			    });
			});
		});
	</script>
  </body>
</html>
