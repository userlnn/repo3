package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestCase;

public class TestBookService extends TestCase{
	private BookService bookService;
	
	@Before
	public void init(){
		bookService = super.getContext().getBean("bookService", BookService.class);
	}
	
	@Test
	public void test(){
		NoteResult<List<Book>> result =  
				bookService.loadUserBooks("48595f52-b22c-4485-9244-f4004255b972");
		
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		
		for (Book book : result.getData()) {
			System.out.println(book.getCn_notebook_name());
		}
		
	}
	
	
}
