var roleId,//角色ID 
resourceId, //角色资源ID
loginUserId;	//当前登陆者ID 
var paginationInfo = ""; //分页
var usersList = ""; //用户列表
var clientGroupList = ""; //分组列表
var myLabelList = ""; //我的标签列表

/**
 * 弹出层
 */
function myDialog(){
	var tipModal = "<div id='tipModal' class='modal fade bs-example-modal-sm' tabindex='-1' role='dialog' aria-labelledby='mySmallModalLabel'>"
			+ "		<div class='modal-dialog modal-sm'>"
			+ "			<div class='modal-content'>"
	        + "				<div class='modal-body'></div>"
	        + "			</div>"
	        + "		</div>"
	        + "</div>";
	var loadModal = "<div id='loadModal' class='modal fade bs-example-modal-sm' tabindex='-1' role='dialog' aria-labelledby='mySmallModalLabel'>"
		+ "		<div class='modal-dialog modal-sm'>"
		+ "			<div class='modal-content'>"
		+ "				<div class='modal-body'></div>"
		+ "			</div>"
		+ "		</div>"
		+ "</div>";
	var msgModal = "<div class='modal fade' id='msgModal' tabindex='-1' role='dialog'  aria-hidden='true'>"
				 + "	<div class='modal-dialog'>"
				 + "		<div class='modal-content'>"
				 + "			<div class='modal-header'>"
				 + "				<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>"
				 + "				<h4 class='modal-title'>提示框</h4>"
				 + " 			</div>"
			     + "			<div class='modal-body'></div>"
			     + "			<div class='modal-footer'>"
			     + "			   <button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>"
			     + "   			   <button type='button' class='btn btn-primary'>提交</button>"
			     + "			</div>"
			     + "		</div>"
			     + "	</div>"
			     + "</div>";
	$("body").append(msgModal);
	$("body").append(tipModal);
	$("body").append(loadModal);
}
/**
 * 隐藏弹出层
 */
function closeDialog(){
	$("#tipModal").modal("hide");
}

/**
 * 手机端用于有好提示功能菜单的
 */
var loadNav = function(active){
	var basePath = $("#basePath").val();
	$(".footer > a,#dropdown-menu > li").click(function(){
		if(!notNull($(this).data("url"))){
	  		$("#tipModal").find(".modal-body").html("很抱歉正在页面施工中...");
	  		$("#tipModal").modal("toggle");
			return;
		}
		$(".footer > a,#dropdown-menu > li").removeClass("active");
		$(this).addClass("active");
	});
	$(".footer > a,#dropdown-menu > li").each(function(){
		if($(this).data("index") == active){
			$(this).addClass("active");
		}
	});
};


/**
 * "1">金融, "2">政府及事业单位, "3">文化创意, "4">农业, "5">公益慈善 ,"6">专业服务 ,"7">工业制造,"8">IT互联网
 * "9">多元化集团 , "10">房地产 , "11">餐饮休闲娱乐会所 , "12">大健康 ,"13">其他
 * 通过索引返回对应的标题
 * @param index 行业类型索引
 * @returns 标题
 */
function getSelectTitle(index){
	switch (index) {
	case 1: return "金融"; break;
	case 2: return "政府及事业单位"; break;
	case 3: return "文化创意"; break;
	case 4: return "农业"; break;
	case 5: return "公益慈善"; break;
	case 6: return "专业服务"; break;
	case 7: return "工业制造"; break;
	case 8: return "IT互联网"; break;
	case 9: return "多元化集团"; break;
	case 10: return "房地产"; break;
	case 11: return "餐饮休闲娱乐会所"; break;
	case 12: return "大健康"; break;
	case 13: return "其他"; break;
	default: return ""; break;
	}
}

/**
 * 验证是否为数字
 * @param str
 * @returns true:是数字，false:不是数字
 */
function isNumber(str){
	var reg = new RegExp("^[0-9]*$");
	return reg.test(str);
}

/**
 * 验证字符串是否为空
 * @param s
 * @returns {Boolean}
 */
