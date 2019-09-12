package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestUserService {
	UserService service;
	
	@Before
	public void init(){
		String[] conf = {"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		service = ac.getBean("userService", UserService.class);
	}
	
	@Test
	public void test1(){
		NoteResult<User> result = service.checkLogin("abc", "123");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	
	
	@Test    //预期结果：注册成功
	public void test2(){
		String name ="cang";
		String password="123456";
		String nick = "狗蛋儿";
		NoteResult<Object> result = service.addUser(name, password, nick);
		
		System.out.println(result.getMsg());
				
	}
	
}
