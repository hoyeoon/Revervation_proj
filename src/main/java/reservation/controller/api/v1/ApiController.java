package kr.or.connect.reservation.controller.api.v1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.FileInfoService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.PromotionService;
import kr.or.connect.reservation.service.ReservationService;
import kr.or.connect.reservation.service.UserService;
import kr.or.connect.reservation.service.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class ApiController {
	private final CategoryService categoryService;
	private final ProductService productService;
	private final PromotionService promotionService;
	private final DisplayInfoService displayInfoService;
	private final CommentService commentService;
	private final ReservationService reservationService;
	private final FileInfoService fileInfoService;
	private final UserService userService;
	
	@ApiOperation(value = "???????????? ?????? ?????????")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@GetMapping("/categories")
	public Category.Response categoryList() {
		int size = categoryService.getCategoryCount();
		List<Category.Info> items = categoryService.getCategories();
		
		Category.Response categoryResponse = new Category.Response(size, items);
		return categoryResponse;
	}

	@ApiOperation(value = "?????? ?????? ?????????")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", value = "???????????? ?????????(0 ?????? ?????? ?????? ?????? ??????)", dataType = "int", required = false, paramType = "query"),
			@ApiImplicitParam(name = "start", value = "?????? ??????(?????? ?????? 0)", dataType = "int", required = false, paramType = "query") })
	@GetMapping("/products")
	public Product.Response productList(@RequestParam(defaultValue = "0") int categoryId,
			@RequestParam(defaultValue = "0") int start) {
		List<Product.Info> products = productService.getProducts(categoryId, start);
		int totalCount = productService.getTotalCount(categoryId);
		int productCount = products.size();

		Product.Response productResponse = new Product.Response(totalCount, productCount, products);
		return productResponse;
	}

	@ApiOperation(value = "???????????? ?????? ?????????")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@GetMapping("/promotions")
	public Promotion.Response promotionList() {
		int size = promotionService.getPromotionCount();
		List<Promotion.Info> items = promotionService.getPromotions();
		
		Promotion.Response promotionResponse = new Promotion.Response(size, items);
		return promotionResponse;
	}

	@ApiOperation(value = "?????? ?????? ?????????")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@GetMapping("/displayinfos/{displayId}")
	public Display.InfoResponse displayInfoList(@PathVariable(name = "displayId") int displayInfoId) {
		List<Product.Info> product = displayInfoService.getProduct(displayInfoId);
		List<Product.Image> productImages = displayInfoService.getProductImages(displayInfoId);
		List<Display.InfoImage> displayInfoImages = displayInfoService.getDisplayInfoImages(displayInfoId);
		int avgScore = displayInfoService.getAvgScore(displayInfoId);
		List<Product.PriceDB> productPrices = displayInfoService.getProductPrices(displayInfoId);

		Display.InfoResponse displayInfoResponse = new Display.InfoResponse(product, productImages, displayInfoImages, avgScore, productPrices);
		return displayInfoResponse;
	}

	@ApiOperation(value = "?????? ?????? ?????????")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productId", value = "?????? ?????????", dataType = "int", required = false, paramType = "query"),
			@ApiImplicitParam(name = "start", value = "?????? ??????(?????? ?????? 0)", dataType = "int", required = false, paramType = "query") })
	@GetMapping("/comments")
	public Comment.Response commentList(@RequestParam int productId,
			@RequestParam(defaultValue = "0") int start) {
		List<Comment.Info> reservationUserComments = commentService.getComments(productId, start);
		int totalCount = commentService.getTotalCount(productId);
		int commentCount = reservationUserComments.size();

		Comment.Response commentResponse = new Comment.Response(totalCount, commentCount, reservationUserComments);
		return commentResponse;
	}
	
	@ApiOperation(value = "?????? ????????????")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@PostMapping("/reservationInfos")
	public Reservation.RegistrationResponse createReservation(@AuthenticationPrincipal CustomUserDetails customUserDetails, 
			@RequestBody(required = true) Reservation.RegistrationRequest reservationRegistrationRequest) throws ParseException {

		for(Product.Price productPriceRequest : reservationRegistrationRequest.getPrices()){
			if(productService.isExistProductPriceId(productPriceRequest.getProductPriceId()) == 0)
				throw new RuntimeException(productPriceRequest.getProductPriceId()
						+ "???(???) ???????????? ?????? productPriceId ?????????. product_price ???????????? ???????????????.");
		}
		int userId = userService.getUserId(customUserDetails.getUsername());
		if(userId != reservationRegistrationRequest.getUserId()){
			throw new RuntimeException(reservationRegistrationRequest.getUserId()
					+ "???(???) ???????????? ???????????? id??? ????????????. userId ?????? ???????????????.");
		}else if(productService.isExistProductId(reservationRegistrationRequest.getProductId()) == 0){
			throw new RuntimeException(reservationRegistrationRequest.getProductId() +
					 "???(???) ???????????? ?????? productId ?????????. product ???????????? ???????????????.");
		}else if(displayInfoService.isExistDisplayInfoId(reservationRegistrationRequest.getDisplayInfoId()) == 0){
			throw new RuntimeException(reservationRegistrationRequest.getDisplayInfoId() +
					"???(???) ???????????? ?????? displayInfoId ?????????. display_info ???????????? ???????????????.");
		}
		return reservationService.registerReservation(reservationRegistrationRequest);
	}
	
	@ApiOperation(value = "?????? ?????? ?????????")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@GetMapping("/reservationInfos")
	public Reservation.InfoResponse reservationList(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		int userId = userService.getUserId(customUserDetails.getUsername());
		List<Reservation.Info> items = reservationService.getReservations(userId);
		int size = reservationService.getReservationCount(userId);

		Reservation.InfoResponse reservationResponse = new Reservation.InfoResponse(size, items);
		return reservationResponse;
	}
	
	@ApiOperation(value = "?????? ????????????")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@PutMapping("/reservationInfos")
	public Map<String, String> cancelReservation(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			@RequestBody(required = true) Map<String, Integer> param) {
		int userId = userService.getUserId(customUserDetails.getUsername());
		int reservationId = param.get("id");
		int result = reservationService.cancelReservation(reservationId, userId);

		HashMap<String, String> map = new HashMap<>();
		if (result == 1)
			map.put("result", "success");
		else
			map.put("result", "fail");
		return map;
	}

	@ApiOperation(value = "?????? ????????????", produces = "multipart/form-data")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "reservationInfoId", value = "reservationInfoId", dataType = "int", required = true, paramType = "query"),
			@ApiImplicitParam(name = "score", value = "score", dataType = "int", required = true, paramType = "query"),
			@ApiImplicitParam(name = "comment", value = "comment", dataType = "String", required = true, paramType = "query") })
	@PostMapping("/comments")
	public Comment.RegisterResponse createComment(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			@RequestParam(defaultValue = "") int reservationInfoId,
			@RequestParam(defaultValue = "") int score,
			@RequestParam(defaultValue = "") String comment,
			@RequestParam(value = "multipartFile", required = true) MultipartFile file) {
		int userId = userService.getUserId(customUserDetails.getUsername());
		int productId = reservationService.getProductId(reservationInfoId);
		if(score < 1 || score > 5){
			throw new RuntimeException("1????????? 5??? ????????? ????????? ???????????????.");
		}
		return commentService.registerComment(userId, productId, reservationInfoId, score, comment, file);
	}
}