package jp.hiroki.rookie.portfolio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.hiroki.rookie.portfolio.repository")
public class PortfolioApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}
	
}
