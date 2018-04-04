/**
 * 
 */
package test;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wentao.cloud_note.dao.UserDao;
import com.wentao.cloud_note.entity.User;
import com.wentao.cloud_note.service.UserService;

/**
 * 
 * @author wentao
 */
public class test {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void initCtx() {
		ctx=new ClassPathXmlApplicationContext("conf/spring-mvc.xml","conf/spring-mybatis.xml","conf/spring-service.xml");
	}
	@After
	public void closeCtx() {
		ctx.close();
	}
	
	@Test
	public void test1() {
		String name="demo";
		System.out.println(name);
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user=dao.findUserByName(name);
		System.out.println(user);
		
	}
	
	@Test
	public void test2() {
		String name="demo123";
		String password="123";
		String confirm="1234";
		
		UserService service=ctx.getBean("userService",UserService.class);
		User user=service.regist(name, password,"", confirm);
		System.out.println(user);
	}
	
	@Test
	public void testMd5() {
		String str="123";
		String md5=DigestUtils.md5Hex(str);
		System.out.println(md5);
		
	}
	
	@Test
	public void testAdd() {
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user=new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("test");
		user.setNick("test");
		user.setPassword(DigestUtils.md5Hex("12345"));
		user.setToken("test");
		int i=dao.addUser(user);
		System.out.println(i);
	}

}
