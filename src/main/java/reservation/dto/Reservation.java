package kr.or.connect.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Reservation {
	@Getter @Setter
	@ToString
	public static class Info {
		private int id;
		private int productId;
		private int displayInfoId;
		private int cancelFlag;
		private String productDescription;
		private String productContent;
		private int userId;
		private int sumPrice;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime reservationDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifyDate;
	}
	
	@Getter @Setter
	@ToString
	@RequiredArgsConstructor
	public static class InfoDB {
		private int id;
		@NonNull private int productId;
		@NonNull private int displayInfoId;
		@NonNull private int userId;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@NonNull private LocalDateTime reservationDate;
		@NonNull private int cancelFlag;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@NonNull private LocalDateTime createDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@NonNull private LocalDateTime modifyDate;
	}
	
	@Getter @Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@RequiredArgsConstructor
	public static class InfoPriceDB {
		private int id;
		@NonNull private int reservationInfoId;
		@NonNull private int productPriceId;
		@NonNull private int count;
	}
	
	@Getter @Setter
	@ToString
	@AllArgsConstructor
	public static class InfoResponse {
		private int size;
		private List<Reservation.Info> items;
	}
	
	@Getter @Setter
	@ToString
	public static class RegistrationRequest {
		private List<Product.Price> prices;
		private int productId;
		private int displayInfoId;
		@JsonFormat(pattern = "yyyy.MM.dd")
		private LocalDate reservationYearMonthDay;
		private int userId;
	}
	
	@Getter @Setter
	@ToString
	@AllArgsConstructor
	public static class RegistrationResponse {
		private int id;
		private int productId;
		private int cancelFlag;
		private int displayInfoId;
		private int userId;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime reservationDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifyDate;
		private List<Reservation.InfoPriceDB> prices;
	}
}