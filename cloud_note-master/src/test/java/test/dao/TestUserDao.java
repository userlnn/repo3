package test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;

public class TestUserDao {
	private UserDao dao;
	
	@Before
	public void init(){
		String[] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		dao = ac.getBean("userDao",UserDao.class);
	}
	
	@Test
	public void test1(){
		User user = dao.findByName("demo");
		System.out.println(user);
	}
	
	
	@Test
	public void testSave(){
		User user = new User("123456789", "mixinan", "123456", null, "nan");
		dao.save(user);
		System.out.println(user);
	}
}
