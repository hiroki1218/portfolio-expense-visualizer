package jp.levtech.rookie.portfolio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.levtech.rookie.portfolio.dto.master.MasterDto;
import jp.levtech.rookie.portfolio.master.MasterType;
import jp.levtech.rookie.portfolio.service.master.MasterService;

@Controller
@RequestMapping("/masters")
public class MasterController {
	
	private final MasterService masterService;
	
	public MasterController(MasterService masterService) {
		this.masterService = masterService;
	}
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("types", MasterType.values());
		return "masters/index";
	}
	
	@GetMapping("/{type}")
	public String list(@PathVariable("type") String type, Model model) {
		
		MasterType masterType = MasterType.fromPath(type);
		List<MasterDto> rows = masterService.list(masterType);
		List<MasterDto> categories = masterService.list(MasterType.CATEGORY);
		
		model.addAttribute("type", masterType);
		model.addAttribute("rows", rows);
		model.addAttribute("categories", categories);
		
		return "masters/list";
		
	}
	
	@PostMapping("{type}/update")
	public String update(@PathVariable String type, @RequestParam Long id, @RequestParam Long categoryId,
			RedirectAttributes redirectAttributes) {
		
		MasterType masterType = MasterType.fromPath(type);
		
		try {
			masterService.update(masterType, id, categoryId);
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/masters/" + masterType.getPath();
	}
}
