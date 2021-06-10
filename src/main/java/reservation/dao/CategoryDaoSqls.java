package kr.or.connect.reservation.dao;

public class CategoryDaoSqls {
	public static final String SELECT_CATEGORIES = "SELECT id, name FROM category";
	public static final String SELECT_DISPLAY_INFO_COUNT_BY_CATEGORY_ID = "SELECT count(*) FROM display_info d JOIN product p ON p.id = product_id WHERE category_id = :categoryId";
	public static final String SELECT_CATEGORY_COUNT = "SELECT count(*) FROM category";
}