//package tech.fiap.project.infra.exception;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class KitchenStatusExceptionTest {
//
//	@Test
//	void shouldCreateExceptionWithFormattedMessage() {
//		// Arrange
//		Long orderId = 12345L;
//		String expectedMessage = String.format("Order %d cannot proceed due to kitchen status.", orderId);
//
//		// Act
//		KitchenStatusException exception = new KitchenStatusException(orderId);
//
//		// Assert
//		assertEquals(expectedMessage, exception.getMessage());
//		assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatusCode());
//		assertNull(exception.getMetadata());
//	}
//
//}
