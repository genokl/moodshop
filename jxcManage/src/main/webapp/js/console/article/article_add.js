


/**
 * 文章添加页面js
 */
var custAct = {}; //当前页面对象
  
$(function(){
	var websocket;
	var domain="ws://asd.nat300.top/";
//	var domain="ws://localhost:8080/";

    // 首先判断是否 支持 WebSocket
    if('WebSocket' in window) {
        websocket = new WebSocket(domain+"websocket");
    } else if('MozWebSocket' in window) {
        websocket = new MozWebSocket(domain+"websocket");
    } else {
        websocket = new SockJS(domain+"sockjs/websocket");
    }
    // 打开时
    websocket.onopen = function(evnt) {
        console.log("  websocket.onopen  ");
    };


    // 处理消息时
    websocket.onmessage = function(evnt) {
        $("#msg").append("<p>(<font color='red'>" + evnt.data + "</font>)</p>");
        console.log("  websocket.onmessage   ");
    };


    websocket.onerror = function(evnt) {
        console.log("  websocket.onerror  ");
    };

    websocket.onclose = function(evnt) {
        console.log("  websocket.onclose  ");
    };


    // 点击了发送消息按钮的响应事件
    $("#TXBTN").click(function(){

        // 获取消息内容
        var text = $("#tx").val();

        // 判断
        if(text == null || text == ""){
            alert(" content  can not empty!!");
            return false;
        }

        var msg = {
            msgContent: text,
            postsId: 1
        };

        // 发送消息
        websocket.send(JSON.stringify(msg));

    });


});


//$(function(){
//	   var articleId=getQueryString("articleId");
//	   custAct.init();
//	   if(notNull(articleId)){
//		   custAct.loadArticleInfo(articleId);
//	   }
//});

/**
 * 初始化加载百度编辑器
 */
custAct.init=function(){
	//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
//	/var ue = UE.getEditor('editor');
	//初始化图片上传组件
	custAct.initUploadFive()
	
}

//请求页面数据
custAct.loadArticleInfo=function(articleId){
	var o={};
	o["articleId"]=articleId;
	$.post("/article/loadArticleInfo.do?",o,function(data){
		if (data.statusCode==1) {
			custAct.initArticleInfo(data)
		}else {
//			alert("创建文章失败，请联系管理员！！");
		}
	},"json");
}

//回显页面数据
custAct.initArticleInfo=function(data){
	var d=JSON.parse(data.info)
	var previewImgUrls=d.article.previewImgUrls
	$("#f2 .article").find("input").each(function(){
		var t=$(this)
		var name=t.attr("class");
		t.val(d.article[name])
	})
	$("#f2 .article textarea").val(d.article.descriptions);
	for (var i = 0; i < d.pictureResourceList.length; i++) {
		var pictureResourceInstance=$(".pictureResourceInstance").clone();
//		console.log(previewImgUrls.indexOf(d.pictureResourceList[i].pictureUrl))
		pictureResourceInstance.attr("class","pictureResource")
		if(previewImgUrls.indexOf(d.pictureResourceList[i].pictureUrl)!=-1){
//			console.log(pictureResourceInstance.find("checkbox"))
			pictureResourceInstance.find("input[class=setviewImg]").prop("checked",true)
		}
		pictureResourceInstance.children(".id").val(d.pictureResourceList[i].id)
		//pictureResourceInstance.children(".description").val(d.pictureResourceList[i].description)
		pictureResourceInstance.children(".viewFrequency").val(d.pictureResourceList[i].viewFrequency)
		pictureResourceInstance.children(".articleId").val(d.pictureResourceList[i].articleId)
		pictureResourceInstance.children(".pictureResourceType").val(d.pictureResourceList[i].pictureResourceType)
		pictureResourceInstance.children(".height").val(d.pictureResourceList[i].height)
		pictureResourceInstance.children(".width").val(d.pictureResourceList[i].width)
		pictureResourceInstance.children(".createTime").val(d.pictureResourceList[i].createTime)
		pictureResourceInstance.children(".isDel").val(d.pictureResourceList[i].isDel)
		pictureResourceInstance.children(".other").val(d.pictureResourceList[i].other)
		pictureResourceInstance.children(".pictureUrl").attr("src",d.pictureResourceList[i].pictureUrl)
		pictureResourceInstance.show();
		$("#f1").append(pictureResourceInstance)
	}
}

/**
 * 初始化图片上传组件
 */
custAct.initUploadFive=function(){
	/**
	 * 图片上传
	 */
	var path="";
	var paths="";
	var size=5;
	$('#file_upload').uploadifive({
		'auto'             : false,//是否自动提交
		'formData'         : {
								'timestamp' : new Date().getTime(),
								'updateType': 2, //上传类型
								'isCutImg':0,	// 是否压缩图片
								'isCreateMinImg':0//是否生成小图片
							},//上传参数
		'queueID'          : 'queue',//进度条位置
		'uploadScript'     : 'upload/uploadPic.do',//上传地址
		'buttonText':'上传图片',//按钮显示名称
		'fileObjName':'myFile',// 上传文件名
		'fileSizeLimit':3096,// 文件大小限制
		'method':'post',//上传方式
		'uploadLimit':0,//最大上传数量
		'onSelect':function(queue){
//			var imgNum = $("#imgUrls").val().split(",").length-1;
			 var imgNum=$("#f1 .pictureResource").size();
			if(!isNaN(imgNum)){
				if((queue.selected+imgNum)>10){
					alert("一次活动最大上传10张图片！");
	            	$('#file_upload').uploadifive('clearQueue');
	            	return ;
				}else{
					$("#onUpload").trigger("click");
				}
			}
		},
		'onUploadComplete' : function(file, data) {
			custAct.addPictureResourceInstance(data)
							}
		});
}

