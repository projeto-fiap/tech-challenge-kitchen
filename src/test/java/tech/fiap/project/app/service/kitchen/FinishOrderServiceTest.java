package tech.fiap.project.app.service.kitchen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

class FinishOrderServiceTest {

	@Mock
	private RestTemplate restTemplate;

	private FinishOrderService finishOrderService;

	private final String orderBaseUrl = "https://api.example.com/orders/finish";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		finishOrderService = new FinishOrderService(restTemplate, orderBaseUrl, "clientId", "keycloakBaseUrl",
				"clientSecret");
	}

	@Test
	void finishOrder_ShouldCallRestTemplateAndReturnResponse() {
		Long orderId = 123L;
		ResponseEntity<String> mockResponse = ResponseEntity.ok("Order finished successfully");

		when(restTemplate.postForEntity(anyString(), eq(null), eq(String.class))).thenReturn(mockResponse);

		Assertions.assertDoesNotThrow(() -> finishOrderService.finishOrder(orderId));
	}

}
