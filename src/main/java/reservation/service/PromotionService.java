package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Promotion;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionService {
	private final PromotionDao promotionDao;
	private static final String TYPE = "ma";
	
	public int getPromotionCount() {
		return promotionDao.selectPromotionCount();
	}

	public List<Promotion.Info> getPromotions(){
		return promotionDao.selectPromotions(TYPE);
	}
}
