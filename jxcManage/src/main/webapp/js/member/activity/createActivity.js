
$(function() {
	$("a").attr("href","javascript:void(0)");//禁用模版的a标签
	$("#activityForm").find("img").each(function(){
		$(this).css("display","none");
	});
	
//	// 加载活动模版详情
//	loadActivityModelInfo();
//	
//	//初始化单选多选问题组件
//	initQuestionTool();
//	
//	//将html元素中的提交按钮禁用
//	$("#content").find("input[type=submit]").attr("disabled","disabled");		
	
	//初始化图片上传
	initUploadImg();
	
	$("#selfShare").hide();
	
});

/**
 * 初始化表单验证排除字段
 */
function initExcludeCheckList(){
	var activityType=$("#activityType").val()
	var excludeCheckList="activityAddress,activityNote";
	var excludeS=excludeCheckList.split(",");
	if (activityType==7) {
		$("#excludeCheckList").val(excludeCheckList);
		for ( var i = 0; i < excludeS.length; i++) {
			$("#"+excludeS[i]).next().hide();
		}
	}else {
		if (notNull(activityType)) {
			for ( var i = 0; i < excludeS.length; i++) {
				$("#"+excludeS[i]).next().show();
			}
			$("#excludeCheckList").val("");
		}
	}
}
/**
 * 初始化单选多选问题组件
 */
function initQuestionTool(){
	///************************
	//单选框部分  
	var radioboxId=0;
	var radioboxName=0;
	var singleChoseTitle="";//单选框标题
	//创建单选问题
	$("#addOnlyChose").click(function(){
		var singleChoseHtml="";//单选页面元素
		//  问题   ""
		//获取问题内容
		var iusse=$("#onlyChoseIssue").val();
		//获得选择元素
//		var choseItem=$("#singleChose").html();
		var radios="";
		//添加单选框
		$("#onlyChoseItem").find("input[type=text]").each(function() {
			if (null!=$(this).val()&&"radios"==$(this).attr("id")) {
				if (""!=radios) {
					radios+=",";
				}
				radios+=$(this).val();
			}
		});
		singleChoseTitle="<label style='font-weight:bold;' id='title'>"+iusse+"</label><br>";//问题题目
		var radiosArr=radios.split(",");
		for ( var i = 0; i < radiosArr.length; i++) {
			singleChoseHtml+="<input value='1' id='rs"+radioboxId+i+"' name='"+radioboxName+"' type='radio'><label for='rs"+radioboxId+i+"'>"+ radiosArr[i]+"</label>&nbsp&nbsp";
		}
		radioboxId++;
		radioboxName++;
		if (1==$('#otherSingleChose').children('input').length){
			singleChoseHtml+="其他:<input type='text' value='' id='other'"+radioboxId+">";
		}
		var s = "<div style='display:block;' class='singleSelect'>"+singleChoseTitle+singleChoseHtml+"</div>";
		$("#xq").append(s);
		$("#onlyChoseItem").find("input[type=text]").each(function(){
			if ($(this).attr("placeholder")=="请输入第1个选项内容") {
				$(this).val("");
			}else {
				$(this).remove();
			}
		});
		$("#onlyChoseIssue").val("");
		$("#otherSingleChose").empty();
		$("#myModal1").modal('hide');
		single=2;
	});
	
	/**
	 *  添加单选答案
	 */
	var single=2;
	$("#addOnlyChoseItem").click(function(){
		var s = "<input type='text' class='form-control' id='radios' placeholder='请输入第"+single+"个选项内容'>";
//		$(this).parent().prev().prepend(s);
		single++;
		$("#onlyChoseItem").append(s);
	});
	/**
	 * 显示单选框
	 */
	$("#showOnlyChose").click(function(){showOrHideDomById("onlyChose");});
	/**
	 * 单选框其他答案
	 */
	$("#onlyInput").click(function(){//每个问题至多有一个其他答案
		if (1==$('#otherSingleChose').children('input').length) {
			$("#otherSingleChose").empty();
		} else {
			var s="<label for='tept_content'>其他答案</label><span style='color:#f00'>*</span>";
			s+="<input type='text' class='form-control' id='onlyChose1' placeholder='请输入其他选项内容'>";
			$("#otherSingleChose").append(s);
		}
	});
	
	
	///************************
	//多选框部分 
	var checkboxId=0;
	var checkboxName=0;
	var mutilChoseTitle="";//多选问题标题
	//创建多选问题
	$("#addMutilChose").click(function(){
		var mutilChoseHtml="";//多选问题选项
		var bd = $("#bd");
		///moerChoseIssue    moerChoseItem
		//  问题   ""
		//获取问题内容
		var iusse=$("#mutilChoseIssue").val();
		//获得选择元素moreChoseItem
		var choseItem=$("#mutilChoseItem").html();
		var otherCheckBoxs="";
		var checkBoxs="";
		$("#mutilChoseItem").find("input[type=text]").each(function() {
			if (null!=$(this).val()&&"checkBoxs"==$(this).attr("id")) {
				if (""!=checkBoxs) {
					checkBoxs+=",";
				}
				checkBoxs+=$(this).val();
			}
		});
		mutilChoseTitle="<label style='font-weight:bold;' id='title'>"+iusse+"</label><br>";//问题题目
		var checkBoxsArr=checkBoxs.split(",");
		for ( var i = 0; i < checkBoxsArr.length; i++) {
			mutilChoseHtml+="<input value='1' id='rs"+checkboxId+i+"' name='"+checkboxName+"' type='checkbox'><label for='rs"+checkboxId+i+"'>"+ checkBoxsArr[i]+"</label>&nbsp&nbsp";
		}
		checkboxId++;
		checkboxName++;
		if (1==$("#otherCheckBox").children('input').length){
			mutilChoseHtml+="其他:<input type='text' value='' id='other'"+checkboxId+">";
		}
		var s = "<div style='display:block;' class='mutilSelect'>"+mutilChoseTitle+mutilChoseHtml+"</div>";
		$("#xq").append(s);
		
		//还原多选弹出框
		$("#mutilChoseItem").find("input[type=text]").each(function(){
			if ($(this).attr("placeholder")=="请输入第1个选项内容") {
				$(this).val("");
			}else {
				$(this).remove();
			}
		});
		$("#mutilChoseIssue").val("");
		$("#otherCheckBox").empty();
		$("#myModal2").modal('hide');
		mutil=2;
	});
	/**
	 * 添加多选问题答案
	 */
	var mutil=2;
	$("#addMutilChoseItem").click(function(){
		var s = "<input type='text' class='form-control' id='checkBoxs' placeholder='请输入第"+mutil+"个选项内容'>";
		mutil++;
		$("#mutilChoseItem").append(s);
	});
	
	/**
	 * 显示多选框
	 */
	$("#showMoreChose").click(function(){showOrHideDomById("moreChose");});
	/**
	 * 添加多选框其他答案
	 */
	$("#mutilInput").click(function(){//每个问题至多有一个其他答案
		if (1==$('#otherCheckBox').children('input').length) {
			$("#otherCheckBox").empty();
		} else {
			var s="<label for='tept_content'>其他答案</label><span style='color:#f00'>*</span>";
			s+="<input type='text' class='form-control' id='mutilChose1' placeholder='请输入其他选项内容'>";
			$("#otherCheckBox").append(s);
		}
	});
};

/**
 * 获取表单数据
 */
