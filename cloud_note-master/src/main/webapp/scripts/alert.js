function shareNote(){
	//获取参数noteId
	var $li = $(this).parents("li");
	var noteId = $li.data("noteId");
	//发送ajax请求
	$.ajax({
		url:path+"/share/add.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			var noteTitle = $li.text();
			var str = '';
			str += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
			str += noteTitle;
			str += ' <i class="fa fa-sitemap"></i>';
			str += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
			
			//将笔记li元素的a标记内容替换
			$li.find('a').html(str);
			
			alert(result.msg);
		},
		error:function(){
			alert("分享失败");
		}
	});
}





//新建笔记
function addNote(){
	console.log(this);
	//获取参数    笔记名称，笔记本id
	//笔记名称
	var title = $("#input_note").val().trim();
	//userId
	var userId = getCookie("userId");
	//笔记本id
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	//数据格式检查
	var ok = true;
	
	if (title=="") {//是否为空
		ok=false;
		$("#title_span").html("笔记标题不能为空");
	}
	
	if (userId==null) {//是否失效
		ok=false;
		window.location.href="log_in.html";
	}
	
	if (ok) {
		$.ajax({
			url:path+"/note/add.do",
			type:"post",
			data:{"userId":userId,"title":title,"bookId":bookId},
			dataType:"json",
			success:function(result){
				//alert(result.msg);
				if (result.status==0) {
					//关闭对话框
					closeAlertWindow();
					var note = result.data;
					//获取noteTitle
					var noteTitle = note.cn_note_title;
					//获取noteId
					var noteId = note.cn_note_id;
					
					createNoteLi(noteId,noteTitle);
				}
			},
			error:function(){
				alert("笔记创建失败");
			}
		});
	}
	
}





//弹出“新建笔记”对话框
function alertAddNoteWindow() {
	// 判断是否有笔记本被选中
	var $li = $("#book_ul a.checked").parent();
	if ($li.length == 0) {
		alert("请选择笔记本");
	} else {
		// 加载“新建笔记”页面
		$("#can").load("alert/alert_note.html");
		// 显示背景
		$(".opacity_bg").show();
	}

}





// 弹出“新建笔记本”对话框
function alertAddBookWindow() {
	// 加载“新建笔记本”页面
	$("#can").load("alert/alert_notebook.html");
	// 显示背景
	$(".opacity_bg").show();
}

// 隐藏“新建笔记本”对话框
function closeAlertWindow() {
	$("#can").html("");
	// 隐藏背景
	$(".opacity_bg").hide();
}





// 新建笔记本
function addBook() {
	console.log(this);
	// 获取参数
	// 输入内容：笔记本名称 #input_notebook
	var name = $("#input_notebook").val().trim();
	// 通过cookie获取userId
	var userId = getCookie("userId");
	alert("name:" + name + " ,userId:" + userId);

	if (name != "") {

		$.ajax({
			url : path + "/book/add.do",
			type : "post",
			data : {
				"userId" : userId,
				"bookName" : name
			},
			dataType : "json",
			success : function(result) {
				// 提示创建结果
				alert(result.msg);

				if (result.status == 0) {
					var book = result.data;
					var bookId = book.cn_notebook_id;
					var bookName = book.cn_notebook_name;
					
					// 更新笔记本列表
					createBookLi(bookId, bookName);
				}
			},
			error : function() {
				alert("新建笔记本失败");
			}
		});
	}else{
		alert("笔记本名字不能为空");
	}
}