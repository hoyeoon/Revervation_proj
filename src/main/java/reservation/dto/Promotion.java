package kr.or.connect.reservation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Promotion {
	@Getter @Setter
	@ToString
	public static class Info {
		private int id;
		private int productId;
		private int categoryId;
		private String categoryName;
		private String description;
		private int fileId;
	}
	
	@Getter @Setter
	@ToString
	@AllArgsConstructor
	public static class Response {
		private int size;
		private List<Promotion.Info> items;
	}	
}