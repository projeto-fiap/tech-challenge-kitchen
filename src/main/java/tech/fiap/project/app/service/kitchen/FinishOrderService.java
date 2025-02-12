package tech.fiap.project.app.service.kitchen;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class FinishOrderService {
    private final RestTemplate restTemplate;

    public ResponseEntity<String> finishOrder(Long order) {

        HttpHeaders header= new HttpHeaders();
        header.add("Authorization", "*****************");
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.add("Accept", "application/json");

        String uri = String.format("https://change-this/order/%d", order);

        return restTemplate.postForEntity(
                uri,
                null,
                String.class
        );
    }

}