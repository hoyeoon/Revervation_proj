package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.DisplayDao;
import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.Product;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisplayInfoService {
	private final DisplayDao displayInfoDao;
	private static final String TYPE = "ma";
	
	public List<Product.Info> getProduct(int displayInfoId) {
		return displayInfoDao.selectProduct(displayInfoId, TYPE);
	}

	public List<Product.Image> getProductImages(int displayInfoId) {
		return displayInfoDao.selectProductImages(displayInfoId, TYPE);
	}

	public List<Display.InfoImage> getDisplayInfoImages(int displayInfoId) {		
		return displayInfoDao.selectDisplayInfoImages(displayInfoId);
	}

	public int getAvgScore(int displayInfoId) {
		return displayInfoDao.selectAvgScore(displayInfoId);
	}

	public List<Product.PriceDB> getProductPrices(int displayInfoId) {
		return displayInfoDao.selectProductPrices(displayInfoId);
	}
	
	public int isExistDisplayInfoId(int displayInfoId){
		return displayInfoDao.selectExistsDisplayInfo(displayInfoId);
	}
}