/**
 * 回显图片资源对象
 */
custAct.addPictureResourceInstance=function(data){
	d =JSON.parse(data);
	var date=getPresentDate(0);
	if(d.statusCode==1){
		var pictureResourceInstance=$(".pictureResourceInstance").clone();
		pictureResourceInstance.attr("class","pictureResource")
		pictureResourceInstance.children(".pictureResourceType").val(0)
		pictureResourceInstance.children(".viewFrequency").val(0)
		pictureResourceInstance.children(".pictureUrl").attr("src",d.fileUrl)
		pictureResourceInstance.children(".isDel").val(0)
		pictureResourceInstance.show()
		$("#f1").append(pictureResourceInstance)
	}else{
		alert(data);
	}
}

/**
 * 表单提交动作
 */
$("#createArticle").click(function(){
	
	//获取表单元素
	var o=custAct.getArticleData();
	custAct.createArticle(o)
});

/**
 * 处理图片 添加域名
 * ps:192.168.1.43:8080/zgyt/upload/20160808/1470629115046.jpg
 */
//custAct.dealImg=function(t){
//	var imgs=t.split(",");
//	var t="";
//	var h=self.location.host;
//	for ( var i = 0; i < imgs.length-1; i++) {
////		t+=h+"/zgyt/"+imgs[i]+",";
//		t+=h+"/"+imgs[i]+",";
//	}
//	return t;
//};

/**
 * 处理图片 添加域名
 * ps:192.168.1.43:8080/zgyt/upload/20160808/1470629115046.jpg
 */
custAct.createArticle=function(o){
	//提交数据
	console.log(o)
	$.post("/article/saveArticle.do",o,function(data){
		console.log(data)
		if (data.statusCode==1) {
			alert("保存文章成功！");
		}else {
//			alert("创建文章失败，请联系管理员！！");
		}
	},"json");
}

/**
 * 获取表单元素
 */
custAct.getPictureListData=function(){
	//获取表单元素
	 var ps=new Array(); 
	 $("#f1").find("div[class='pictureResource']").each(function(){
		 data1={};
		 var t=$(this)
		 var id=t.children(".id").val();
		 if((!notNull(id))||(t.children(".isDel").val()==1)){
			 var articleId=t.children(".articleId").val();
			 var descriptions=t.children(".descriptions").val();
			 var pictureResourceType=t.children(".pictureResourceType").val();
			 var viewFrequency=t.children(".viewFrequency").val();
			 var height=t.children(".height").val();
			 var width=t.children(".width").val();
			 var createTime=t.children(".createTime").val();
			 var isDel=t.children(".isDel").val();
			 var pictureUrl=t.children(".pictureUrl").attr("src");
			 data1={
					 "id":id,
					 "articleId":articleId,
					 "descriptions":descriptions,
					 "pictureResourceType":pictureResourceType,
					 "viewFrequency":viewFrequency,
					 "height":height,
					 "width":width,
					 "createTime":createTime,
					 "isDel":isDel,
					 "pictureUrl":pictureUrl,
					 "other":"",
			 };  
			 ps.push(data1);  
		 }
	 })
//	 console.log($("#f1").find("div[class='pictureResource']").size())
//	 console.log(ps)
	 return ps;
};

custAct.getArticleData=function(){
	 var o={};
	 o["param"]=JSON.stringify(custAct.getPictureListData());
 	 var t= $("#f2").children("div[class='article']")
 
	 o["id"]=t.children(".id").val();
	 o["name"]=t.find("input[class=name]").val();
	 o["descriptions"]=t.find("textarea[class=descriptions]").val();
	 o["previewImgUrls"]=t.children(".previewImgUrls").val();
	 o["articleType"]=t.find("select[class=articleType]").val();
	 o["viewFrequency"]=t.children(".viewFrequency").val();
	 o["addMemberId"]=t.children(".addMemberId").val();
	 o["isShow"]=t.children(".isShow").val();
	 o["isDel"]=t.children(".isDel").val();
//	 console.log(o)
	 return o;
}

/**
 *删除图片 
 */
function removeImg(t){
	var src =$(t).parent().find("img").attr("src");
	$(t).parent().remove();
	$("#imgUrls").val($("#imgUrls").val().replace(src+",",""));
}

/**
 * 删除本张照片
 */
$("#f1").on("click",".del",function(){
	var t=$(this)
	if(confirm("确定要删除本张照片吗？\n为保存的数据将丢失！")){
//		t.parent().get(0).remove()
		t.parent().hide()
		t.parent().children("input[class=isDel]").val(1)
//		console.log(t.parent().hide())
	}
}) 

/**
 * 设置图片为预览图
 * 选中与取消动作
 */
$("#f1").on("click",".setviewImg",function(){
	var t=$(this)
	var src=t.parent().prevAll("img").attr("src")
	if(t.get(0).checked){
//		console.log($("#f2 .previewImgUrls").val())
		$("#f2 .previewImgUrls").val(src+";"+$("#f2 .previewImgUrls").val())
	}else{
		$("#f2 .previewImgUrls").val($("#f2 .previewImgUrls").val().replace(src+";",""))
	}
})

