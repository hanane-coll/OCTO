package ma.octo.assignement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class NiceBankApplication {
	public static void main(String[] args) {
		SpringApplication.run(NiceBankApplication.class, args);
	}
}
