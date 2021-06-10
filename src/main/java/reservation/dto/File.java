package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class File {
	@Getter @Setter
	@ToString
	@RequiredArgsConstructor
	public static class InfoDB {
		private int id;
		@NonNull private String fileName;
		@NonNull private String saveFileName;
		@NonNull private String contentType;
		@NonNull private int deleteFlag;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@NonNull private LocalDateTime createDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@NonNull private LocalDateTime modifyDate;
	}
}