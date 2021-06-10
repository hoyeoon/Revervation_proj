package kr.or.connect.reservation.dao;

public class CommentDaoSqls {
	public static final String SELECT_TOTAL_COUNT_BY_PRODUCT_ID = "SELECT count(*) "
			+ "FROM reservation_user_comment WHERE product_id=:productId";
	public static final String SELECT_COMMENTS_BY_PRODUCT_ID = "SELECT ruc.id, ruc.product_id, ruc.reservation_info_id, ruc.score, ruc.user_id, u.email AS reservation_email, ruc.comment "
			+ "FROM user u, reservation_user_comment ruc " + "WHERE u.id = user_id AND product_id=:productId "
			+ "ORDER BY ruc.id DESC LIMIT :start, :limit";
	public static final String SELECT_COMMENT_IMAGES = "SELECT r.id, r.reservation_info_id, r.reservation_user_comment_id, r.file_id, f.file_name, f.save_file_name, f.content_type, f.delete_flag, f.create_date, f.modify_date "
			+ "FROM reservation_user_comment_image r, file_info f "
			+ "WHERE f.id = r.file_id AND reservation_user_comment_id=:reservationUserCommentId";
}