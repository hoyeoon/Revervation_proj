package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.FileDaoSqls.SELECT_CONTENT_TYPE;
import static kr.or.connect.reservation.dao.FileDaoSqls.SELECT_FILE_NAME;
import static kr.or.connect.reservation.dao.FileDaoSqls.SELECT_SAVE_FILE_NAME;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.File;

@Repository
public class FileDao {
	private SimpleJdbcInsert insertAction;
	private NamedParameterJdbcTemplate jdbc;
	
	public FileDao(DataSource dataSource){
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("file_info").usingGeneratedKeyColumns("id");
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int insertFileInfo(File.InfoDB fileInfo){
		SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public String selectFileName(int fileInfoId){
		Map<String, Integer> params = Collections.singletonMap("id", fileInfoId);
		return jdbc.queryForObject(SELECT_FILE_NAME, params, String.class);
	}
	
	public String selectSaveFileName(int fileInfoId){
		Map<String, Integer> params = Collections.singletonMap("id", fileInfoId);
		return jdbc.queryForObject(SELECT_SAVE_FILE_NAME, params, String.class);
	}

	public String selectContentType(int fileInfoId){
		Map<String, Integer> params = Collections.singletonMap("id", fileInfoId);
		return jdbc.queryForObject(SELECT_CONTENT_TYPE, params, String.class);
	}
}