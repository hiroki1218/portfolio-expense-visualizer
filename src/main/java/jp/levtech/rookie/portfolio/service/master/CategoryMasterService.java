package jp.levtech.rookie.portfolio.service.master;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jp.levtech.rookie.portfolio.dto.MasterDto;
import jp.levtech.rookie.portfolio.repository.master.CategoryMasterMapper;

@Service
public class CategoryMasterService {
	
	private final CategoryMasterMapper mapper;
	
	public CategoryMasterService(CategoryMasterMapper mapper) {
		this.mapper = mapper;
	}
	
	public void create(String categoryName) {
		if (!StringUtils.hasText(categoryName)) {
			throw new IllegalArgumentException("カテゴリ名は必須です");
		}
		try {
			mapper.insert(categoryName);
		} catch (DuplicateKeyException e) {
			throw new IllegalArgumentException("そのカテゴリ名は既に登録済みです");
		}
	}
	
	public void update(Long id, String categoryName) {
		if (id == null) {
			throw new IllegalArgumentException("IDが不正です");
		}
		if (!StringUtils.hasText(categoryName)) {
			throw new IllegalArgumentException("カテゴリ名が必須です");
		}
		try {
			mapper.update(id, categoryName);
		} catch (DuplicateKeyException e) {
			throw new IllegalArgumentException("そのカテゴリ名は既に存在します");
		}
	}
	
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("IDが不正です");
		}
		try {
			mapper.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("このカテゴリは使用中のため削除できません");
		}
	}
	
	public MasterDto findById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("IDが不正です");
		}
		
		MasterDto dto = mapper.findById(id);
		if (dto == null) {
			throw new IllegalArgumentException("対象のカテゴリが存在しません");
		}
		return dto;
	}
	
}
