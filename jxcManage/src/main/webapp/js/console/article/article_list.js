/**
 * 文章列表页面js
 */
  
$(function(){
//	   var articleId=getQueryString("articleId");
	showList($("#List1"))
});

function showList(t){
	loadArticleList(t)
}

function prepareQueryCondition(t){
	var t=$(t)
	var id=t.attr("id");
	var o={};
	if(id=="List1"){//0待审核
		o["isShow"]="0"
		$("#listType").val(0)
	}else if(id=="List2"){//1通过
		o["isShow"]="1"
		$("#listType").val(1)
	}else if(id=="List3"){//2拒绝
		o["isShow"]="2"
		$("#listType").val(2)
	}
	o["pageNo"]=$("#pageNo").val();
	o["pageSize"]=$("#pageSize").val();
	return o;
}

function loadArticleList(t){
	var o=prepareQueryCondition(t);
	$(".listBtn").attr("class","btn btn-info listBtn")
	$(t).attr("class","btn btn-primary listBtn")
	$.post("/article/loadArticleList.do",o,function(data){
		if (data.statusCode==1) {
			initArticleList(data)
		}else {
//			alert("创建文章失败，请联系管理员！！");
		}
	},"json");
}

function initArticleList(d){
	$("#articlesList").html("")
	var info=""
	var data=JSON.parse(d.info)
//	console.log(data)
	for (var i = 0; i < data.info.length; i++) {
		var ss=data.info[i];
		info+="<tr>"
		info+="<input type='hidden' class='id' value='"+ss.id+"'>"
		if(notNull(ss.name)){//文章名称
			info+="<td>"+ss.name+"</td>"
		}else{
			info+="<td></td>"
		}
		
		if(notNull(ss.previewImgUrls)){//预览图
			info+="<td><img alt='预览图' src="+$("#basePath").val()+ss.previewImgUrls+" style='height: 100px;'></td>"
		}else{
			info+="<td></td>"
		}
		
		if(notNull(ss.articleType)){//文章类型
			var articleType="";
			if(ss.articleType==0){
				articleType="图片列表"
			}
			info+="<td>"+articleType+"</td>"
		}else{
			info+="<td></td>"
		}
		info+="<td>"+initListBtn()+"</td>"
		info+="</tr>"
	}
	$("#articlesList").html(info)
}

function initListBtn(){
	var info="";
	var listType=$("#listType").val();
	if(listType==0){//查看未审核文章列表--通过审核按钮 查看文章详情按钮
		info+="<input type='button' value='查看文章详情'  class='btn btn-info articleInfo'>&nbsp"
		info+="<input type='button' value='上架' data-value='1'  class='btn btn-warning show'>"
	}else if(listType==1){//查看已显示文章列表--通过审核按钮
		info+="<input type='button' value='查看文章详情'  class='btn btn-info articleInfo'>&nbsp"
		info+="<input type='button' value='下架' data-value='0'  class='btn btn-danger show'>"
	}
	return info;
}
//跳转文章详情
$("#articlesList").on("click",".articleInfo",function(){
	var t=$(this)
	var id=t.parent().parent().children("input[class=id]").val();
	window.location.href=$("#basePath").val()+"jump/topage.do?pagePath=page/console/article&pageName=article_add&articleId="+id;
}) 
//上架下架动作
$("#articlesList").on("click",".show",function(){
	var t=$(this)
	var id=t.parent().parent().children("input[class=id]").val();
	var isShow=t.attr("data-value");
	var o={};
	o["id"]=id;
	o["isShow"]=isShow;
	o["articleType"]=0;
	$.post("/article/updateArticleShowStatus.do?",o,function(data){
		if (data.statusCode==1) {
			alert("该文章已经状态已经修改！");
			t.parent().parent().remove();
		}else {
//			alert("创建文章失败，请联系管理员！！");
		}
	},"json");
}) 