function notNull(s){
	if(s===""||s===null||s==="null"||s===undefined){
		return false;
	}
	if(s.toString().replace(/^\s+/, "" ).replace(/\s+$/, "" ).length===0){
		return false;
	}
	return true;
}
/**
 * 根据键 获取cookie 值
 * @param key
 * @returns
 */
function getCookieValue(key){
	var arrStr = document.cookie;
	var _theArray = arrStr.split(";");	//得到Cookie组
	for(var i = 0;i < _theArray.length;i ++){
		var temp = _theArray[i].split("=");		
		if(temp[0].replace(/[ ]/g,"") == key) {
			return unescape(temp[1].replace(/"/g,""));
		};	
	}
}
/**
 * 拓展Array 添加删除指定下标方法
 * @param dx 需要删除的下标
 * @returns 返回对象本身
 */
Array.prototype.remove=function(dx){
    if(!notNull(dx)||dx>this.length){
    	return this;
    }
    for(var i=0,n=0;i<this.length;i++){
        if(this[i]!=this[dx]){
            this[n++]=this[i];
        }
    }
    this.length--;
    return this;
} ;
/**
 * 截取字符串最后一个 逗号
 * @param str
 * @returns {String}
 */
function subStr(str){
	return str.substring(0, str.length-1)+";";
}
/**
 * 根据性别下标  获取性别
 * @param i 0 女  1男  其他不详
 * @returns {String}  返回处理后的性别
 */
function getSex(i){
	i=parseInt(i);
	if(i===0){
		return '女';
	}else if(i===1){
		return '男';
	}else{
		return '不详';
	}
}
/**
 * 格式化字符串
 * @param str -123*张三,-123*张李四,-123*王老五,
 * @return {"ids":"123,123,123","names":"张三,赵四,王五"}
 */
function formatStr1(str){
	if(!notNull(str)){
		return;
	}
	var strArr = str.split(","),ids = "",names = "",urlStr="";
	for (var int = 0; int < strArr.length; int++) {
		if(notNull(strArr[int])){
			var itemsArr = strArr[int].split("*");
			ids += itemsArr[0].replace("-","")+",";
			names += itemsArr[1]+",";
			urlStr += "<a href='client_toClientInfo?clientId="+itemsArr[0]+"'>"+itemsArr[1]+"</a>,";
		}
	}
	ids = ids.substring(0,ids.length-1);
	names = names.substring(0,names.length-1);
	urlStr = urlStr.substring(0,urlStr.length-1);
	return {"ids":ids,"names":names,"urlStr":urlStr};
};

/**
 * 模版工具
 * @param temp 模版(jquery选择器)
 * @param json json
 * @param dest 展示位置(jquery选择器)
 * @return templateStr
 */
function handleUtil(temp,json,dest){
	var tempStr = Handlebars.compile($(temp).html())(json);
	$(dest).html(tempStr);
	return tempStr;
}

/**
 * 用户相关功能对象
 */
var userAct = {};
/**
 * 跳转发送消息页面
 * ids: 客户资料id
 */
userAct.toMsg = function(ids){
	if(notNull(ids)){
		//申请发送消息
		var url = $("#basePath").val()+"msg_toMsg?cids="+ids;
		location.href = url;
	}else{
  		$("#tipModal").find(".modal-body").html("请选择至少一个客户!");
  		$("#tipModal").modal("show");
	}
};
/**
 * 我的收藏
 */
userAct.collect = function(ids){
	if(notNull(ids)){
		var url = $("#basePath").val()+"collect_addCollect";
		$.post(url,{"clientIds":ids},function(data){
			if(data.statusCode == "1"){
		  		$("#tipModal").find(".modal-body").html("收藏成功！");
			}else{
		  		$("#tipModal").find(".modal-body").html("收藏失败！Error:"+data.statusCode);
			}
			$("#tipModal").modal("show");
		});
	}else{
  		$("#tipModal").find(".modal-body").html("请选择至少一个客户!");
  		$("#tipModal").modal("show");
	}
};



(function(){
	//为初始成员变量赋值 当前登陆者id  当前登陆者角色 当前登陆者资源
	loginUserId=getCookieValue("loginUserId");
	var s =getCookieValue("roleInfo");
	if(notNull(s)){
		var a= s.split(":");
		roleId=a[0]; //用户角色
		resourceId=a[1]; //用户权限
	}
	//初始化弹出层
	myDialog();
})();

/**
 * 格式化字符串
 * @param str 张三*10,李四*99,张三*10,李四*99
 * @return {"ids":"123,123,123","names":"张三,赵四,王五"}
 */
function formatStr2(str){
	if(!notNull(str)){
		return;
	}
	var strArr = str.split(","),ids = "",names = "",urlStr="";
	for (var int = 0; int < strArr.length; int++) {
		if(notNull(strArr[int])){
			var itemsArr = strArr[int].split("*");
			ids += itemsArr[1].replace("-","")+",";
			names += itemsArr[0]+",";
			urlStr += "<a href='client_toClientInfo?clientId="+itemsArr[0]+"'>"+itemsArr[1]+"</a>,";
		}
	}
	ids = ids.substring(0,ids.length-1);
	names = names.substring(0,names.length-1);
	urlStr = urlStr.substring(0,urlStr.length-1);
	return {"ids":ids,"names":names,"urlStr":urlStr};
};

/**
 * 处理维护人id字符串
 * @param maintainUserId  张三*10,李四*99,张三*10,李四*99
 * @param type 0:前面 1：后面
 * @returns 
 */
function maintainUserIdUtil(maintainUserId,type){
	if (!notNull(maintainUserId)) {
		return;
	}
	if (type==0) {//0:前面
		return formatStr2(maintainUserId).names;
	}else {//1：后面
		return formatStr2(maintainUserId).ids;
	}
}

/**
 * 将毫秒转换为日期字符串
 * @param time 需要转换的毫秒值 
 * @param format 需要格式化的字符  (eg:yyyy-MM-dd HH:mm:ss )(eg:yyyy年MM月dd日 HH时mm分ss秒 )
 * @returns 返回格式化后的字符串
 */
function formatDateToMillisecond(time, format){
	if(time=="")return"";
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i;};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    });
}


