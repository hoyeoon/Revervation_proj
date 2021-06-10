package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Product {
	@Getter @Setter
	@ToString
	public static class Image {
		private int productId;
		private int productImageId;
		private String type;
		private int fileInfoId;
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
	public static class Info {
		private int id;
		private int categoryId;
		private int displayInfoId;
		private String name;
		private String description;
		private String content;
		private String event;
		private String openingHours;
		private String placeName;
		private String placeLot;
		private String placeStreet;
		private String tel;
		private String homepage;
		private String email;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createDate;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifyDate;
		private int fileId;
	}

	@Getter @Setter
	@ToString
	public static class Price {
		private int count;
		private int productPriceId;
	}
	
	@Getter @Setter
	@ToString
	public static class PriceDB {
	    private int id;
	    private int productId;
	    private String priceTypeName;
	    private int price;
	    private int discountRate;
	    private LocalDateTime createDate;
	    private LocalDateTime modifyDate;
	}
	
	@Getter @Setter
	@ToString
	@AllArgsConstructor
	public static class Response {
		private int totalCount;
		private int productCount;
		private List<Product.Info> products;
	}	
}
