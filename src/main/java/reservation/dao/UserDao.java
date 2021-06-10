package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.UserDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.User;

@Repository
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<User.DB> rowMapper = BeanPropertyRowMapper.newInstance(User.DB.class);
	private RowMapper<User.RoleDB> rowMapper2 = BeanPropertyRowMapper.newInstance(User.RoleDB.class);

	public UserDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public User.DB selectUserByEmail(String email) {
		Map<String, String> params = Collections.singletonMap("email", email);
		return jdbc.queryForObject(SELECT_ALL_USER_BY_EMAIL, params, rowMapper);
	}

	public int selectUserIdByEmail(String email) {
		Map<String, String> params = Collections.singletonMap("email", email);
		return jdbc.queryForObject(SELECT_ID_BY_EMAIL, params, Integer.class);
	}

	public List<User.RoleDB> selectRolesByEmail(String email) {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		return jdbc.query(SELECT_ALL_USER_ROLE_BY_EMAIL, map, rowMapper2);
	}
}