function getPageData(){
	var activitySynopsis = UE.getEditor('editor').getContent();// 获取富文本编辑器中的内容
	//处理图片
	var $t =$("<div>"+activitySynopsis+"</div>");
	$t.find("img").each(function(){
	if (notNull($(this).attr("src"))) {
		if ($(this).attr("src").indexOf("http",0)==-1) {
			$(this).attr("src","http://crm.qyxml.cc/"+$(this).attr("src")).css({"width":"100%","height":"100%"});
		}else if ($(this).attr("src").indexOf("http://crm.qyxml.cc",0)==-1) {
			$(this).remove();
		}
	}
	});
	activitySynopsis=$t.html();
	
	$("#bd > div:visible").each(function() {
	htm+= $(this).prop("outerHTML");
	});
	
	//表单一
	var param1 = $("#f1").serializeArray();
	var o = {};
	for(var i =0;i<param1.length;i++){
		o[param1[i].name]=param1[i].value;
	}
	
	$("#f1").find("select[name='activity.shareImg']").find("option").each(function(){//处理自定义分享图片
		if ($(this).prop("selected")&&$(this).html()=="上传自定义图片") {
			o["activity.shareImg"]=dealShareImg($(this).val());
		}
	});
	//checkbox内容提交
	//除去openRefundRules，openPayRule两项
	$("#f3").find("input[type=checkbox]").each(function(){
		if ($(this).attr("id")!="openRefundRules"&&$(this).attr("id")!="openPayRule") {
			var id=$(this).attr("id");
			if ($(this).is(':checked')) {
				o["activityInfo."+id]="1";
			} else {
				o["activityInfo."+id]="0";
			}
		}
	});
	
	//处理活动费用activityMoney
	if ($("#isToll").get(0).checked) {
		o["activityInfo.activityMoney"]=$("#activityMoney").val();
		o["activitySignUpPayRule.signUpMoney"]=$("#activityMoney").val();
	}else {
		o["activityInfo.activityMoney"]=0;
		o["activitySignUpPayRule.signUpMoney"]=0;
	}
	/**
	 * 获取页面元素
	 */
//	//Dom元素显示项
	var s = "";
	$(".hiden").each(function(i,t){
		if($(t).is(":visible")){
			s+=$(t).attr("id")+",";
		}
	});
	s = s.substring(0,s.lastIndexOf(","));
	o["activity.activityShowDOM"]=getShowDOMSelect();
	
	o["activity.activityEssentialDOM"]=getEssentialDOMSelect();
	
	
	//获得活动图片链接
	var imgs=$("#imgUrls").val().substring("",$("#imgUrls").val().lastIndexOf(","));
	o["activity.activityImg"]=dealImg(imgs);
	
	o["activity.activitySynopsis"]=activitySynopsis;
	o["activity.activityPageHtml"]=htm;
	if($("#isNeedCheck").is(':checked')){
		o["activityInfo.isNeedCheck"]="1";
	}else {
		o["activityInfo.isNeedCheck"]="0";
	}
	o["activityInfo.activityNote"]=$("#activityNote").val().replace(/\n/g,"<br/>");
	//付款规则
	if ($("#openPayRule").get(0).checked) {
		var payRule="";
		for ( var i = 0; i < 9; i++) {
			if (i==2||i==5||i==8) {
				if (notNull($("#payRule"+(i+1)).val())) {
					payRule+=$("#payRule"+(i+1)).val()+";";
				}
			}else{
				if (notNull($("#payRule"+(i+1)).val())) {
					if (i==1||i==4||i==7) {
						payRule+="="+$("#payRule"+(i+1)).val()+":";
					}else {
						payRule+=$("#payRule"+(i+1)).val()+":";
					}
				}
			}
		}
		o["activitySignUpPayRule.payRule"]=trimSym(payRule);
	}
	//退款规则
	// fd11 是否开启退款规则"&activityInfo.openRefundRules="
	if ($("#openRefundRules").get(0).checked) {// 开启退款规则
		// 获取退款规则
		var t="";
		$("#refundRules").find("input[type=text]").each(function() {
			t += $(this).val();
			if (!($(this).attr("id") == "refundRules5")) {
				t += ",";
			}
		});
		o["activitySignUpPayRule.refundRule"]=t;
	}
	//
	var param2=$("#f3").serializeArray();
	for(var i =0;i<param2.length;i++){
		o[param2[i].name]=param2[i].value;
	}
	return o;
}

/**
 * 创建活动
 */
$("#createActivity").click(function() {
				if (getTimeStamp()==$("#checkTime").val()) {
					return;
				}
				
				// 需要创建的活动表单
				var htm = "";
				var param1;
				var param3 = "";
//				var activitySynopsis = UE.getEditor('editor')
//						.getContent();// 获取富文本编辑器中的内容
//				//处理图片
//				var $t =$("<div>"+activitySynopsis+"</div>");
//				$t.find("img").each(function(){
//					if (notNull($(this).attr("src"))) {
//						if ($(this).attr("src").indexOf("http",0)==-1) {
//							$(this).attr("src","http://crm.qyxml.cc/"+$(this).attr("src")).css({"width":"100%","height":"100%"});
//						}else if ($(this).attr("src").indexOf("http://crm.qyxml.cc",0)==-1) {
//							$(this).remove();
//						}
//					}
//				});
//				activitySynopsis=$t.html();
//				
//				$("#bd > div:visible").each(function() {
//					htm+= $(this).prop("outerHTML");
//				});
				
				//校验表单
				//若可以提交，则设置$("#checkForm")标签的value属性为true
				if (!checkForm()) {
					$("#createActivity").prop("disabled",false);
					return;
				}else {
					$("#checkForm").val("true");
				}
				
				
				// 表单三
				param=$("#f3").serializeArray();
				for(var i =0;i<param.length;i++){
					o[param[i].name]=param[i].value;
				}
				
				var o=getPageData();
				o=getCustomerList(o);
				console.log(o)
				//处理调研活动选择项
				//(1)处理单选
				var singleSelect="&singleChoseInfo=";//单选问题答案
				var mutilSelect="&mutilChoseInfo=";//多选问题答案
				var singleCount =1;
				var mutilCount =1;
				var count=0;
				var count1=0;
				$("div.singleSelect").find("label").each(function() {
					if ("font-weight:bold;"==$(this).attr("style")) {
						if (count!=0) {
							count=0;
						}
						singleSelect+="asingleIssue="+$(this).html()+",";
					}else{
						if (count==0) {
							singleSelect+="singleChoseInfo="+$(this).html()+",";
						}else {
							singleSelect+=$(this).html()+",";
						}
						count++;
					}
				});
				
				//是否有其他---选项    若该问题存在其他选项，则记录其他选项出现的顺序
				singleSelect+="singleOther=";
				$("#xq").find("div.singleSelect").each(function(){
					$(this).find("input").each(function() {
						if ("other"==$(this).attr("id")){//得到单选问题有其他选项时 所排的位置
							singleSelect+=singleCount+",";
						}
					});
					singleCount++;
				});
				singleSelect=singleSelect.substring("",singleSelect.lastIndexOf(","));
				param3+=singleSelect;
				
				//(2)处理多选
				$("div.mutilSelect").find("label").each(function() {
					if ("font-weight:bold;"==$(this).attr("style")) {
						if (count1!=0) {
						}
						mutilSelect+="amutilIssue="+$(this).html()+",";
					}else{
						if (count1==0) {
							mutilSelect+="mutilChoseInfo="+$(this).html()+",";
						}else {
							mutilSelect+=$(this).html()+",";
						}
						count1++;
					}
				});
				
				//是否有其他选项
				mutilSelect+="mutilother=";
				$("#xq").find("div.mutilSelect").each(function(){
					$(this).find("input").each(function(){
						if ("other"==$(this).attr("id")){
							mutilSelect+=mutilCount+",";
						}
					});
					mutilCount++;
				});
				mutilSelect=mutilSelect.substring("",mutilSelect.lastIndexOf(","));
				param3+=mutilSelect;
				
				if (param3!="") {
					param3="?"+param3;
				}
				
				$("#loadModal").find(".modal-body").html("<img src='img/load.gif' class='mr10' style='width: 20px; height: 20px;'>正在加载数据...");
				$("#loadModal").modal({backdrop:"static"},"show");
				//提交活动
//				if ($("#checkForm").val()=="true") {// 可以提交
//					$("#checkTime").val(getTimeStamp());
//					$.post("activity_createActivity"+param3,o,function(data) {
//						if (data.statusCode == 1) {
//							$("#loadModal").modal("hide");
//							alert("创建活动成功");
//						} else if (data.statusCode==-7) {
//							$("#loadModal").modal("hide");
//							alert("权限不足，创建活动失败");
//						}else if(data.statusCode==-8){
//							$("#loadModal").modal("hide");
//							alert("创建活动失败");
//						}
//					}, "json");
//					$("#createActivity").prop("disabled",false);
//				}
			});

