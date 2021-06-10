package kr.or.connect.reservation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Category {
	@Getter @Setter
	@ToString
	public static class Info {
		private int id;
		private String name;
		private int count;
	}
	
	@Getter @Setter
	@ToString
	@AllArgsConstructor
	public static class Response {
		private int size;
		private List<Category.Info> items;
	}
}