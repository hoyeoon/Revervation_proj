package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dto.Category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryDao categoryDao;
	
	public List<Category.Info> getCategories() {
		List<Category.Info> list = categoryDao.selectCategories();
		
		for(Category.Info category : list){
			int count = categoryDao.selectDisplayInfoCountByCategoryId(category.getId());
			category.setCount(count);
		}
		return list;
	}
	
	public int getCategoryCount() {
		return categoryDao.selectCategoryCount();
	}
}