/**
 * 初始化图片上传
 */
function initUploadImg(){
	
	/**
	 * 图片上传
	 */
	var path="";
	var paths="";
	var size=5;
	$('#file_upload').uploadifive({
		'auto'             : false,//是否自动提交
		'fileExt'    :'*.png',
		'fileDesc'    :'请选择png格式图片',
		'formData'         : {
								'timestamp' : new Date().getTime(),
								'updateType': 2, //上传类型
								'isCutImg':1,	// 是否压缩图片
								'isCreateMinImg':1//是否生成小图片
							},//上传参数
		'queueID'          : 'queue',//进度条位置
		'uploadScript'     : 'file/uploadPic2.do',//上传地址
		'buttonText':'上传图片',//按钮显示名称
		'fileObjName':'myFile',// 上传文件名
		'fileSizeLimit':3096,// 文件大小限制
		'method':'post',//上传方式
		'uploadLimit':0,//最大上传数量
		'onSelect':function(queue){
			console.log(queue)
			var imgNum = $("#imgUrls").val().split(",").length-1;
			if(!isNaN(imgNum)){
				if((queue.selected+imgNum)>5){
					alert("一次活动最大上传5张图片！");
	            	$('#file_upload').uploadifive('clearQueue');
	            	return ;
				}else{
					$("#onUpload").trigger("click");
				}
			}
		},
		'onUploadComplete' : function(file, data) {
								d =JSON.parse(data);
								var date=getPresentDate(0);
								if(d.statusCode==1){
//									$("#show1").attr('src',"upload/"+new Date().toUTCString()
//											+"/"+d.filePath);<img id="show1" alt="" src="" style="height: 30px; width: 30px;">
									path="upload/"+date+"/"+d.filePath+",";
									$("#imgUrls").val(path+$("#imgUrls").val());//拼接字符串
									var img="";
								    img="<div id='"+d.filePath+"' style='height: auto; width: auto; float: left;'><img id='show' alt='' src='"+$("#basePath").val()+"upload/"+date+"/"+d.filePath+"'  style='height: 40px; width: 40px;'>";
									img+="&nbsp<button onclick='removeImg(this)' style='height: 30px;width: 50px;margin-top: -50px;margin-left: 0px'>删除</button>";////////////
									size=5-paths.split(",").length;
									img+="</div>";//<input type='hidden' value='"+paths+"' id='imgUrlssss' data-value='"+size+"'>
									$("#image").append(img);
								}else{
									alert(data);
								}
							}
		});
}
/******单选多选框生成js*****/
/**
 * 获取 调研用户基本信息表单
 * @returns {String}
 */
function getAllBaseInfo() {
	var htm = "";
	$("#bd > div:visible").each(function() {
		htm += $(this).prop("outerHTML");
	});
	return htm;
}
/**
 * 根据一个ID 显示或隐藏一个dom 元素
 * @param id
 */
function showOrHideDomById(id){
	var $t=$("#"+id);
	if($t.is(':visible')){
		$t.hide();
	}else{
		$t.show();
	}
}

/**
 * 改变联动状态（活动费用，和退款规则两个属性）
 */
$("#isToll").change(function() {
	var $_isToll = $("#isToll");
	var $_activityMoney = $("#activityMoney");
	var $_openRefundRules = $("#openRefundRules");
	var $_openPayRule = $("#openPayRule");
	if ($_isToll.get(0).checked == true) {// 付费活动
//		$("#activityMoneyMsg").html("若为付费活动，则必须填写活动费用");
//		$("#activityMoneyMsg").show();
//		$_activityMoney.attr("data-value", "false");
		$_activityMoney.val(200);
		$_activityMoney.removeAttr("readonly");
		$_openRefundRules.removeAttr("disabled");
		$_openPayRule.removeAttr("disabled");
	} else {
		$_activityMoney.attr("readonly", "readonly");
		$_activityMoney.attr("data-value", "true");
		$_activityMoney.val(0);
		$("#activityMoneyMsg").html("");
		
		$_openRefundRules.removeAttr("checked");//退款规则
		$_openRefundRules.attr("disabled","disabled");
		
		$_openPayRule.removeAttr("checked");//付款规则
		$_openPayRule.attr("disabled","disabled");
	}
});

/**
 * 设置退款规则只读
 */
$("#openRefundRules").change(function() {
	var $_v = $("#openRefundRules");
//	var $_refundRules = ("#refundRules");
	if ($_v.get(0).checked == true) {
		$("#refundRulesMsg").html("若勾选开启退款规则，则必须填写退款规则");
		$("#refundRulesMsg").show();
		$("#refundRules").attr("data-value","false");
		$("#refundRules").find("input[type=text]").each(function() {
			$(this).removeAttr("readonly");
		});
	} else {
		$("#refundRules").find("input[type=text]").each(function() {
			$("#refundRules").attr("data-value","true");
			$("#refundRulesMsg").html("");
			$(this).attr("readonly", "readonly");
		});
	}
});

/**
 * 验证活动主题
 */
$("#activityTitle").blur(function() {
	var $_v = $("#activityTitle");
	if ("" == $_v.val()) {
		$("#activityTitleMsg").html("请输入活动名称！");
		$("#activityTitleMsg").show();
		$_v.attr("data-value", "false");
		return;
	} else {
			$_v.attr("data-value", "true");
			$("#activityTitleMsg").hide();
	}
});
/**
 * 验证活动地点
 */
$("#activityAddress").blur(function() {
	var $_v = $("#activityAddress");
	if ("" == $_v.val()) {
		$("#activityAddressMsg").html("请输入活动地点！");
		$("#activityAddressMsg").show();
		$_v.attr("data-value", "false");
		return;
	} else {
		$_v.attr("data-value", "true");
		$("#activityAddressMsg").hide();
	}
});

/**
 * 验证活动费用
 */
$("#activityMoney").blur(function() {
	var $_v = $("#activityMoney");
	if ($("#isToll").get(0).checked == true) {
		if ("" == $_v.val()) {// 输入框为空值
			$_v.attr("data-value", "false");
			$("#activityMoneyMsg").html("请输入活动费用");
			$("#activityMoneyMsg").show();
		} else {
			$_v.attr("data-value", "true");
			$("#activityMoneyMsg").hide();
		}
	}
});

/**
 * 验证活动日期先后
 */
function checkDate(data) {
	var $_v = $("#endDate");
	var createDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	createDate=createDate.replace(/[^0-9]{1}[^0-9]*/g,"");
	endDate=endDate.replace(/[^0-9]{1}[^0-9]*/g,"");
	if (createDate > endDate||createDate == endDate) {
		$("#endDateMsg").html("活动开始时间必须早于活动结束时间！");
		$("#endDateMsg").show();
		$_v.attr("data-value", "false");
		return;
	} else {
		$_v.attr("data-value", "true");
		$("#endDateMsg").hide();
		//向活动显示时间input框赋值
		var activityShowData=$("#beginDate").val().split(" ")[0]+"~"+$("#endDate").val().split(" ")[0];
		$("#activityShowData").val(activityShowData);
	}
}

/**
 * 验证分享标题
 */
$("#shareTitile").blur(function() {
	var $_v = $("#shareTitile");
	if ("" == $_v.val()) {
		$("#shareTitileMsg").html("请填写分享标题！");
		$("#shareTitileMsg").show();
		$_v.attr("data-value", "false");
		return;
	} else {
		$_v.attr("data-value", "true");
		$("#shareTitileMsg").hide();
	}
});

/**
 * 验证分享内容
 */
$("#shareContetn").blur(function() {
	var $_v = $("#shareContetn");
	if ("" == $_v.val()) {
		$("#shareContetnMsg").html("请填写分享内容！");
		$("#shareContetnMsg").show();
		$_v.attr("data-value", "false");
		return;
	} else {
		$_v.attr("data-value", "true");
		$("#shareContetnMsg").hide();
	}
});

