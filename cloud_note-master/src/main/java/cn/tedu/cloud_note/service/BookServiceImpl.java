package cn.tedu.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Resource
	private BookDao bookDao;
	
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		List<Book> list = bookDao.findByUserId(userId);
		//构建返回结果result
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		result.setStatus(0);
		result.setMsg("查询笔记本成功");
		result.setData(list);
		
		return result;
	}


	public NoteResult<Book> createBook(String userId, String bookName) {
		Book book = new Book();
		book.setCn_user_id(userId);
		book.setCn_notebook_id(NoteUtil.createId());
		book.setCn_notebook_name(bookName);
		
		int rows = bookDao.save(book);
		
		NoteResult<Book> result = new NoteResult<Book>();
		
		if (rows==1) {
			result.setStatus(0);
			result.setMsg("创建book成功");
			result.setData(book);
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("创建book失败");
			return result;
		}
		
	}

}
