function sureSearchShare(event){
	var code = event.keyCode;
	if (code==13) {  //回车键
		//清空“搜索结果框”之前的内容
		var $ul = $("#pc_part_6").find(".contacts-list");
		$ul.empty();
		
		//获取搜索关键字 input的value
		var keyword = $("#search_note").val().trim();
		if (keyword=="") {
			alert("不能为空");
		}else{
			loadPageShare(keyword,1);
		}
	}
}



//加载下一页
function moreSearchShare(){
	//获取参数
	//页数
	page=page+1;
	//关键字
	var keyword = $("#search_note").val().trim();
	//发送ajax请求加载列表
	loadPageShare(keyword,page);
}



//执行“搜索”业务
function loadPageShare(keyword,page){
	//发送ajax请求
	$.ajax({
		url:path+"/share/search.do",
		type:"post",
		data:{"keyword":keyword,"page":page},
		dataType:"json",
		success:function(result){
			
			if (result.status==0) {
				//服务器返回的数据
				var shares = result.data;
				
				//布局容器替换
				$("#pc_part_2").hide();
				$("#pc_part_6").show();
				
				for (var i = 0; i < shares.length; i++) {
					var shareNote = shares[i];
					var str = '';
					
					str+='<li class="online">';
					str+='<a>';
					str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					str+=shareNote.cn_share_title;
					str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					str+='</a>';
					str+='</li>';
					
					var $li = $(str);
					
					$li.data("shareId",shareNote.cn_share_id);
					
					var $ul = $("#pc_part_6").find(".contacts-list");
					$ul.append($li);
				}
			}else if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("查询失败");
		}
	});
}