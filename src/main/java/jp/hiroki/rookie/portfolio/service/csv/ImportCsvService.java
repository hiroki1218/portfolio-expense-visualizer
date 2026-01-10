package jp.hiroki.rookie.portfolio.service.csv;

import org.springframework.web.multipart.MultipartFile;

public interface ImportCsvService {
	//csv取り込み
	void importCsv(MultipartFile file);
	
}