// var form3=0;
/**
 * 验证退款规则
 */
function checkRefundRule(t) {
	var $_isToll = $("#isToll");
	var $_activityMoney = $("#activityMoney");
	var $_v = $("#refundRules");
	var a = new Array();
	var i = 0;
	if ($("#isToll").get(0).checked == false) {// 进行验证 若活动为免费活动，则关闭退款规则
		$_v.attr("data-value", false);
	}
	$("#refundRules").find("input[type=text]").each(function() {
		if (!("" == $(this).val())) {
			a[i++] = $(this).val();
		}
	});
	if ($("#openRefundRules").get(0).checked == true) {// 进行验证
		if (a.length < 5) {// 退款规则填写不完整
			$("#refundRulesMsg").html("请完整填写退款规则！");
			$("#refundRulesMsg").show();
			$_v.attr("data-value", "false");
			return;
		}
		if (eval(a[0]) < eval(a[2])) {
			$("#refundRulesMsg").html("退款天数填写错误！");
			$("#refundRulesMsg").show();
			$_v.attr("data-value", "false");
			return;
		}
		if (eval(a[4]) <= eval(a[1]) || eval(a[4]) <= eval(a[3])
				|| eval(a[1]) > eval(a[3])) {
			$("#refundRulesMsg").html("扣费数额填写错误！");
			$("#refundRulesMsg").show();
			$_v.attr("data-value", "false");
			return;
		}
		else {
			if (eval(a[4])>$_activityMoney.val()) {
				$("#refundRulesMsg").html("扣费数额不能大于活动费用！");
				$("#refundRulesMsg").show();
				return;
			}
		}
		$_v.attr("data-value", "true");
		$("#refundRulesMsg").hide();
	}
};

/**
 * 验证活动联系人非空
 */
$("#activityContactsPeople").blur(function() {
	var $_v = $("#activityContactsPeople");
	if ("" == $_v.val()) {
		$("#activityContactsPeopleMsg").html("请填写活动联系人！<br/>");
		$("#activityContactsPeopleMsg").show();
		$_v.attr("data-value", "false");
		return;
	} else {
		$_v.attr("data-value", "true");
		$("#activityContactsPeopleMsg").hide();
	}
});

//付款规则
$("#openPayRule").change(function(){
	var $_v = $("#openPayRule");
	if ($("#openPayRule").get(0).checked==true) {
		$("#payRulesMsg").html("若开启付款规则，则必须填写付款规则！");
		$("#payRulesMsg").show();
		$("#payRule").attr("data-value",false);
		$("#payRule").children("div").children("input").removeAttr("readonly");
	}else {
		$("#payRulesMsg").html("");
		$("#payRulesMsg").hide();
		$("#payRule").attr("data-value",true);
		$("#payRule").children("div").children("input").val("");
		$("#payRule").children("div").children("input").attr("readonly","readonly");
	}
});

/**
 * 验证付款规则填写是否符合要求
 */
function checkPayRule() {
	var $_isToll = $("#isToll");
	var $_activityMoney = $("#activityMoney");
	var $_v = $("#payRule");
	var a = new Array();
	var i = 0;
	if ($_isToll.get(0).checked == false) {// 进行验证 若活动为免费活动，则关闭退款规则
		$_v.attr("data-value", true);
	}
	$_v.find("input[type=text]").each(function() {
		if (notNull($(this).val())) {
			a[i++] = $(this).val();
		}
	});
	
	if ($("#openPayRule").get(0).checked == true) {// 进行验证
		var group=0;
		var result=1;
		switch (a.length) {
		case 3:
			group=1;
			break;
		case 6:
			group=2;
			break;
		case 9:
			group=3;
			break;
		default: 
			result=0;
			break
			}
			if (group==1) {//填写一组付款规则
				for ( var i = 0; i < 3; i++) {
					if (!notNull($("#payRule"+(i+1)).val())) {
						result=0;//不符合条件
					}
				}
			}else if(group==2){//填写两组付款规则
				for ( var i = 0; i < 6; i++) {
					if (!notNull($("#payRule"+(i+1)))) {
						result=0;
					}
				}
				if (!checkPayRuleDate(2)) {
					$_v.attr("data-value", false);
					$("#payRulesMsg").html("付款规则日期填写错误！");
					$("#payRulesMsg").show();
					return;
				}
			}else if(group==3){//填写三组付款规则
				for ( var i = 0; i < 9; i++) {
					if (!notNull($("#payRule"+(i+1)))) {
						result=0;
					}
				}
				if (!checkPayRuleDate(3)) {//付款规则日期填写错误
					$_v.attr("data-value", false);
					$("#payRulesMsg").html("付款规则日期填写错误！");
					$("#payRulesMsg").show();
					return;
				}
			}
			if (result==0) {//付款规则填写不完整
				$_v.attr("data-value", false);
				$("#payRulesMsg").html("付款规则填写不完整！");
				$("#payRulesMsg").show();
			}else {
				$_v.attr("data-value",true);
				$("#payRulesMsg").html("");
				$("#payRulesMsg").hide();
			}
		
	   }
}
/**
 * 检查付款规则日期填写是否正确
 * @param t 付款规则组数 1组，2组，3组
 * @returns true 正确 false不正确
 */
function checkPayRuleDate(t) {
	var result=true;
	if (t==2&&(eval($("#payRule1").val())<=eval($("#payRule4").val()))) {
		//活动日期填写错误
		result=false;
			
	}else if(t==3){
		if (eval($("#payRule1").val())<=eval($("#payRule4").val())||eval($("#payRule4").val())<=eval($("#payRule7").val())) {
			result=false;
		}
	}
	return result;
}


/**
 * 活动联系人手机号码非空
 */
$("#activityContactsPeopleNo").blur(function() {
	var $_v = $("#activityContactsPeopleNo");
	if ("" == $_v.val()) {
		$("#activityContactsPeopleNoMsg").html("请填写活动联系人手机号码！<br/>");
		$("#activityContactsPeopleNoMsg").show();
		$_v.attr("data-value", "false");
		return;
	}
	else {
		var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test($_v.val())) { 
			$("#activityContactsPeopleNoMsg").html("请输入有效的手机号码!(11位)<br/>");
			$("#activityContactsPeopleNoMsg").show();
			$_v.attr("data-value", "false");
		} 
		else {
			$_v.attr("data-value", "true");
			$("#activityContactsPeopleNoMsg").hide();
		}
	}
});

/**
 * 验证最大活动人数
 */
//var createDate;
$("#maxPeopleNum").blur(function() {
	var $_v = $("#maxPeopleNum");
	if ("" == $_v.val()) {
		$("#maxPeopleNumMsg").html("请填写最大活动人数！<br/>");
		$("#maxPeopleNumMsg").show();
		$_v.attr("data-value", "false");
		return;
	} else {
		$_v.attr("data-value", "true");
		$("#maxPeopleNumMsg").hide();
	}
});

/**
 * 验证活动填写说明
 */
//var createDate;
$("#activityNote").blur(function() {
	var $_v = $("#activityNote");
	if ("" == $_v.val()) {
		$("#activityNoteMsg").html("请填写活动填写说明！<br/>");
		$("#activityNoteMsg").show();
		$_v.attr("data-value", "false");
		return;
	} else {
		$_v.attr("data-value", "true");
		$("#activityNoteMsg").hide();
	}
});

/**
 *删除图片 
 */
function removeImg(t){
	
	if ($(t).parent().parent().attr("id")=="selfShareImg") {
		uploadShareImg();//重置图片上传组件
		$("#selfUploadImg").remove();
		return;
	}
	
	var src =$(t).parent().find("img").attr("src");
	$(t).parent().remove();
	$("#imgUrls").val($("#imgUrls").val().replace(src+",",""));
}

