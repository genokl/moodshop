$(function(){
	prepareQueryCondition();
})


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
	conso.le.log(o)
	return o;
}