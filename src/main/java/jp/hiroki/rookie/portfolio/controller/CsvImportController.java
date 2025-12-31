package jp.hiroki.rookie.portfolio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ★これを追加
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.hiroki.rookie.portfolio.service.csv.ImportCsvService;
import jp.hiroki.rookie.portfolio.service.csv.common.CsvFormatDetector;
import jp.hiroki.rookie.portfolio.service.csv.common.CsvReader;
import jp.hiroki.rookie.portfolio.service.csv.enums.CsvFormat;

@Controller
@RequestMapping("/csv")
public class CsvImportController {
	
	private final ImportCsvService importCsvService;
	private final CsvReader csvReader;
	private final CsvFormatDetector csvFormatDetector;
	
	public CsvImportController(
			ImportCsvService importCsvService,
			CsvReader csvReader,
			CsvFormatDetector csvFormatDetector) {
		this.importCsvService = importCsvService;
		this.csvReader = csvReader;
		this.csvFormatDetector = csvFormatDetector;
	}
	
	// 1. フォーム表示
	@GetMapping("/import")
	public String form() {
		return "import";
	}
	
	// 2. ファイル受け取り→結果メッセージを画面に返す
	@PostMapping("/import")
	public String importCsv(@RequestParam("files") MultipartFile[] files,
			Model model) { // ★ Model を引数に追加
		
		if (files == null || files.length != 2) {
			model.addAttribute("statusMessage",
					"エラー：CSV ファイルは必ず 2 つアップロードしてください。");
			return "import";
		}
		
		MultipartFile bankCsv = null;
		MultipartFile creditCsv = null;
		
		for (MultipartFile file : files) {
			try {
				
				List<String> header = csvReader.readHeader(file);
				CsvFormat format = csvFormatDetector.detect(header);
				
				if (format == CsvFormat.BANK_MUFG) {
					bankCsv = file;
				} else if (format == CsvFormat.CREDIT_MUFG) {
					creditCsv = file;
				}
			} catch (Exception e) {
				model.addAttribute("statusMessage",
						"エラー：対応していない CSV → " + file.getOriginalFilename());
				return "import";
			}
		}
		
		if (bankCsv == null || creditCsv == null) {
			model.addAttribute("statusMessage",
					"エラー：銀行 CSV とクレカ CSV の両方をアップロードしてください。");
			return "import";
		}
		
		try {
			importCsvService.importCsv(bankCsv);
			importCsvService.importCsv(creditCsv);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("statusMessage",
					"エラー：CSV の取り込みに失敗しました。");
			return "import";
		}
		
		model.addAttribute("statusMessage", "正常に取り込みが完了しました。");
		return "import";
	}
}
