package test;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jm.basic.bean.User;
import jm.basic.mapper.UserMapper;
import jm.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试  classpath*:/spring/servlet.xml","classpath*:/spring/common.xml
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext-dao.xml"})
public class TestContro {

	@Resource(name="userService")
	private UserService userService;
	
	@Resource
	private UserMapper userMapper;
	
	@Test
	public void testAdd() {
		System.out.println(2321);
		try {
			User user = userMapper.findById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(user.getUserName());
	}
}
