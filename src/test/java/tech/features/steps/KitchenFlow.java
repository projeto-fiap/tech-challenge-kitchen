package tech.features.steps;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.service.kitchen.KitchenService;

@SpringBootTest
public class KitchenFlow {

	@Autowired
	private KitchenService kitchenService;

	private KitchenDTO kitchenDTO;

	private Long pedidoId;

	@Given("Cria um pedido na cozinha")
	public void createKitchenOrder() {
		pedidoId = 1L;
		kitchenDTO = kitchenService.create(pedidoId)
				.orElseThrow(() -> new IllegalStateException("Pedido não foi criado corretamente"));
	}

	@When("ele passa o id {long}")
	public void givenId(Long id) {
		pedidoId = id;
	}

	@Then("deve criar o pedido AWAITING_PRODUCTION")
	public void KitchenStatus_AWAITING_PRODUCTION() {
		assertEquals("AWAITING_PRODUCTION", kitchenDTO.getStatus().toString(), "O pedido não foi criado corretamente!");
	}

	@Given("Um pedido valido em AWAITING_PRODUCTION")
	public void umPedidoValidoEmAWAITING_PRODUCTION() {
		pedidoId = 1L;
		kitchenDTO = kitchenService.create(pedidoId)
				.orElseThrow(() -> new IllegalStateException("Pedido não foi criado corretamente"));
		assertEquals("AWAITING_PRODUCTION", kitchenDTO.getStatus().toString());
	}

	@Then("deve mover o pedido para PRODUCTION")
	public void deveMoverOPedidoParaPRODUCTION() {
		kitchenDTO = kitchenService.setInProduction(pedidoId)
				.orElseThrow(() -> new IllegalStateException("Pedido não foi movido para PRODUCTION"));
		assertEquals("IN_PRODUCTION", kitchenDTO.getStatus().toString(),
				"O pedido não foi movido corretamente para Production!");
	}

}
