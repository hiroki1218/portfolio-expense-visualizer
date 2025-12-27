package jp.levtech.rookie.portfolio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.levtech.rookie.portfolio.dto.MasterDto;
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
	
	@GetMapping("{type}")
	public String list(@PathVariable("type") String type, Model model) {
		
		MasterType masterType = MasterType.fromPath(type);
		List<MasterDto> rows = masterService.list(masterType);
		
		model.addAttribute("type", masterType);
		model.addAttribute("rows", rows);
		
		return "masters/list";
		
	}
	
}
