package kr.or.connect.reservation.service;

import org.junit.Assert;
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
public class FileInfoServiceTest {
	private FileInfoService fileInfoService;
	
	@Before
	public void setUp(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		fileInfoService = ac.getBean(FileInfoService.class);
	}
	@Test
	public void unitTest() throws Exception{
		Assert.assertEquals("최호연.jpg", fileInfoService.getFileName(194));
		Assert.assertEquals("c:/tmp/최호연.jpg", fileInfoService.getSaveFileName(194));
		Assert.assertEquals("image/jpg", fileInfoService.getContentType(194));
	}
}