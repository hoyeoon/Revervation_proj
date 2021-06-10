package kr.or.connect.reservation.dao;

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
@ContextConfiguration(classes = { MvcConfig.class, ApplicationConfig.class })
public class FileInfoDaoTest {
	private FileDao fileInfoDao;
	
	@Before
	public void setUp(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		fileInfoDao = ac.getBean(FileDao.class);
	}
	
	@Test
	public void unitTest() throws Exception {
		Assert.assertEquals("최호연.jpg", fileInfoDao.selectFileName(194));
		Assert.assertEquals("c:/tmp/최호연.jpg", fileInfoDao.selectSaveFileName(194));
		Assert.assertEquals("image/jpg", fileInfoDao.selectContentType(194));
	}
}
