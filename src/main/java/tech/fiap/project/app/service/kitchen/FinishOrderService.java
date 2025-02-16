package tech.fiap.project.app.service.kitchen;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import tech.fiap.project.app.dto.KitchenDTO;

import java.util.Objects;

@AllArgsConstructor
@Slf4j
public class FinishOrderService {

    private final RestTemplate restTemplate;
    private final String clientId;
    private final String keycloakBaseUrl;
    private final String clientSecret;
    private final String orderServiceUrl;

    public void finishOrder(Long order) {
        String accessToken = getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<JsonNode> requestEntity = new HttpEntity<>(null, headers);
        String url = String.format("%s/api/v1/orders/done/%s", orderServiceUrl, order);
        ResponseEntity<KitchenDTO> exchange = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, KitchenDTO.class);
        if (exchange.getStatusCode().is2xxSuccessful()) {
            log.info("Order sent to kitchen");
        }
    }

    protected String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = String.format("client_id=%s&client_secret=%s&grant_type=client_credentials", clientId, clientSecret);
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<ObjectNode> response = restTemplate.exchange(
                keycloakBaseUrl + "/realms/master/protocol/openid-connect/token", HttpMethod.POST, requestEntity,
                ObjectNode.class);
        return Objects.requireNonNull(response.getBody()).get("access_token").asText();
    }
}