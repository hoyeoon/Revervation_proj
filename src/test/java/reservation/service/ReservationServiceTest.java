package kr.or.connect.reservation.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

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
import kr.or.connect.reservation.dto.Reservation;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, ApplicationConfig.class })
public class ReservationServiceTest {
	private ReservationService reservationService;
	
	@Before
	public void setUp(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		reservationService = ac.getBean(ReservationService.class);
	}
	@Test
	public void unitTest() throws Exception{
		Reservation.Info reservation = new Reservation.Info();
		reservation.setProductId(1);
		reservation.setDisplayInfoId(1);
				
		reservation.setReservationDate(LocalDateTime.now());
		reservation.setUserId(1);
		
		Reservation.InfoPriceDB reservationPrice = new Reservation.InfoPriceDB();
		reservationPrice.setCount(2);
		reservationPrice.setProductPriceId(3);
		
		assertTrue(reservationService.getReservations(1) != null);
		assertTrue(reservationService.getReservationCount(1) != 0);
//		assertTrue(reservationService.addReservation(reservation, reservationPrice) != null);
//		assertTrue(reservationService.cancelReservation(18, 1) == 1);
		assertTrue(reservationService.getProductId(18) == 1);
	}
}