package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DisplayDaoSqls.SELECT_AVG_SCORE;
import static kr.or.connect.reservation.dao.DisplayDaoSqls.SELECT_DISPLAY_INFO_IMAGES;
import static kr.or.connect.reservation.dao.DisplayDaoSqls.SELECT_EXISTS_DISPLAY_INFO;
import static kr.or.connect.reservation.dao.DisplayDaoSqls.SELECT_PRODUCT;
import static kr.or.connect.reservation.dao.DisplayDaoSqls.SELECT_PRODUCT_IMAGES;
import static kr.or.connect.reservation.dao.DisplayDaoSqls.SELECT_PRODUCT_PRICES;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.Product;

@Repository
public class DisplayDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product.Info> rowMapper = BeanPropertyRowMapper.newInstance(Product.Info.class);
	private RowMapper<Product.Image> rowMapper2 = BeanPropertyRowMapper.newInstance(Product.Image.class);
	private RowMapper<Display.InfoImage> rowMapper3 = BeanPropertyRowMapper.newInstance(Display.InfoImage.class);
	private RowMapper<Product.PriceDB> rowMapper4 = BeanPropertyRowMapper.newInstance(Product.PriceDB.class);
	
	public DisplayDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Product.Info> selectProduct(int displayInfoId, String type) {
		Map<String, Object> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("type", type);
		return jdbc.query(SELECT_PRODUCT, params, rowMapper);
	}
	
	public List<Product.Image> selectProductImages(int displayInfoId, String type) {
		Map<String, Object> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("type", type);
		return jdbc.query(SELECT_PRODUCT_IMAGES, params, rowMapper2);
	}
	
	public List<Display.InfoImage> selectDisplayInfoImages(int displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_DISPLAY_INFO_IMAGES, params, rowMapper3);
	}
	
	public int selectAvgScore(int displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_AVG_SCORE, params, Integer.class);
	}
	
	public List<Product.PriceDB> selectProductPrices(int displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_PRICES, params, rowMapper4);
	}
	
	public int selectExistsDisplayInfo(int displayInfoId){
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_EXISTS_DISPLAY_INFO, params, Integer.class);
	}
}