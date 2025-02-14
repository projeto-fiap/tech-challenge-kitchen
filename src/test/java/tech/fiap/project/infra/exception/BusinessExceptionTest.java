// package tech.fiap.project.infra.exception;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.http.HttpStatus;
//
// import java.util.MissingResourceException;
// import java.util.ResourceBundle;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// class BusinessExceptionTest {
//
// @Test
// void shouldCreateExceptionWithFormattedMessage() {
// String ERROR_KEY = "test.exception";
// String EXPECTED_MESSAGE = "Teste Exception";
//
// BusinessException exception = new BusinessException(ERROR_KEY, HttpStatus.BAD_REQUEST,
// null);
//
// assertEquals(EXPECTED_MESSAGE, exception.getMessage());
// assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatusCode());
// assertNull(exception.getMetadata());
// }
//
// @Test
// void shouldCreateExceptionWithMetadata() {
// String ERROR_KEY = "test.exception";
// String EXPECTED_MESSAGE = "Teste Exception";
//
// Object metadata = "Additional error details";
// BusinessException exception = new BusinessException(ERROR_KEY, HttpStatus.NOT_FOUND,
// metadata);
//
// assertEquals(EXPECTED_MESSAGE, exception.getMessage());
// assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatusCode());
// assertEquals(metadata, exception.getMetadata());
// }
//
// @Test
// void shouldCreateExceptionWithCause() {
// String ERROR_KEY = "test.exception";
// String EXPECTED_MESSAGE = "Teste Exception";
//
// Throwable cause = new RuntimeException("Cause of the error");
// BusinessException exception = new BusinessException(ERROR_KEY, cause);
//
// assertEquals(EXPECTED_MESSAGE, exception.getMessage());
// assertEquals(cause, exception.getCause());
// assertNull(exception.getHttpStatusCode());
// assertNull(exception.getMetadata());
// }
//
// @Test
// void shouldHandleMissingResourceKeyGracefully() {
// String invalidKey = "non.existing.key";
//
// assertThrows(MissingResourceException.class, () -> {
// new BusinessException(invalidKey, HttpStatus.BAD_REQUEST, null);
// });
// }
//
// }
