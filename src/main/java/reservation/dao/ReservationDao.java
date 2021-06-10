package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_PRODUCT_ID_BY_ID;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_RESERVATIONS;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_RESERVATION_COUNT;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.UPDATE_RESERVATION;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Reservation;

@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private SimpleJdbcInsert insertAction2;
	private RowMapper<Reservation.Info> rowMapper = BeanPropertyRowMapper.newInstance(Reservation.Info.class);
	
	public ReservationDao(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info").usingGeneratedKeyColumns("id");
		this.insertAction2 = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price").usingGeneratedKeyColumns("id");
	}
	
	public int selectReservationCount(int userId){
		Map<String, Integer> params = Collections.singletonMap("userId", userId);
		return jdbc.queryForObject(SELECT_RESERVATION_COUNT, params, Integer.class);
	}
	
	public List<Reservation.Info> selectReservations(int userId){
		Map<String, Integer> params = Collections.singletonMap("userId", userId);
		return jdbc.query(SELECT_RESERVATIONS, params, rowMapper);
	}
	
	public int insertReservation(Reservation.InfoDB reservationDB){
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationDB);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public int updateReservation(int reservationId, int userId){
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationId", reservationId);
		params.put("userId", userId);
		return jdbc.update(UPDATE_RESERVATION, params);
	}
	
	public int selectProductId(int reservationInfoId){
		Map<String, Integer> params = Collections.singletonMap("id", reservationInfoId);
		
		try{
			return jdbc.queryForObject(SELECT_PRODUCT_ID_BY_ID, params, Integer.class);
		} catch(EmptyResultDataAccessException e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public int insertReservationPrice(Reservation.InfoPriceDB reservationPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationPrice);
		return insertAction2.executeAndReturnKey(params).intValue();
	}
}