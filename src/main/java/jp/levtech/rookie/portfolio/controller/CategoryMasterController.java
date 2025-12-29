package jp.levtech.rookie.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.levtech.rookie.portfolio.dto.MasterDto;
import jp.levtech.rookie.portfolio.service.master.CategoryMasterService;

@Controller
@RequestMapping("/masters/category")
public class CategoryMasterController {
	
	private final CategoryMasterService categoryMasterService;
	
	public CategoryMasterController(CategoryMasterService categoryMasterService) {
		this.categoryMasterService = categoryMasterService;
	}
	
	//新規登録画面
	@GetMapping("/new")
	public String newCategory() {
		return "masters/category/new";
	}
	
	//新規登録
	@PostMapping("/create")
	public String create(@RequestParam("categoryName") String categoryName,
			RedirectAttributes redirectAttributes) {
		try {
			categoryMasterService.create(categoryName);
			redirectAttributes.addFlashAttribute("successMessage", "カテゴリを登録しました");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/masters/category/new";
	}
	
	//編集
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		MasterDto category = categoryMasterService.findById(id);
		model.addAttribute("category", category);
		return "masters/category/edit";
	}
	
	//更新
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id,
			@RequestParam("categoryName") String categoryName,
			RedirectAttributes redirectAttributes) {
		try {
			categoryMasterService.update(id, categoryName);
			redirectAttributes.addFlashAttribute("successMessage", "更新しました");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/masters/category/" + id + "/edit";
	}
	
	// 削除
	@PostMapping("/{id}/delete")
	public String delete(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes) {
		try {
			categoryMasterService.delete(id);
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/masters/category";
	}
	
}
