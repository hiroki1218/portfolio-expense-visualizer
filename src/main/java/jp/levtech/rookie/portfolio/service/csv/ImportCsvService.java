package jp.levtech.rookie.portfolio.service.csv;

import org.springframework.web.multipart.MultipartFile;

public interface ImportCsvService {
	
	void importCsv(MultipartFile file);
	
}
