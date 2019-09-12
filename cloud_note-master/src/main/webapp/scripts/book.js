

function loadUserBooks() {
	// 获取userId
	var userId = getCookie("userId");
	// alert("userId:"+userId);
	// 判断是否获取到有效的userId
	if (userId == null) {
		window.location.href = "log_in.html";
	} else { // 发送ajax请求
		$.ajax({
			url : path + "/book/loadbooks.do",
			type : "post",
			data : {
				"userId" : userId
			},
			dataType : "json",
			success : function(result) {
				// 判断查询是否成功
				if (result.status == 0) {
					// 获取笔记本集合
					var books = result.data;
					for (var i = 0; i < books.length; i++) {
						// 获取笔记本id
						var bookId = books[i].cn_notebook_id;
						// 获取笔记本名称
						var bookName = books[i].cn_notebook_name;
						// 创建笔记本li元素
						createBookLi(bookId, bookName);
					}
				}
			},
			error : function() {
				alert("笔记本加载失败");
			}
		});
	}

};

/* 创建一个笔记本li元素 */
function createBookLi(bookId, bookName) {
	var str = "";
	str += '<li class="online">';
	str += '<a>';
	str += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	str += '</i>';
	str += bookName;
	str += '</a>';
	str += '</li>';

	// 将str字符串转换成jQuery对象li元素
	var $li = $(str);
	// 将bookId绑定在jQuery对象上
	$li.data("bookId", bookId);
	// 将li元素添加到ul列表里
	$("#book_ul").prepend($li);
}
