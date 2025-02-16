package tech.fiap.project.app.service.kitchen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FinishOrderServiceTest {

	@Mock
	private RestTemplate restTemplate;

	private FinishOrderService finishOrderService;

	private final String orderBaseUrl = "https://api.example.com/orders/%d/finish";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		finishOrderService = new FinishOrderService(restTemplate, orderBaseUrl);
	}

	@Test
	void finishOrder_ShouldCallRestTemplateAndReturnResponse() {
		// Arrange
		Long orderId = 123L;
		String authorization = "Bearer token";
		String expectedUrl = String.format(orderBaseUrl, orderId);
		ResponseEntity<String> mockResponse = ResponseEntity.ok("Order finished successfully");

		when(restTemplate.postForEntity(anyString(), eq(null), eq(String.class))).thenReturn(mockResponse);

		// Act
		ResponseEntity<String> response = finishOrderService.finishOrder(orderId, authorization);

		// Assert
		assertNotNull(response);
		assertEquals("Order finished successfully", response.getBody());

		// Verify that the RestTemplate is called with the correct parameters
		verify(restTemplate, times(1)).postForEntity(eq(expectedUrl), eq(null), eq(String.class));
	}

}
