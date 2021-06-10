package kr.or.connect.reservation.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import kr.or.connect.reservation.ApplicationConfig;
import kr.or.connect.reservation.MvcConfig;
import kr.or.connect.reservation.controller.api.v1.ApiController;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.PromotionService;
import kr.or.connect.reservation.service.ReservationService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, ApplicationConfig.class })
public class ApiControllerTest {
	private MockMvc mockMvc;
	@Mock
	CategoryService categoryService;
	@Mock
	ProductService productService;
	@Mock
	PromotionService promotionService;
	@Mock
	DisplayInfoService displayInfoService;
	@Mock
	CommentService commentService;
	@Mock
	ReservationService reservationService;
	
	@InjectMocks
	public ApiController apiController;
	
	@Before
	public void createController(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
	}
	
	@Test
	public void getCategories() throws Exception {
		Category.Info category = new Category.Info();
		category.setId(1);
		category.setName("뮤지컬");
		category.setCount(10);
		
		List<Category.Info> list = Arrays.asList(category);		
		when(categoryService.getCategories()).thenReturn(list);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/categories").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		verify(categoryService).getCategories();
	}
	
	@Test
	public void getProducts() throws Exception {
		Product.Info product = new Product.Info();
		product.setId(21);
		product.setCategoryId(3);
		product.setDisplayInfoId(21);
		product.setName("콘서트");
		product.setDescription("5TARDIUM 2018");
		product.setContent("Surrealistic EDM Festival with Magnificent Stage and Performances. 2 Days, 10 Artists for 5th Anniversary!");
		product.setEvent("");
		product.setOpeningHours("2017년 12월 12일(토) 12PM");
		product.setPlaceName("장소 추후 공개");
		product.setPlaceLot("서울특별시 중구 태평로1가 31");
		product.setPlaceStreet("서울특별시 중구 세종대로 110");
		product.setTel("010-3360-7846");
		product.setHomepage("");
		product.setEmail("");
		product.setCreateDate(LocalDateTime.now());
		product.setModifyDate(LocalDateTime.now());
		product.setFileId(113);
		
		List<Product.Info> list = Arrays.asList(product);		
		when(productService.getProducts(0, 0)).thenReturn(list);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/products").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		verify(productService).getProducts(0, 0);
	}
	
	@Test
	public void getPromotions() throws Exception {
		Promotion.Info promotion = new Promotion.Info();
		promotion.setId(1);
		promotion.setProductId(1);
		promotion.setCategoryId(1);
		promotion.setCategoryName("전시");
		promotion.setDescription("Paper, Present:너를 위한 선물");
		promotion.setFileId(61);
		
		List<Promotion.Info> list = Arrays.asList(promotion);		
		when(promotionService.getPromotions()).thenReturn(list);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/promotions").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		verify(promotionService).getPromotions();
	}
	
	@Test
	public void getProductImages() throws Exception {
		Product.Image productImage = new Product.Image();
		productImage.setProductId(1);
		productImage.setProductImageId(2);
		productImage.setType("ma");
		productImage.setFileInfoId(61);
		productImage.setFileName("1_ma_2.png");
		productImage.setSaveFileName("img/1_ma_2.png");
		productImage.setContentType("image/png");
		productImage.setDeleteFlag(0);
		productImage.setCreateDate(LocalDateTime.now());
		productImage.setModifyDate(LocalDateTime.now());
		
		List<Product.Image> list = Arrays.asList(productImage);		
		when(displayInfoService.getProductImages(1)).thenReturn(list);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/1").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		verify(displayInfoService).getProductImages(1);
	}
	
	@Test
	public void getDisplayInfoImages() throws Exception {
		Display.InfoImage displayInfoImage = new Display.InfoImage();
		displayInfoImage.setId(1);
		displayInfoImage.setDisplayInfoId(1);
		displayInfoImage.setFileId(1);
		displayInfoImage.setFileName("1_map_1.png");
		displayInfoImage.setSaveFileName("img_map/1_map_1.png");
		displayInfoImage.setContentType("image/png");
		displayInfoImage.setDeleteFlag(0);
		displayInfoImage.setCreateDate(LocalDateTime.now());
		displayInfoImage.setModifyDate(LocalDateTime.now());
		
		List<Display.InfoImage> list = Arrays.asList(displayInfoImage);
		when(displayInfoService.getDisplayInfoImages(1)).thenReturn(list);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/1").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		verify(displayInfoService).getDisplayInfoImages(1);
	}
	
	@Test
	public void getProductPrices() throws Exception {
		Product.PriceDB productPrice = new Product.PriceDB();
		productPrice.setId(3);
		productPrice.setProductId(1);
		productPrice.setPriceTypeName("B");
		productPrice.setPrice(2000);
		productPrice.setDiscountRate(50);
		productPrice.setCreateDate(LocalDateTime.now());
		productPrice.setModifyDate(LocalDateTime.now());
		
		List<Product.PriceDB> list = Arrays.asList(productPrice);
		when(displayInfoService.getProductPrices(1)).thenReturn(list);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/1").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		verify(displayInfoService).getProductPrices(1);
	}
	
	@Test
	public void getComments() throws Exception {
		Comment.Info comment = new Comment.Info();
		comment.setId(15);
		comment.setProductId(1);
		comment.setReservationInfoId(15);
		comment.setScore(5);
		comment.setUserId(1);
		comment.setComment("즐거움!!!");
		
		int productId = 1;
		int start = 1;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("productId", Integer.toString(productId));
		params.add("start", Integer.toString(start));
		
		List<Comment.Info> list = Arrays.asList(comment);
		when(commentService.getComments(productId, start)).thenReturn(list);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/comments").params(params).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		verify(commentService).getComments(productId, start);
	}
//	@Test
//	public void getReservationInfos() throws Exception {
//		Reservation reservation = new Reservation();
//		reservation.setId(16);
//		reservation.setProductId(1);
//		reservation.setDisplayInfoId(1);
//		reservation.setCancelFlag(1);
//		reservation.setProductDescription("너를 위한 선물");
//		reservation.setProductContent("대림미술관은 오는 12월 7일부터 ~~");
//		reservation.setUserId(1);
//		reservation.setSumPrice(4000);
//		reservation.setReservationDate(new Date());
//		reservation.setCreateDate(new Date());
//		reservation.setModifyDate(new Date());
//		
//		List<Reservation> list = Arrays.asList(reservation);
//		when(reservationService.getReservations(1)).thenReturn(list);
//		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/reservationInfos").contentType(MediaType.APPLICATION_JSON);
//		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
//		verify(reservationService).getReservations(1);
//	}
}