//function getPresentDate(t){
//	var date=new Date();
//	var month=date.getMonth()+1;
//	var day=date.getDate();
//	if (day<10) {
//		day="0"+day;
//	}
//	if (month<10) {
//		month="0"+month;
//	}
//	
//	if (1==t) {//日期中间带横杠"-"
//		date=date.getFullYear()+"-"+month+"-"+day;
//	}else{//日期中间不带横杠"-"
//		date=date.getFullYear()+month+day;
//	}
//	return date;
//}

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

/**
 * 获取元素
 */
function getShow(){
		var s = "";
		$(".hiden").each(function(i,t){
			if($(t).is(":visible")){
				s+=$(t).attr("id")+",";
			}else{
			}
		});
		return s;
	}


function getTimeStamp(){
	//防止表单重复提交时间戳
	var second=new Date().getSeconds();
	var min=new Date().getMinutes();
	var hours=new Date().getHours();
	var time=hours*3600+min*60+second;
	return time;
}

function getUnit(){
	//处理调研活动选择项
	//(1)处理单选
	var singleSelect="&singleChoseInfo=";//单选问题答案
	var mutilSelect="&mutilChoseInfo=";//多选问题答案
	var labelstr ="";
	var singleCount =1;
	var mutilCount =1;
	var count=0;
	var count1=0;
	
	var m1 = new Map();
	$("div.singleSelect").find("label").each(function() {
		if ("font-weight:bold;"==$(this).attr("style")) {
			if (count!=0) {
				count=0;
			}
//			m1.add
			singleSelect+="asingleIssue="+$(this).html()+",";
		}else{
			if (count==0) {
				singleSelect+="singleChoseInfo="+$(this).html()+",";
			}else {
				singleSelect+=$(this).html()+",";
			}
			count++;
		}
	});
//	singleSelect=singleSelect.substring("",singleSelect.lastIndexOf(","));
	
	//是否有其他---选项    若该问题存在其他选项，则记录其他选项出现的顺序
	singleSelect+="singleOther=";
	$("#xq").find("div.singleSelect").each(function(){
		$(this).find("input").each(function() {
			if ("other"==$(this).attr("id")){//得到单选问题有其他选项时 所排的位置
				singleSelect+=singleCount+",";
			}
		});
		singleCount++;
	});
	singleSelect=singleSelect.substring("",singleSelect.lastIndexOf(","));
//	param3+=singleSelect;
	
	
	//(2)处理多选
	$("div.mutilSelect").find("label").each(function() {
		if ("font-weight:bold;"==$(this).attr("style")) {
			if (count1!=0) {
			}
			mutilSelect+="amutilIssue="+$(this).html()+",";
		}else{
			if (count1==0) {
				mutilSelect+="mutilChoseInfo="+$(this).html()+",";
			}else {
				mutilSelect+=$(this).html()+",";
			}
			count1++;
		}
	});
	
//	mutilSelect=mutilSelect.substring("",mutilSelect.lastIndexOf(","));
	
	//是否有其他选项
	mutilSelect+="mutilother=";
	$("#xq").find("div.mutilSelect").each(function(){
		$(this).find("input").each(function(){
			if ("other"==$(this).attr("id")){
				mutilSelect+=mutilCount+",";
			}
		});
		mutilCount++;
	});
	mutilSelect=mutilSelect.substring("",mutilSelect.lastIndexOf(","));
//	param3+=mutilSelect;
}

/**
 * 校验表单方法，直接调用
 */
function checkForm(){
	var status=false;
	// 校验表单1(表单1div id为ad开头)
	var f1DivS="";
		//校验上传图片
		if (""==$("#imgUrls").val()) {
			f1DivS+="imgUrls,";
		}else {
			$("#imgUrlsMsg").hide();
		}
		//输入框
		for ( var i = 2; i < 10; i++) {
			var $t=$("#f1").find("div[id=ad"+i+"]").find("input");
//							var $_t=$t.
			if ("undefined".indexOf($t.attr("id"),0)==-1) {
				if ("false".indexOf($t.attr("data-value"),0)!=-1) {
					f1DivS+=$t.attr("id")+",";
				}
			}
		}
		
	// 校验表单3(表单1div id为fd开头)
	var f2DivS="";
		//输入框
		for ( var i = 2; i < 10; i++) {
			var $t=$("#f3").find("div[id=fd"+i+"]").find("input");
			if ("undefined".indexOf($t.attr("id"),0)==-1) {
				if ("false".indexOf($t.attr("data-value"),0)!=-1) {
					f2DivS+=$t.attr("id")+",";
				}
			}
		}
	
	var activityNote=$("#fd110").find("textarea");
	if ("false".indexOf(activityNote.attr("data-value"),0)!=-1) {
		f2DivS+=activityNote.attr("id")+",";
	}
//	if ($("#activityNote").attr("data-value")) {
//		f2DivS+="activityNote";
//	}
	
	//校验表单2退款规则
	var $_refundRules = $("#refundRules");
	if ("false".indexOf($_refundRules.attr("data-value"), 0)!=-1) {
		f2DivS+="refundRules,";
	}
	/**
	 * 处理表单验证 排除不用验证的字段
	 */
	var fDivS=dealExcludeCheckList(f1DivS+f2DivS);
	
	//检查校验结果，根据校验结果进行提示，并跳转页面到第一个提示的地方
	var divs=fDivS.split(",");
	for ( var i = 0; i < divs.length; i++) {
		var name=divs[i]+"Msg";
		var text=($("#"+divs[i]).parent().find("span").html());
		if ("undefined".indexOf(text,0)==-1) {
			if (divs[i]=="refundRules") {
				text="退款规则：";
			}
			$("#"+name).html(text.split("：")[0]+"不能为空！");
			$("#"+name).show();
		}
		if (name=="imgUrlsMsg") {
			$("#"+name).html("至少上传一张图片");
			$("#"+name).show();
		}
	}
	
	//判断表单是否可以提交     或着        将页面跳转到错误处
	if (f1DivS+f2DivS!="") {
		//跳转页面
		location.hash = divs[0]+"Msg";
	 }
	
	if ((f1DivS+f2DivS)=="") {//可以提交
		$("#checkForm").val("true");
		status=true;
	}else {
		$("#checkForm").val("false");
		console.log(f1DivS+f2DivS)
	}
	return status;
}

/**
 * 处理表单验证
 * 排除不用验证的字段
 * @param fDivS
 */
function dealExcludeCheckList(f){
	var excludeCheckList=$("#excludeCheckList").val().split(",");
	for ( var i = 0; i < excludeCheckList.length; i++) {
		if (f.indexOf(excludeCheckList[i]!=-1)) {
			f=f.replace(excludeCheckList[i]+",", "");
		}
	}
	return f;
}
/**
 * 去掉最后一个字符
 * @param t
 * @returns
 */
function trimSym(t){
	t=t.substring(0, t.length-1);
	return t;
}

/**
* 获取页面必填项Div
*/
function getEssentialDOMSelect(){
	var essentialDOM="";
	$("#xq").find("input[class='essentialDOMSelect']").each(function(){
		if ($(this).prop("checked")) {
			essentialDOM+=$(this).parent().parent().attr("id")+",";
		}
	});
	return "div1,div4,"+essentialDOM;
}

/**
 * 获取页面显示项Div
 * @returns
 */
function getShowDOMSelect(){
	//Dom元素显示项
	var s = "";
	$(".hiden").each(function(i,t){
		if($(t).is(":visible")){
			s+=$(t).attr("id")+",";
//			if ("div4"==$(t).attr("id")) {
//				s+="div100,";
//			}
		}
	});
	return s.substring(0,s.lastIndexOf(","));
}
/**
 * 处理上传图片
 * @param t
 * @returns {String}
 */
function dealImg(t){
	var result="";
	if ($("#isUploadMinPic").prop("checked")) {
		result=t;
	}else {
		var a  ="";
		var b  =/\_min/g;
		result=t.replace(b,a);
	}
	return result;
}

/**
 * 处理自定义分享图片
 * 是否做压缩处理
 * @param t
 * @returns {String}
 */
