package jp.levtech.rookie.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jp.levtech.rookie.portfolio.dto.CreditCardImportDto;
import jp.levtech.rookie.portfolio.service.CreditCardCsvImportService;

//http://localhost:8080/csv/import

@Controller                 // ① このクラスはWebの入り口ですよ、という宣言
@RequestMapping("/csv")     // ② 全てのURLの先頭に /csv を付ける

public class CsvImportController {

    @Autowired
    private CreditCardCsvImportService creditCardCsvImportService;
    // ③ Service をDI（Springが自動でインスタンスを入れてくれる）
    //    → ControllerはServiceを“呼ぶだけ”

    @GetMapping("/import")
    public String ImportForm() {
        // ④ ただ画面(import.html)を返すだけ
        return "import";
    }

    @PostMapping(path = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public List<CreditCardImportDto> importCsv(@RequestParam("file") MultipartFile file) throws Exception {

        // ⑤ CSVファイルを受け取り、Serviceに丸投げ
        return creditCardCsvImportService.importCreditCardCsv(file);

        // ⑥ Serviceの実行結果（DTOのリスト）をJSONとして返す
    }
}