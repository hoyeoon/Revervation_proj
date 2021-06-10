package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Product;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductDao productDao;
    private static final String TYPE = "ma";
    private static final int LIMIT = 4;
	
	public int getTotalCount(int categoryId) {
		int count;
		if(categoryId == 0){
			count = productDao.selectCount(); 
		}else{
			count = productDao.selectCountByCategoryId(categoryId);
		}
		return count;
	}
	
	public List<Product.Info> getProducts(int categoryId, int start) {
		List<Product.Info> list;
		if(categoryId == 0){
			list = productDao.selectAllProducts(TYPE, start, LIMIT);
		}else{
			list = productDao.selectProductsByCategoryId(categoryId, TYPE, start, LIMIT);
		}		
		return list;
	}
	
	public int isExistProductId(int productId){
		return productDao.selectExistsProduct(productId);
	}
	
	public int isExistProductPriceId(int productPriceId){
		return productDao.selectExistsProductPrice(productPriceId);
	}
}