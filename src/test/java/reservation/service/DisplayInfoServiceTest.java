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
public class DisplayInfoServiceTest {
	private DisplayInfoService displayInfoService;
		
	@Before
	public void setUp(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		displayInfoService = ac.getBean(DisplayInfoService.class);
	}
	@Test
    public void unitTest() throws Exception {
    	assertTrue(displayInfoService.getProduct(1) != null);
    	assertTrue(displayInfoService.getProductImages(1) != null);
    	assertTrue(displayInfoService.getDisplayInfoImages(1) != null);
    	assertTrue(displayInfoService.getAvgScore(1) != 0);
    	assertTrue(displayInfoService.getProductPrices(1) != null);    	
    }
}
