package kr.or.connect.reservation.dao;

public class UserDaoSqls {
	public static final String SELECT_ALL_USER_BY_EMAIL = "SELECT id, name, password, email, phone, create_date, modify_date FROM user WHERE email = :email";
	public static final String SELECT_ID_BY_EMAIL = "SELECT id FROM user WHERE email = :email";
	public static final String SELECT_ALL_USER_ROLE_BY_EMAIL = "SELECT ur.id, ur.user_id, ur.role_name FROM user_role ur "
            + "JOIN user u ON ur.user_id = u.id WHERE u.email = :email";
}