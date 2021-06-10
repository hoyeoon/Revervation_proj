package kr.or.connect.reservation.dao;

public class FileDaoSqls {
	public static final String SELECT_FILE_NAME = "SELECT file_name FROM file_info WHERE id = :id";
	public static final String SELECT_SAVE_FILE_NAME = "SELECT save_file_name FROM file_info WHERE id = :id";
	public static final String SELECT_CONTENT_TYPE = "SELECT content_type FROM file_info WHERE id = :id";
}