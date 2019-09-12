package test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteUtil;

public class TestBookDao {
	String[] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	BookDao dao = ac.getBean("bookDao", BookDao.class);
	
	@Test
	public void testDao(){
		
		List<Book> books = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		for (Book book:books) {
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	
	@Test
	public void save(){
		Book book = new Book();
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		String bookName = "testWeb";
		String bookId = NoteUtil.createId();
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		book.setCn_notebook_id(bookId);
		dao.save(book);
	}
}
