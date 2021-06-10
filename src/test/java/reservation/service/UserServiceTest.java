package kr.or.connect.reservation.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.connect.reservation.ApplicationConfig;
import kr.or.connect.reservation.MvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, ApplicationConfig.class })
public class UserServiceTest {
	private UserService userService;
	
	@Before
	public void setUp(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		userService = ac.getBean(UserServiceImpl.class);
	}
	@Test
	public void unitTest() throws Exception {
		assertTrue(userService.getUserId("carami@connect.co.kr") == 1);
	}
}