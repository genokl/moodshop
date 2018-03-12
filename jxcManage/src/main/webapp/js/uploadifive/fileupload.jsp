<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UploadiFive Test</title>
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript" src="jquery.uploadifive.min.js?d=111"></script>
<link rel="stylesheet" type="text/css" href="uploadifive.css">
<style type="text/css">
</style>
</head>

<body>
	<h1>UploadiFive Demo</h1>
	
	<input type="file" accept="image/x-png" accept="image/x-png" />
	
	<form>
		<div id="queue"></div>
		<input id="file_upload" name="file_upload" type="file" multiple="true" accept="image/x-png">
		<!-- <a style="position: relative; top: 8px;" href="javascript:$('#file_upload').uploadifive('upload')">Upload Files</a> -->
	</form>
	<div style="height: 50px;width: 50px;">
		<img id="show1" alt="" src="" style="height: 30px; width: 30px;">
	</div>
	<script type="text/javascript">
		$(function() {
			$('#file_upload').uploadifive({
				'auto'             : true,//是否自动提交
				'formData'         : {
										'timestamp' : new Date().getTime(),
										'updateType': 2, //上传类型
										'isCutImg':0,	// 是否压缩图片
										'isCreateMinImg':0//是否生成小图片
									},//上传参数
				'queueID'          : 'queue',//进度条位置
				'uploadScript'     : 'up/upload',//上传地址
				'buttonText':'上传图片',//按钮显示名称
				'fileObjName':'myFile',// 上传文件名
				'fileDesc' : 'jpg/jpeg/png/gif',
				'fileTypeDesc' : 'png',
				'fileTypeExts' : '*.gif; *.jpg; *.png',
				'fileSizeLimit':3096,// 文件大小限制
				'method':'post',//上传方式
				'uploadLimit':5,//最大上传数量
				'onUploadComplete' : function(file, data) {
										d =JSON.parse(data);
										if(d.statusCode==1){
											$("#show1").attr('src',"upload/20160421/"+d.filePath);
										}else{
											alert(data);
										}
									}
			});
		});
	</script>
</body>
</html>
