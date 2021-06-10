package kr.or.connect.reservation.dao;

public class PromotionDaoSqls {
	public static final String SELECT_PROMOTION_COUNT = "SELECT count(*) FROM promotion";
	public static final String SELECT_PROMOTIONS = "SELECT prom.id, prod.id product_id, c.id category_id, name category_name, description, file_id "
			+ "FROM category c, product prod, promotion prom, product_image proimg "
			+ "WHERE c.id = category_id AND prod.id = prom.product_id AND prod.id = proimg.product_id AND type=:type";
}