function dealShareImg(t){
	if (!notNull(t)) {
		return $("#defaultShareImg").val();
	}
	var result="";
	if ($("#isUploadMinSharePic").prop("checked")) {
		result=t;
	}else {
		var a  ="";
		var b  =/\_min/g;
		result=t.replace(b,a);
	}
	return result;
}

/**
 * 重新上传图片动作
 */
function reUpLoadShareImg(){
	$("#selfUpShareImg").val("");
	$("#selfShareImg").html("");
	uploadShareImg();
}


/**
 * 初始化分享图片上传控件
 */
function uploadShareImg(){
//	removeShareImg();//清空
	$("#ShareImgButton").show();
	
	/**
	 * 图片上传
	 */
	$('#file_upload1').uploadifive({
		'auto'             : true,//是否自动提交
		'formData'         : {
								'timestamp' : new Date().getTime(),
								'updateType': 2, //上传类型
								'isCutImg':1,	// 是否压缩图片
								'isCreateMinImg':1//是否生成小图片
							},//上传参数
		'queueID'          : 'queue',//进度条位置
		'uploadScript'     : 'up/upload',//上传地址
		'buttonText':'上传图片',//按钮显示名称
		'fileObjName':'myFile',// 上传文件名
		'fileSizeLimit':3096,// 文件大小限制
		'method':'post',//上传方式
		'uploadLimit':1,//最大上传数量
		'onSelect':function(queue){
		},
		'onUploadComplete' : function(file, data) {
				d =JSON.parse(data);
				var date=getPresentDate(0);
				if(d.statusCode==1){
	//				$("#show1").attr('src',"upload/"+new Date().toUTCString()
	//						+"/"+d.filePath);<img id="show1" alt="" src="" style="height: 30px; width: 30px;">
					path="upload/"+date+"/"+d.filePath+",";
					var img="";
				    img="<div id='"+d.filePath+"' style='height: auto; width: auto;'><img id='show' alt='' src='"+$("#basePath").val()+"upload/"+date+"/"+d.filePath+"'  style='height: 40px; width: 40px;'>";
					img+="&nbsp<button  onclick='reUpLoadShareImg()' style='height: 20px;width: 100px;margin-top: -10px;margin-left: 0px'>重新上传图片</button>";
				//	$("#imgUrls").remove();
	//				size=5-paths.split(",").length;
					img+="</div>";//<input type='hidden' value='"+paths+"' id='imgUrlssss' data-value='"+size+"'>
					$("#selfShareImg").html(img);
				}
				var val='http://'+window.location.host+"/upload/"+date+"/"+d.filePath;
				$("#selfUpShareImg").val(val);
				$("#ShareImgButton").hide();
			 }
		});
	
}
/**
 * 切换分享图片选择菜单
 */
function changeShareImg(t){
	if($("#selfShareImg").is(":visible")){
		$("#selfShare").hide();
	}else{
		if($(t).find("option:selected").html()=="上传自定义图片"){
			$("#selfShare").show();
			uploadShareImg();
		}
	}
}

/**
 * 分享图片自定义上传功能
 */
$("#shareImg").change(function(){});

/**
 * 测试方法
 */
function getConent(){
	var imgs=$("#imgUrls").val().substring("",$("#imgUrls").val().lastIndexOf(","));
	alert(dealImg(imgs));
}

/**
 * 加载活动模版详情
 */
function loadActivityModelInfo(){
	$.post("activityModel_loadActivityModelInfo",function(data) {
						if (data.statusCode==1) {
							var ue = UE.getEditor('editor');
							initActivityModelInfo(data,ue)
							if (notNull(data.lastActivityInfo)) {
								ue.ready(function(){
									initLastActivityData(data.lastActivityInfo,ue)
								});
							}
						}
						
					}, "json");
};

/**
 * 初始化活动模板数据
 */
function initActivityModelInfo(data,ue){
	//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    
		document.getElementById("modelTitle").innerHTML = data.info[0].modelTitle
		+ "模版";
		var modelInfo = data.info[0].modelInfo;
		modelInfo += "<input type='hidden' value='"
				+ data.info[0].id
				+ "' id='activityModelId'>";
		modelInfo += "<input type='hidden' value='"
				+ data.info[0].modelType
				+ "' id='activityModelType'>";
		$("#modelTitle").append(modelInfo);
		//根据活动类型判断是否显示单选多选按钮(自定义表单项)
		 if (data.info[0].modelType!=9) {
			 $("#showOnlyChose").attr("style","margin-top:50px;display:none");
			 $("#showMoreChose").attr("style","margin-top:50px;display:none");
		 }
		 
		 // 回显活动类型下拉选
		 var modelType=data.info[0].modelType;
		 var select = "";
		 if (modelType!=8) {//
			 $("#activityType").find("option").each(function(){
				 if ($(this).val()!=modelType) {//modelTitle.indexOf($(this).html())==-1
					 $(this).remove();
				 }
			 });
			 $("#activityType").parent().hide();
			 
		}
		
		//根据活动类型设置隐藏自定比表单选项
		if (modelType!=9&&modelType!=8) {//当前活动为模版活动时，隐藏自定义表单项
			$("#tj").attr("style","width:300px;border:1px solid #bbb;padding:10px;position:absolute;left:70%;top: 20px;display:none");
			
			var $_isToll = $("#isToll");
			var $_activityMoney = $("#activityMoney");
			var $_openRefundRules = $("#openRefundRules");
			//设置模版活动输入框默认值    国贸附近某高端会所。
			$("#maxPeopleNum").val("18");
			$("#maxPeopleNum").attr("data-value","true");
			$_isToll.attr("checked",true);
			$_activityMoney.removeAttr("readonly");
			$_activityMoney.val(200);
			$_openRefundRules.removeAttr("disabled");
			$_openRefundRules.attr("checked",true);
			
			var refundRulesVal="3,100,2,150,200";
			var $_refundRulesVal=refundRulesVal.split(",");
			for ( var i = 1; i < 6; i++) {
				var $t=$("#refundRules"+i);
				$t.removeAttr("readonly");
				$t.val($_refundRulesVal[i-1]);
			}
			
		}else {//模板活动
			
			$("#openRefundRules").attr("disabled","disabled");
			
			// 回显自定义Dom元素(设置div1，div4，div100为必填项)
			  //自定义和调研活动设置div1和div4（div100）为必填表单项，
			 var activityShowDOMs="div1,div2,div4".split(",");
			 for ( var i = 0; i < activityShowDOMs.length; i++) {
				 var showDomId=activityShowDOMs[i];
				 var $t=$("#"+activityShowDOMs[i]);
				 //回显Dom元素
				 $t.show();
				 $("#xq").append($t);
				 //设置Dom元素对应的按钮状态
				 $("#tj").find("input[type='button']").each(function(){
					 if(showDomId.replace("div","")==$(this).attr("data-value")){
						 this.style.background = "#aaa";
						 $(this).attr("disabled","disabled");
					 }
				 });
			 }
			 
				
		}
		//付款规则
		$("#openPayRule").attr("disabled","disabled");
		 
		//根据活动类型设置分享图片
		if (modelType==1) {//三友饭局
			$("#shareImg").find("option").each(function(){
				if ($(this).html()=="三有饭局") {
					$(this).attr("selected","selected");
				}
			});
		}else if (modelType==2){//私董会
			$("#shareImg").find("option").each(function(){
				if ($(this).html()=="私董会") {
					$(this).attr("selected","selected");
				}
			});
		}else if (modelType==-2){//私董会
			$("#shareImg").find("option").each(function(){
				if ($(this).html()=="掺和") {
					$(this).attr("selected","selected");
				}
			});
		}
		 
		 
	//	//设置退款规则单选框默认状态
	//	$("#openRefundRules").attr("disabled","disabled");
	// 设置活动维护人下拉选
	var users = "";
	for ( var i = 0; i < data.UserList.length; i++) {
		users += "<option value ='"+ data.UserList[i].id+"'>"+ data.UserList[i].nikeName + "</option>";
	}
	$("#activityCheckUserId").append(users);
	
	//设置时间默认值 活动显示时间，活动开始时间，活动结束时间
	$("#beginDate").val(getPresentDateAndTime(0,0));//<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>
	$("#endDate").val(getPresentDateAndTime(0,1));//<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>
}

