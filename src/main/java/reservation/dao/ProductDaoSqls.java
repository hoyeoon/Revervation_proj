package kr.or.connect.reservation.dao;

public class ProductDaoSqls {
	public static final String SELECT_TOTAL_COUNT_BY_CATEGORY_ID = "SELECT count(*) FROM product, display_info "
			+ "WHERE category_id = :categoryId AND product.id = display_info.product_id";
	public static final String SELECT_TOTAL_COUNT = "SELECT count(*) FROM product, display_info "
			+ "WHERE product.id = display_info.product_id";
	public static final String SELECT_PRODUCTS_BY_CATEGORY_ID = "SELECT p.id, category_id, display_info_id, name, description, content, event, opening_hours, place_name, place_lot, place_street, tel, homepage, email, p.create_date, p.modify_date, pi.file_id "
			+ "FROM category c, product p, display_info di, display_info_image dii, product_image pi "
			+ "WHERE category_id = :categoryId AND c.id = category_id AND p.id = di.product_id AND di.id = display_info_id AND p.id = pi.product_id AND type = :type LIMIT :start, :limit";
	public static final String SELECT_ALL_PRODUCTS = "SELECT p.id, category_id, display_info_id, name, description, content, event, opening_hours, place_name, place_lot, place_street, tel, homepage, email, p.create_date, p.modify_date, pi.file_id "
			+ "FROM category c, product p, display_info di, display_info_image dii, product_image pi "
			+ "WHERE c.id = category_id AND p.id = di.product_id AND di.id = display_info_id AND p.id = pi.product_id AND type = :type LIMIT :start, :limit";
	public static final String SELECT_EXISTS_PRODUCT = "SELECT EXISTS (SELECT * FROM product WHERE id = :productId) AS success";
	public static final String SELECT_EXISTS_PRODUCT_PRICE = "SELECT EXISTS (SELECT * FROM product_price WHERE id = :productPriceId) AS success";
}
