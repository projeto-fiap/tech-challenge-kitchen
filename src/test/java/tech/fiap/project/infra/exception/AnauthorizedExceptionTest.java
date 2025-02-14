// package tech.fiap.project.infra.exception;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.http.HttpStatus;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// class UnauthorizedExceptionTest {
//
// @Test
// void shouldCreateExceptionWithFormattedMessageAndHttpStatus() {
// // Arrange
// String key = "not.permission";
// HttpStatus httpStatus = HttpStatus.FORBIDDEN;
// String expectedMessage = "You do not have permission to access this resource.";
// Throwable cause = new RuntimeException("Unauthorized access");
//
// // Act
// UnauthorizedException exception = new UnauthorizedException(key, cause);
//
// // Assert
// assertEquals(expectedMessage, exception.getMessage());
// assertNull(exception.getMetadata());
// }
//
// @Test
// void shouldCreateExceptionWithFormattedMessageAndCause() {
// // Arrange
// Throwable cause = new RuntimeException("Unauthorized access");
//
// // Act
// UnauthorizedException exception = new UnauthorizedException("not.permission", cause);
//
// // Assert
// assertEquals("You do not have permission to access this resource.",
// exception.getMessage());
// assertEquals(cause, exception.getCause());
// assertNull(exception.getHttpStatusCode());
// assertNull(exception.getMetadata());
// }
//
// @Test
// void shouldCreateExceptionWithHttpStatusOnly() {
// // Arrange
// HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
// String expectedMessage = "You do not have permission to access this resource.";
//
// // Act
// UnauthorizedException exception = new UnauthorizedException(httpStatus);
//
// // Assert
// assertEquals(expectedMessage, exception.getMessage());
// assertEquals(httpStatus, exception.getHttpStatusCode());
// assertNull(exception.getMetadata());
// }
//
// @Test
// void shouldCreateException() {
// // Arrange
// HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
// String expectedMessage = "You do not have permission to access this resource.";
//
// UnauthorizedException exception = new UnauthorizedException("not.permission",
// httpStatus, null, "Args");
//
// // Assert
// assertEquals(expectedMessage, exception.getMessage());
// assertEquals(httpStatus, exception.getHttpStatusCode());
// assertNull(exception.getMetadata());
// }
//
// }
