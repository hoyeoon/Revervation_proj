package kr.or.connect.reservation.dao;

public class ReservationDaoSqls {
	public static final String SELECT_RESERVATION_COUNT = "SELECT count(*) FROM reservation_info WHERE user_id = :userId";
	public static final String SELECT_RESERVATIONS = "SELECT r.id, r.product_id, r.display_info_id, r.cancel_flag, "
			+ "p.description AS product_description, p.content AS product_content, r.user_id, "
			+ "SUM(pp.price * rp.count) AS sum_price, r.reservation_date, r.create_date, r.modify_date "
			+ "FROM product p, reservation_info r, reservation_info_price rp, product_price pp "
			+ "WHERE r.user_id = :userId AND p.id = r.product_id AND r.id = rp.reservation_info_id AND pp.id = rp.product_price_id "
			+ "GROUP BY id ORDER BY id DESC";
	public static final String UPDATE_RESERVATION = "UPDATE reservation_info SET cancel_flag = 1 "
			+ "WHERE id = :reservationId AND user_id = :userId";
	public static final String SELECT_PRODUCT_ID_BY_ID = "SELECT product_id FROM reservation_info "
			+ "WHERE id = :id";
}