/**
 * 申请表单活动不需要活动填写说明
 */
$("#activityType").change(function(){
	//申请表单活动隐藏填写说明和活动地点
	initExcludeCheckList();
});


function getPayRuleTip(){
	//付款规则
	if ($("#openPayRule").get(0).checked) {
		var payRule="";
		for ( var i = 0; i < 9; i++) {
			if (i==2||i==5||i==8) {
				if (notNull($("#payRule"+(i+1)).val())) {
					payRule+=$("#payRule"+(i+1)).val()+";";
				}
			}else{
				if (notNull($("#payRule"+(i+1)).val())) {
					if (i==1||i==4||i==7) {
						payRule+="="+$("#payRule"+(i+1)).val()+":";
					}else {
						payRule+=$("#payRule"+(i+1)).val()+":";
					}
				}
			}
		}
		var activityMoney=
		o={
			"activitySignUpPayRule.payRule":trimSym(payRule),
			"activitySignUpPayRule.signUpMoney":$("#activityMoney").val()
		};
		$.post("activity_loadSignUpTip",o,function(data){
			if (data.statusCode==1) {
				initSignUpTip(data);
			}
		},"json");
	}
}

/**
 * 根据付款规则计算付款优惠后金额
 */
function getPayRuleTip(){
	//付款规则
	if ($("#openPayRule").get(0).checked) {
		var payRule="";
		for ( var i = 0; i < 9; i++) {
			if (i==2||i==5||i==8) {
				if (notNull($("#payRule"+(i+1)).val())) {
					payRule+=$("#payRule"+(i+1)).val()+";";
				}
			}else{
				if (notNull($("#payRule"+(i+1)).val())) {
					if (i==1||i==4||i==7) {
						payRule+="="+$("#payRule"+(i+1)).val()+":";
					}else {
						payRule+=$("#payRule"+(i+1)).val()+":";
					}
				}
			}
		}
		var activityMoney=
		o={
			"activitySignUpPayRule.payRule":trimSym(payRule),
			"activitySignUpPayRule.signUpMoney":$("#activityMoney").val()
		};
		$.post("activity_loadSignUpTip",o,function(data){
			if (data.statusCode==1) {
				initSignUpTip(data);
			}
		},"json");
	}
}

/**
 * 显示付款规则提示
 * @param d
 */
function initSignUpTip(d){
	if (notNull(d.info)) {
		var tips=d.info.split(",");
		for ( var i = 0; i < tips.length-1; i++) {
			$("#payRuleTip"+((i+1))).html(tips[i]/100)
		}
	}
}


/**
 * 初始化页面数据
 * @param data
 */
function initLastActivityData(data,ue){
	$("#checkForm").val("true")
//	alert()
	var result="";	  //标题和活动内容栏
	var memberlist="";//参加活动会员列表
	var userList="";  //维护人列表
	//生成活动标题和活动内容  
//	result+="<div style='position:relative;top:-32px;float:left'>";
//	result+="<span style='margin-left:100px;font-size:18px;'>活动标题</span><input value='"+data.activity.activityTitle+"' type='text' style='font-size:16px;height:28px;margin-top:10px;margin-left:10px' id='activityTitle'>";
//	result+="</div>";
//	if (data.isExpiredActivity==1) {//活动已过期
//		$("#updateActivity").hide();
//	}
//	result+="<div style='position:relative;top:-32px'>";
//	result+="<span style='margin-left:100px;font-size:18px;display:inline-block'>活动内容</span><input type='text' style='font-size:16px;height:20px;margin-top:10px;margin-left:10px' readonly='readonly'>";
//		result+="</div>";
//	result+="<span style='font-size:18px'>活动标题</span><input type='text' value='"+1+"' style='font-size:16px;height:20px;margin-left:10px;margin-top:10px'>" +
//			"<input type='hidden' id='activityId' value='"+data.info.activityInfo.activityId+"' dblclick='showAcitvityMember()'><br>";
	//result+="<span style='margin-left:50px'>活动内容</span><input type='text' value="+data.info.activity.activitySynopsis+" style='margin-left:10px;margin-top:10px'><br>";
//	$('#activityInfo').prepend(result);
	
	//回显富文本编辑器的内容
	ue.setContent(data.activity.activitySynopsis);
	//生成活动维护人列表
//	var activityChechUserId=data.info[0].activityInfo.activityCheckUserId;
//	for ( var i = 0; i < data.UserList.length; i++) {
//		if (data.UserList[i].id==activityChechUserId) {
//			userList += "<option value ='"+ data.UserList[i].id + "' selected='selected'>"+ data.UserList[i].nikeName + "</option>";
//		}
//		else{
//			userList += "<option value ='"+ data.UserList[i].id + "'>"+data.UserList[i].nikeName + "</option>";
//		}
//	}
//	$('#activityChechUserId').append(userList);
	
	 //生成是否显示人数判断框
	 if (data.activityInfo.isShowPeopleNum==1) {
		 $('#isShowPeopleNum').attr("checked","checked");
	 }
	 
	 /**
	  *回显form1
	  */
	 //活动基本信息回显
	 //activity实体              
	 $('#activityTitle').val(data.activity.activityTitle);
	 $("#activityAddress").attr("value",data.activity.activityAddress);
	 $("#beginDate").attr("value",data.activity.beginDate);
	 $("#endDate").attr("value",data.activity.endDate);//活动结束时间
	
	 var aa="activityTitle,activityAddress,shareTitile,shareContetn,activityContactsPeople,activityContactsPeopleNo,activityNote".split(",")
	 for ( var k = 0; k < aa.length; k++) {
		 $("#"+aa[k]).attr("data-value", "true")
	 }
	 // 设置活动类型下拉选
	 var activityType=data.activity.activityType;
	 $("#activityType").find("option").each(function(){
		 if (activityType==$(this).val()) {
			 $(this).prop("selected",true);
		 }else {
			 $(this).remove();
		 }
	 });
	 
	 /**
	  * 根据活动类型判断是否隐藏部分表单
	  * 活动类型7隐藏
	  */
	 initExcludeCheckList();
	 
	 //activity实体                        隐藏信息
	 $("#f1").find("div[id=ad0]").find("input[type=hidden]").each(function(){
		 var id=$(this).attr("data-value");
		 $(this).val(data.activity[id]);
	 });
	 
	 //回显活动状态
	 var activityStatus=data.activity.activityStatus;
	 $("#activityStatus").val(data.activity.activityStatus);
	 if (activityStatus==0) {
		 $("#activityDisplayStatus").val("未开启");
	 }else {
		 $("#activityDisplayStatus").val("开启");
	 }
	 //活动图片回显
	 var imghtml="";
	 var imgs=data.activity.activityImg.split(",");
	 if (data.activity.activityImg!="") {
		 for ( var i= 0; i < imgs.length; i++) {
			 imghtml+="<div style='height: auto; width: auto; float: left;' id=''>";
			 imghtml+="<img id='show' alt='' src='http://crm.qyxml.cc/"+imgs[i]+"' style='height: 40px; width: 40px;'>";
			 imghtml+="<button style='height: 30px;width: 50px;margin-top: -50px;margin-left: 0px' onclick='removeImg(this)' value='' id=''>删除</button></div>";
		 }
	 }
	 $("#image").append(imghtml);
//	 var imgUrl=data.info[0].activity.activityImg;
	 $("#imgUrls").val(data.activity.activityImg+",");
	 //活动分享信息回显
	 $("#shareTitile").attr("value",data.activity.shareTitile);
	 $("#shareContetn").attr("value",data.activity.shareContetn);
	 
	 //回显分享图片
	 initShareImg(data.activity.shareImg)
	 
	 /**
	  * 回显自定义Dom元素 
	  * 显示项 与  必填项
	  */
	 initDomData(data.activity);
	 
	 /**
	  *回显form3
	  */
	 $("#activityContactsPeople").attr("value",data.activityInfo.activityContactsPeople);
	 $("#activityContactsPeopleNo").attr("value",data.activityInfo.activityContactsPeopleNo);
	 if (data.activityInfo.isToll==1) {
		 $('#isToll').attr("checked","checked");
		 $("#activityMoney").prop("readonly",false);
	 }
	 $("#activityMoney").attr("value",data.activityInfo.activityMoney);
	 
	 if (data.activityInfo.isNeedCheck==1) {
		 $('#isNeedCheck').attr("checked","checked");
	 }
	 if (data.activityInfo.isSendPhoneMsg==1) {
		 $('#isSendPhoneMsg').attr("checked","checked");
	 }
	 if (data.activityInfo.isSendWecatMsg==1) {
		 $('#isSendWecatMsg').attr("checked","checked");
	 }
	 $("#maxPeopleNum").attr("value",data.activityInfo.maxPeopleNum);
	 if (data.activity.isShowPeopleNum==1) {
		 $('#isShowPeopleNum').attr("checked","checked");
	 }
	 //回显活动当前人数
	 $("#currentPeopleNum").attr("value",data.activityInfo.currentPeopleNum);
	 $("#viewsNumber").attr("value",data.activityInfo.viewsNumber);
	 $("#signUpPeopleNum").attr("value",data.activityInfo.signUpPeopleNum);
	 //回显活动填写说明
	 var activityNote=data.activityInfo.activityNote;
	 $("#activityNote").val(activityNote.replace(/<br\/>/g,'\n'));
	 
	 //回显activityInfo隐藏表单元素
	 $("#f3").find("div[id=fd0]").find("input[type=hidden]").each(function(){
		 var id=$(this).attr("id");
		 $(this).val(data.activityInfo[id]);
	 });
	 
	 initPicStatus(data.activity.activityImg);//回显上传图片状态(是否是压缩图片)
//	 console.log(data.activityInfo)
	 initActivitySignUpPayRule(data.activitySignUpPayRule);//回显退款付款规则
};

