package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class User {
	@Getter @Setter
	@ToString
	public static class DB {
		private int id;
		private String name;
		private String password;
		private String email;
		private String phone;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifyDate;
	}

	@Getter @Setter
	@ToString
	public static class RoleDB {
	    private int id;
	    private int userId;
	    private String roleName;
	}
}