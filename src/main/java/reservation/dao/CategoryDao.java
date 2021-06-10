package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.CategoryDaoSqls.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Category;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category.Info> rowMapper = BeanPropertyRowMapper.newInstance(Category.Info.class);

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category.Info> selectCategories() {
		return jdbc.query(SELECT_CATEGORIES, Collections.emptyMap(), rowMapper);
	}
	
	public int selectDisplayInfoCountByCategoryId(int categoryId) {
		Map<String, Integer> params = Collections.singletonMap("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_COUNT_BY_CATEGORY_ID, params, Integer.class);
	}
	
	public int selectCategoryCount() {
		return jdbc.queryForObject(SELECT_CATEGORY_COUNT, Collections.emptyMap(), Integer.class);
	}
}