/**
 * 回显分享图片
 * @param d
 */
function initShareImg(shareImg){
	if (shareImg.indexOf("www.ydclub.cc")!=-1) {//判断分享图片是否为自定义上传
		 $("#shareImg").find("option").each(function(){//www.ydclub.cc
			 if ($(this).val()==shareImg) {
				 $(this).prop("selected",true);
			 }
		 });
		 $("#selfShare").hide();//隐藏分享图片自定义上传选项
		 
	}else {//分享图片为   自定义上传类型
		 $("#selfUpShareImg").val(shareImg);
		 $("#selfUpShareImg").prop("selected",true);
		 var img="";
		     img="<div style='height: auto; width: auto;' id='shareImgDis'><img alt='' src='"+shareImg+"'  style='height: 40px; width: 40px;'>";
			 img+="&nbsp<button onclick='reUpLoadShareImg()' style='height: 20px;width: 100px;margin-top: -10px;margin-left: 0px'>重新上传图片</button>";
		 $("#selfShareImg").html(img);
		 
		 if (shareImg.indexOf("_min")!=-1) {
			$("#isUploadMinSharePic").prop("checked",true);
		 }
		 $("#ShareImgButton").hide();
	}
}

/**
 *回显表单dom元素 
 */
function initDomData(activity){
	/**
	 * 回显显示项
	 */
	var activityType=activity.activityType;
	var activityEssentialDOMs="";
	var activityShowDOMs="";
	var activityEssentialDOM=activity.activityEssentialDOM.substring(0,activity.activityEssentialDOM.length-1)
	if (activityType==2) {//私董会
		 activityEssentialDOMs=activityEssentialDOM.split(";")[0].split(",");
		 activityShowDOMs=activity.activityShowDOM.split(";")[0].split(",");
	 }else {
		 activityEssentialDOMs=activityEssentialDOM.split(",");
		 activityShowDOMs=activity.activityShowDOM.split(",");
	 }
	 for ( var i = 0; i < activityShowDOMs.length; i++) {
//		 console.log(activityShowDOMs[i])
		 var showDomId=activityShowDOMs[i];
		 var $t=$("#"+activityShowDOMs[i]);
		 
		 //回显Dom元素  $t.find("input[id='essentialDOMSelect']").prop('outerHTML','<span style="color:#f00">必填项</span>')
		 $t.show();
		 $("#xq").append($t);
		 //设置Dom元素对应的按钮状态  <span style="color:#f00">*</span>
		 $("#tj").find("input[type='button']").each(function(){
			 if(showDomId.replace("div","")==$(this).attr("data-value")){
				 this.style.background = "#aaa";
			 }
		 });
	 }
	 
	 /**
	  * 回显必填项
	  */
	 for ( var j = 0; j < activityEssentialDOMs.length; j++) {
		$("#"+activityEssentialDOMs[j]).find("input[class='essentialDOMSelect']").prop("checked",true);
	 };
};

function initActivitySignUpPayRule(d){
	if (notNull(d)) {
		//回显退款规则隐藏属性
		$("#f1").find("input[name='activitySignUpPayRule.payMark']").val(d.payMark);
		$("#f1").find("input[name='activitySignUpPayRule.id']").val(d.id);
		//回显付款规则   21:-5:*0.8;   20:-4:*0.85;   12:-3:*0.86
		if (notNull(d.payRule)) {
			$("#openPayRule").prop("checked",true);
			var payRuleArray= d.payRule.split(";");
			 var arr = new Array();
			 for (var i = 0; i < payRuleArray.length; i++) {//10:1,1:10,100
				 arr[0+i*3]=payRuleArray[i].split(":")[0];
				 arr[1+i*3]=payRuleArray[i].split(":")[1].substring(1,payRuleArray[i].split(":")[1].length+1);
				 arr[2+i*3]=payRuleArray[i].split(":")[2];
			 }
			 for ( var i = 1; i <=arr.length; i++) {
				 $("#payRule"+i).val(arr[i-1]);
				 $("#payRule"+i).attr("readonly",false);
			 }
		}
		//回显退款规则   321:3,32:4,33
		if (notNull(d.refundRule)) {//退款规则不为空
			 $('#openRefundRules').prop("checked","checked");
			 //回显退款规则部分
			 var refundArray= d.refundRule.split(",");
			 var arr1 = new Array();
			 for (var i = 0; i < refundArray.length; i++) {//10:1,1:10,100
				 if (refundArray[i].indexOf(":")!=-1) {//该分组中有冒号  10:1
					 arr1[0+i*2]=refundArray[i].split(":")[0];
					 arr1[1+i*2]=refundArray[i].split(":")[1];
				 }else {								    //该分组中没有冒号
					 arr1[4]=refundArray[i];
				 }
			 }
			 for ( var i = 1; i <=5; i++) {
				 $("#refundRules"+i).val(arr1[i-1]);
			 }
		 }else{//退款规则为空
			 $('#refundRules').find("input[type=text]").each(function() {
				 $(this).attr("readonly",true);
			 });
		 }
	}
};

/**
 * 回显上传图片状态（是否是压缩图片）
 * @param d
 */
function initPicStatus(d){
	if (d.indexOf("_min",0)!=-1) {
		$("#isUploadMinPic").prop("checked",true);
	}
}
//
//window.onbeforeunload=onclose;
//
//function onclose(){
//	event.returnValue="确认离开么";
//}

function addCustomBtn(){
	var costomerCount=$("#costomerCount").val();
	if (costomerCount<10) {//最多添加十个拓展问题
		info="<div class='item'>" +
			 "<input type='text' class='itemMmeber' placeholder='请输入问题名称(最大20个字符)' maxlength='20'>" +
			 "<input type='button' value='删除' onclick='deleteCustom(this)' >" +
			 "</div>";
		$("#customListItem").append(info);
		$("#costomerCount").val($("#customList .item").size())
	}
}

function deleteCustom(t){
	var $t=$(t);
	$t.parent().remove();
}
 
