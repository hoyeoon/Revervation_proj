package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Comment {
	@Getter @Setter
	@ToString
	@RequiredArgsConstructor
	public static class DB {
		private int id;
		@NonNull private int productId;
		@NonNull private int reservationInfoId;
		@NonNull private int userId;
		@NonNull private int score;
		@NonNull private String comment;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@NonNull private LocalDateTime createDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@NonNull private LocalDateTime modifyDate;
	}
	
	@Getter @Setter
	@ToString
	public static class Image {
		private int id;
		private int reservationInfoId;
		private int reservationUserCommentId;
		private int fileId;
		private String fileName;
		private String saveFileName;
		private String contentType;
		private int deleteFlag;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifyDate;
	}
	
	@Getter @Setter
	@ToString
	@RequiredArgsConstructor
	public static class ImageDB {
		private int id;
		@NonNull private int reservationInfoId;
		@NonNull private int reservationUserCommentId;
		@NonNull private int fileId;
	}
	
	@Getter @Setter
	@ToString
	public static class Info {
		private int id;
		private int productId;
		private int reservationInfoId;
		private int score;
		private int userId;
		private String comment;
		private List<Comment.Image> reservationUserCommentImages;
	}

	@Getter @Setter
	@ToString
	@AllArgsConstructor
	public static class RegisterResponse {
		private String result;
		private int productId;
	}
	
	@Getter @Setter
	@ToString
	@AllArgsConstructor
	public static class Response {
		private int totalCount;
		private int commentCount;
		private List<Comment.Info> reservationUserComments;
	}
}
