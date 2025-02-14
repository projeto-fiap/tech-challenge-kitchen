package tech.integration.kitchen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.infra.configuration.Configuration;
import tech.fiap.project.infra.entity.KitchenEntity;
import tech.fiap.project.infra.repository.KitchenRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = { Configuration.class, ServletWebServerFactoryAutoConfiguration.class })
@ActiveProfiles("integration-test")
public class KitchenIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private KitchenRepository kitchenRepository;

	@BeforeEach
	void createUser() {
		if (kitchenRepository.findAll().isEmpty()) {
			KitchenEntity kitchen = new KitchenEntity();
			kitchen.setStatus(KitchenStatus.AWAITING_PRODUCTION);
			kitchen.setOrderId(1L);
			kitchen.setCreationDate(LocalDateTime.now());
			kitchen.setUpdatedDate(LocalDateTime.now());
			kitchenRepository.save(kitchen);
		}
	}

	@Test
	void getItem_shouldReturnItem_whenSuccessful() {
		KitchenDTO requestDTO = new KitchenDTO();
		ResponseEntity<KitchenDTO> response = restTemplate.getForEntity("/api/v1/kitchen/1", KitchenDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void createItem_shouldReturnItem_whenSuccessful() {
		KitchenDTO requestDTO = new KitchenDTO();
		ResponseEntity<KitchenDTO> response = restTemplate.postForEntity("/api/v1/kitchen/2/create", requestDTO,
				KitchenDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getStatus(), KitchenStatus.AWAITING_PRODUCTION);
	}

	@Test
	void productionItem_shouldReturnItem_whenSuccessful() {
		KitchenDTO requestDTO = new KitchenDTO();
		ResponseEntity<KitchenDTO> response = restTemplate.exchange("/api/v1/kitchen/1/production", HttpMethod.PUT,
				new HttpEntity<>(requestDTO), KitchenDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getStatus(), KitchenStatus.IN_PRODUCTION);
	}

}
