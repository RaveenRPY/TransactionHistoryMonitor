package dev.danvega;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.danvega.domain.Detail;
import dev.danvega.domain.User;
import dev.danvega.service.DetailService;
import dev.danvega.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class JsontodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsontodbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService, DetailService detailService) {
		return args -> {
			// Read JSON and load User data
			ObjectMapper mapperInvoice = new ObjectMapper();
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/Invoice.json");

			// Read JSON and load Detail data
			ObjectMapper mapperDetail = new ObjectMapper();
			TypeReference<List<Detail>> typeRefDetail = new TypeReference<List<Detail>>() {};
			InputStream inputStreamDetail = TypeReference.class.getResourceAsStream("/json/Details.json");

			try {
				List<Detail> details = mapperDetail.readValue(inputStreamDetail, typeRefDetail);
				detailService.saveAllEntities(details);

				List<User> users = mapperInvoice.readValue(inputStream, typeReference);
				detailService.updateUsersWithTotalPaidAmount(users, details);
				userService.save(users);

			} catch (IOException e) {
				System.out.println("Unable to save details: " + e.getMessage());
			}
		};
	}
}
