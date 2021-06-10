package kr.or.connect.reservation.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Reservation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	private final ReservationDao reservationInfoDao;
	
	public int getReservationCount(int userId) {
		return reservationInfoDao.selectReservationCount(userId);
	}
	
	public List<Reservation.Info> getReservations(int userId) {
		return reservationInfoDao.selectReservations(userId);
	}
	
	@Transactional(readOnly = false)
	public Reservation.RegistrationResponse registerReservation(Reservation.RegistrationRequest reservationRegistrationRequest) throws ParseException{
		Reservation.InfoDB reservationInfoDB = addReservationDB(reservationRegistrationRequest);
		int reservationInfoId = reservationInfoDao.insertReservation(reservationInfoDB);
		
		List<Reservation.InfoPriceDB> prices = new ArrayList<>();
		
		for(Product.Price productPriceRequest : reservationRegistrationRequest.getPrices()){
			Reservation.InfoPriceDB reservationInfoPrice = addReservationPriceDB(reservationInfoId, productPriceRequest);
			int reservationInfoPriceId = reservationInfoDao.insertReservationPrice(reservationInfoPrice);
			
			prices.add(makePricesResponse(reservationInfoPriceId, reservationInfoPrice));
		}
		Reservation.RegistrationResponse reservationRegistrationResponse = 
				makeReservationRegistrationResponse(reservationInfoId, reservationInfoDB, prices);
	
		return reservationRegistrationResponse;
	}
	
	@Transactional(readOnly = false)
	public int cancelReservation(int reservationId, int userId){
		return reservationInfoDao.updateReservation(reservationId, userId);
	}
	
	public int getProductId(int reservationInfoId){
		return reservationInfoDao.selectProductId(reservationInfoId);
	}
	
	private Reservation.InfoDB addReservationDB(Reservation.RegistrationRequest reservationRegistrationRequest) throws ParseException{
		return new Reservation.InfoDB(
				reservationRegistrationRequest.getProductId(),
				reservationRegistrationRequest.getDisplayInfoId(),
				reservationRegistrationRequest.getUserId(),
				convertType(reservationRegistrationRequest.getReservationYearMonthDay()),
				0, LocalDateTime.now(), LocalDateTime.now());
	}
	
	private Reservation.InfoPriceDB addReservationPriceDB(int reservationId, Product.Price productPriceRequest){
		return new Reservation.InfoPriceDB(
				reservationId, 
				productPriceRequest.getProductPriceId(),
				productPriceRequest.getCount());
	}
	
	private Reservation.InfoPriceDB makePricesResponse(int reservationPriceId, Reservation.InfoPriceDB reservationPrice){
		return new Reservation.InfoPriceDB(
				reservationPriceId, 
				reservationPrice.getReservationInfoId(), 
				reservationPrice.getProductPriceId(), 
				reservationPrice.getCount());
	}
	
	private Reservation.RegistrationResponse makeReservationRegistrationResponse(int reservationId, Reservation.InfoDB reservationInfoDB, List<Reservation.InfoPriceDB> prices){
		return new Reservation.RegistrationResponse(
				reservationId,
				reservationInfoDB.getProductId(),
				reservationInfoDB.getCancelFlag(),
				reservationInfoDB.getDisplayInfoId(),
				reservationInfoDB.getUserId(),
				reservationInfoDB.getReservationDate(),
				reservationInfoDB.getCreateDate(),
				reservationInfoDB.getModifyDate(),
				prices);
	}
	
	private LocalDateTime convertType(LocalDate localDate){
		return localDate.atStartOfDay();
	}
}