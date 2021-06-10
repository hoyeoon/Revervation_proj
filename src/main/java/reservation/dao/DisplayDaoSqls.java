package kr.or.connect.reservation.dao;

public class DisplayDaoSqls {
	public static final String SELECT_PRODUCT = "SELECT p.id, category_id, display_info_id, name, description, content, event, opening_hours, place_name, place_lot, place_street, tel, homepage, email, p.create_date, p.modify_date, pi.file_id "
			+ "FROM category c, product p, display_info di, display_info_image dii, product_image pi "
			+ "WHERE di.id=:displayInfoId AND c.id = category_id AND p.id = di.product_id AND di.id = display_info_id AND p.id = pi.product_id AND type = :type";
	public static final String SELECT_PRODUCT_IMAGES = "SELECT p.id product_id, pi.id product_image_id, type, fi.id file_info_id, file_name, save_file_name, content_type, delete_flag, fi.create_date, fi.modify_date "
			+ "FROM product p, file_info fi, product_image pi, display_info di "
			+ "WHERE type=:type AND p.id = pi.product_id AND fi.id = pi.file_id AND p.id = di.product_id AND di.id=:displayInfoId";
	public static final String SELECT_DISPLAY_INFO_IMAGES = "SELECT dii.id, display_info_id, file_id, file_name, save_file_name, content_type, delete_flag, di.create_date, di.modify_date "
			+ "FROM file_info fi, display_info di, display_info_image dii "
			+ "WHERE fi.id = file_id AND di.id = display_info_id AND di.id=:displayInfoId";
	public static final String SELECT_AVG_SCORE = "SELECT IFNULL(AVG(score), 0) "
			+ "FROM product p, display_info di, reservation_user_comment ruc "
			+ "WHERE p.id = di.product_id AND p.id = ruc.product_id AND di.id=:displayInfoId";
	public static final String SELECT_PRODUCT_PRICES = "SELECT pp.id, pp.product_id, price_type_name, price, discount_rate, pp.create_date, pp.modify_date "
			+ "FROM product p, display_info di, product_price pp "
			+ "WHERE p.id = di.product_id AND p.id = pp.product_id AND di.id=:displayInfoId ORDER BY pp.id DESC";
	public static final String SELECT_EXISTS_DISPLAY_INFO = "SELECT EXISTS (SELECT * FROM display_info WHERE id = :displayInfoId) AS success";
}