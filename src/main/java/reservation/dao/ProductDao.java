package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ProductDaoSqls.SELECT_ALL_PRODUCTS;
import static kr.or.connect.reservation.dao.ProductDaoSqls.SELECT_EXISTS_PRODUCT;
import static kr.or.connect.reservation.dao.ProductDaoSqls.SELECT_EXISTS_PRODUCT_PRICE;
import static kr.or.connect.reservation.dao.ProductDaoSqls.SELECT_PRODUCTS_BY_CATEGORY_ID;
import static kr.or.connect.reservation.dao.ProductDaoSqls.SELECT_TOTAL_COUNT;
import static kr.or.connect.reservation.dao.ProductDaoSqls.SELECT_TOTAL_COUNT_BY_CATEGORY_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product.Info> rowMapper = BeanPropertyRowMapper.newInstance(Product.Info.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int selectCountByCategoryId(int categoryId) {
		Map<String, Integer> params = Collections.singletonMap("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_TOTAL_COUNT_BY_CATEGORY_ID, params, Integer.class);
	}

	public int selectCount() {
		return jdbc.queryForObject(SELECT_TOTAL_COUNT, Collections.emptyMap(), Integer.class);
	}

	public List<Product.Info> selectProductsByCategoryId(int categoryId, String type, Integer start, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("type", type);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCTS_BY_CATEGORY_ID, params, rowMapper);
	}

	public List<Product.Info> selectAllProducts(String type, int start, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("type", type);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_PRODUCTS, params, rowMapper);
	}
	
	public int selectExistsProduct(int productId){
		Map<String, Integer> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(SELECT_EXISTS_PRODUCT, params, Integer.class);
	}
	
	public int selectExistsProductPrice(int productPriceId){
		Map<String, Integer> params = Collections.singletonMap("productPriceId", productPriceId);
		return jdbc.queryForObject(SELECT_EXISTS_PRODUCT_PRICE, params, Integer.class);
	}
}