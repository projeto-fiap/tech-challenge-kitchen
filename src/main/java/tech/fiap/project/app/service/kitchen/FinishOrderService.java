package tech.fiap.project.app.service.kitchen;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class FinishOrderService {

	private final RestTemplate restTemplate;

	private final String orderBaseUrl;

	public ResponseEntity<String> finishOrder(Long order, String authorization) {

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", authorization);
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		header.add("Accept", "application/json");

		String uri = String.format(orderBaseUrl, order);

		return restTemplate.postForEntity(uri, null, String.class);
	}

}