/**
 * 获取当前时间
 * @param t
 * @returns
 */
function getPresentDate(t){
	var date=new Date();
	var d="";
	var month=date.getMonth()+1;
	var day=date.getDate();
	if (day<10) {
		day="0"+day;
	}
	if (month<10) {
		month="0"+month;
	}
	
	if (1==t) {//日期中间带横杠"-"
		d=date.getFullYear()+"-"+month+"-"+day;
	}else{//日期中间不带横杠"-"
		d=date.getFullYear()+""+month+""+day;
	}
	return d;
}

/**
 * 根据传人的url地址 获取参数value
 * @param url 需要分解的url
 * @param key 需要提前的key
 * @returns {String} 没有则返回null
 */
function getUrlParameterByKey(url,key){
	var a,b,c;
	if(url.lastIndexOf("\?")>0){
		a=url.split("\?")[1];
	}
	if(a){
		b= a.split("&");
		for(var i =0;i<b.length;i++){
			c=b[i].split("\=");
			if(c[0]==key){
				return c[1];
			}
		}
	}
	return null;
}

/**
 * 获取地址栏参数
 * GetQueryString("key")
 * @param key
 * @return value
 */
function getQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

/**
 * 返回当前的日期和时间的字符串
 * @param t
 * @returns {String}
 */
function getPresentDateAndTime(d,t){
	var intValue  = new Date().getTime()+1000*(24*3600*(d));
	var  endDate  =  new  Date  (intValue);  
	
	var year=endDate.getFullYear();
	var month=endDate.getMonth()+1;
	var day=endDate.getDate();
	if (month<10) {
		month="0"+month;
	}
	if (day<10) {
		day="0"+day;
	}
	return(year+"-"+month+"-"+day+" 00:00:0"+t);
}

function replaceEnter(a) { 
	return a.replace(/\n/g,"<br/>");
}
function displayEnter(a) { 
	return a.replace(/<br\/>/g,'\n')
}