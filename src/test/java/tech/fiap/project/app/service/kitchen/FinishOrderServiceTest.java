package tech.fiap.project.app.service.kitchen;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import tech.fiap.project.app.dto.KitchenDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FinishOrderServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private FinishOrderService finishOrderService;

	private final String clientId = "test-client";
	private final String keycloakBaseUrl = "http://keycloak.test";
	private final String clientSecret = "test-secret";
	private final String orderServiceUrl = "http://orders.test";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		finishOrderService = new FinishOrderService(restTemplate, clientId, keycloakBaseUrl, clientSecret, orderServiceUrl);
	}

	@Test
	void shouldGetAccessTokenSuccessfully() {
		// Mock Keycloak response
		ObjectNode tokenResponse = JsonNodeFactory.instance.objectNode();
		tokenResponse.put("access_token", "mock-token");

		ResponseEntity<ObjectNode> responseEntity = new ResponseEntity<>(tokenResponse, HttpStatus.OK);

		when(restTemplate.exchange(
				eq(keycloakBaseUrl + "/realms/master/protocol/openid-connect/token"),
				eq(HttpMethod.POST),
				any(HttpEntity.class),
				eq(ObjectNode.class)
		)).thenReturn(responseEntity);

		// Call method
		String accessToken = finishOrderService.getAccessToken();

		// Verify
		assertEquals("mock-token", accessToken);
		verify(restTemplate, times(1)).exchange(
				eq(keycloakBaseUrl + "/realms/master/protocol/openid-connect/token"),
				eq(HttpMethod.POST),
				any(HttpEntity.class),
				eq(ObjectNode.class)
		);
	}

	@Test
	void shouldFinishOrderSuccessfully() {
		Long orderId = 123L;
		String mockToken = "mock-token";

		// Mock getAccessToken() method
		FinishOrderService spyService = spy(finishOrderService);
		doReturn(mockToken).when(spyService).getAccessToken();

		// Mock response from order service
		ResponseEntity<KitchenDTO> responseEntity = new ResponseEntity<>(new KitchenDTO(), HttpStatus.OK);

		when(restTemplate.exchange(
				eq(orderServiceUrl + "/api/v1/orders/done/" + orderId),
				eq(HttpMethod.PUT),
				any(HttpEntity.class),
				eq(KitchenDTO.class)
		)).thenReturn(responseEntity);

		// Call method
		spyService.finishOrder(orderId);

		// Verify
		verify(restTemplate, times(1)).exchange(
				eq(orderServiceUrl + "/api/v1/orders/done/" + orderId),
				eq(HttpMethod.PUT),
				any(HttpEntity.class),
				eq(KitchenDTO.class)
		);
	}
}
