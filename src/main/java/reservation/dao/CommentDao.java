package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.CommentDaoSqls.SELECT_COMMENTS_BY_PRODUCT_ID;
import static kr.or.connect.reservation.dao.CommentDaoSqls.SELECT_COMMENT_IMAGES;
import static kr.or.connect.reservation.dao.CommentDaoSqls.SELECT_TOTAL_COUNT_BY_PRODUCT_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;

@Repository
public class CommentDao {
	private SimpleJdbcInsert insertAction;
	private SimpleJdbcInsert insertAction2;
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment.Info> rowMapper = BeanPropertyRowMapper.newInstance(Comment.Info.class);
	private RowMapper<Comment.Image> rowMapper2 = BeanPropertyRowMapper.newInstance(Comment.Image.class);
	
	public CommentDao(DataSource dataSource) {
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment").usingGeneratedKeyColumns("id");
		this.insertAction2 = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment_image").usingGeneratedKeyColumns("id");
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int selectCountByProductId(int productId) {
		Map<String, Integer> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(SELECT_TOTAL_COUNT_BY_PRODUCT_ID, params, Integer.class);
	}

	public List<Comment.Info> selectCommentsByProductId(int productId, int start, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_COMMENTS_BY_PRODUCT_ID, params, rowMapper);
	}

	public List<Comment.Image> selectCommentImages(int reservationUserCommentId) {
		Map<String, Integer> params = Collections.singletonMap("reservationUserCommentId", reservationUserCommentId);
		return jdbc.query(SELECT_COMMENT_IMAGES, params, rowMapper2);
	}
	
	public int insertComment(Comment.DB reservationUserComment) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserComment);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public int insertCommentImage(Comment.ImageDB reservationUserCommentImage) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserCommentImage);
		return insertAction2.executeAndReturnKey(params).intValue();
	}
}