package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Display {
	@Getter @Setter
	@ToString
	public static class InfoImage {
		private int id;
		private int displayInfoId;
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
	@AllArgsConstructor
	public static class InfoResponse {
		private List<Product.Info> product;
		private List<Product.Image> productImages;
		private List<Display.InfoImage> displayInfoImages ;
		private int avgScore;
		private List<Product.PriceDB> productPrices;
	}
}
