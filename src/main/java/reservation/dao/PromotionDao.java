package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.PromotionDaoSqls.SELECT_PROMOTIONS;
import static kr.or.connect.reservation.dao.PromotionDaoSqls.SELECT_PROMOTION_COUNT;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion.Info> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.Info.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int selectPromotionCount() {
		return jdbc.queryForObject(SELECT_PROMOTION_COUNT, Collections.emptyMap(), Integer.class);
	}

	public List<Promotion.Info> selectPromotions(String type) {
		Map<String, String> params = Collections.singletonMap("type", type);
		return jdbc.query(SELECT_PROMOTIONS, params, rowMapper);
	}
}
 