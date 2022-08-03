package me.saransh13.authorizationserver.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auth0.jwt.exceptions.TokenExpiredException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import javax.persistence.NoResultException;

import me.saransh13.authorizationserver.model.HttpErrorResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;

class ExceptionHandlingTest {
    /**
     * Method under test: {@link ExceptionHandling#accountDisabledException()}
     */
    @Test
    void testAccountDisabledException() {
        ResponseEntity<HttpErrorResponse> actualAccountDisabledExceptionResult = (new ExceptionHandling())
                .accountDisabledException();
        assertTrue(actualAccountDisabledExceptionResult.hasBody());
        assertTrue(actualAccountDisabledExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualAccountDisabledExceptionResult.getStatusCode());
        HttpErrorResponse body = actualAccountDisabledExceptionResult.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, body.getHttpStatus());
        assertEquals(400, body.getStatus());
        assertEquals("YOUR ACCOUNT HAS BEEN DISABLED. IF THIS IS AN ERROR, PLEASE CONTACT ADMINISTRATION",
                body.getMessage());
        assertEquals("BAD REQUEST", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#badCredentialsException()}
     */
    @Test
    void testBadCredentialsException() {
        ResponseEntity<HttpErrorResponse> actualBadCredentialsExceptionResult = (new ExceptionHandling())
                .badCredentialsException();
        assertTrue(actualBadCredentialsExceptionResult.hasBody());
        assertTrue(actualBadCredentialsExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualBadCredentialsExceptionResult.getStatusCode());
        HttpErrorResponse body = actualBadCredentialsExceptionResult.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, body.getHttpStatus());
        assertEquals(400, body.getStatus());
        assertEquals("USERNAME / PASSWORD INCORRECT. PLEASE TRY AGAIN", body.getMessage());
        assertEquals("BAD REQUEST", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#badJwtException()}
     */
    @Test
    void testBadJwtException() {
        ResponseEntity<HttpErrorResponse> actualBadJwtExceptionResult = (new ExceptionHandling()).badJwtException();
        assertTrue(actualBadJwtExceptionResult.hasBody());
        assertTrue(actualBadJwtExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualBadJwtExceptionResult.getStatusCode());
        HttpErrorResponse body = actualBadJwtExceptionResult.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, body.getHttpStatus());
        assertEquals(400, body.getStatus());
        assertEquals("BAD TOKEN PROVODED!", body.getMessage());
        assertEquals("BAD REQUEST", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#accessDeniedException()}
     */
    @Test
    void testAccessDeniedException() {
        ResponseEntity<HttpErrorResponse> actualAccessDeniedExceptionResult = (new ExceptionHandling())
                .accessDeniedException();
        assertTrue(actualAccessDeniedExceptionResult.hasBody());
        assertTrue(actualAccessDeniedExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.FORBIDDEN, actualAccessDeniedExceptionResult.getStatusCode());
        HttpErrorResponse body = actualAccessDeniedExceptionResult.getBody();
        assertEquals(HttpStatus.FORBIDDEN, body.getHttpStatus());
        assertEquals(403, body.getStatus());
        assertEquals("YOU DO NOT HAVE ENOUGH PERMISSION", body.getMessage());
        assertEquals("FORBIDDEN", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#lockedException()}
     */
    @Test
    void testLockedException() {
        ResponseEntity<HttpErrorResponse> actualLockedExceptionResult = (new ExceptionHandling()).lockedException();
        assertTrue(actualLockedExceptionResult.hasBody());
        assertTrue(actualLockedExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.UNAUTHORIZED, actualLockedExceptionResult.getStatusCode());
        HttpErrorResponse body = actualLockedExceptionResult.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED, body.getHttpStatus());
        assertEquals(401, body.getStatus());
        assertEquals("YOUR ACCOUNT HAS BEEN LOCKED. PLEASE CONTACT ADMINISTRATION", body.getMessage());
        assertEquals("UNAUTHORIZED", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#tokenExpiredException(TokenExpiredException)}
     */
    @Test
    void testTokenExpiredException() {
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<HttpErrorResponse> actualTokenExpiredExceptionResult = exceptionHandling.tokenExpiredException(
                new TokenExpiredException("An error occurred", atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        assertTrue(actualTokenExpiredExceptionResult.hasBody());
        assertTrue(actualTokenExpiredExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.UNAUTHORIZED, actualTokenExpiredExceptionResult.getStatusCode());
        HttpErrorResponse body = actualTokenExpiredExceptionResult.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED, body.getHttpStatus());
        assertEquals(401, body.getStatus());
        assertEquals("AN ERROR OCCURRED", body.getMessage());
        assertEquals("UNAUTHORIZED", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#tokenExpiredException(TokenExpiredException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTokenExpiredException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.auth0.jwt.exceptions.TokenExpiredException.getMessage()" because "exception" is null
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.tokenExpiredException(ExceptionHandling.java:72)
        //   In order to prevent tokenExpiredException(TokenExpiredException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   tokenExpiredException(TokenExpiredException).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ExceptionHandling()).tokenExpiredException(null);
    }

    /**
     * Method under test: {@link ExceptionHandling#emailExistException(EmailExistException)}
     */
    @Test
    void testEmailExistException() {
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        ResponseEntity<HttpErrorResponse> actualEmailExistExceptionResult = exceptionHandling
                .emailExistException(new EmailExistException("An error occurred"));
        assertTrue(actualEmailExistExceptionResult.hasBody());
        assertTrue(actualEmailExistExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualEmailExistExceptionResult.getStatusCode());
        HttpErrorResponse body = actualEmailExistExceptionResult.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, body.getHttpStatus());
        assertEquals(400, body.getStatus());
        assertEquals("AN ERROR OCCURRED", body.getMessage());
        assertEquals("BAD REQUEST", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#emailExistException(EmailExistException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEmailExistException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "me.saransh13.authorizationserver.exception.EmailExistException.getMessage()" because "exception" is null
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.emailExistException(ExceptionHandling.java:77)
        //   In order to prevent emailExistException(EmailExistException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   emailExistException(EmailExistException).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ExceptionHandling()).emailExistException(null);
    }

    /**
     * Method under test: {@link ExceptionHandling#userNotFoundException(UserNotFoundException)}
     */
    @Test
    void testUserNotFoundException() {
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        ResponseEntity<HttpErrorResponse> actualUserNotFoundExceptionResult = exceptionHandling
                .userNotFoundException(new UserNotFoundException("An error occurred"));
        assertTrue(actualUserNotFoundExceptionResult.hasBody());
        assertTrue(actualUserNotFoundExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualUserNotFoundExceptionResult.getStatusCode());
        HttpErrorResponse body = actualUserNotFoundExceptionResult.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, body.getHttpStatus());
        assertEquals(400, body.getStatus());
        assertEquals("AN ERROR OCCURRED", body.getMessage());
        assertEquals("BAD REQUEST", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#userNotFoundException(UserNotFoundException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUserNotFoundException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "me.saransh13.authorizationserver.exception.UserNotFoundException.getMessage()" because "exception" is null
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.userNotFoundException(ExceptionHandling.java:83)
        //   In order to prevent userNotFoundException(UserNotFoundException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   userNotFoundException(UserNotFoundException).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ExceptionHandling()).userNotFoundException(null);
    }

    /**
     * Method under test: {@link ExceptionHandling#methodNotSupportedException(HttpRequestMethodNotSupportedException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMethodNotSupportedException() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:208)
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.methodNotSupportedException(ExceptionHandling.java:88)
        //   In order to prevent methodNotSupportedException(HttpRequestMethodNotSupportedException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   methodNotSupportedException(HttpRequestMethodNotSupportedException).
        //   See https://diff.blue/R013 to resolve this issue.

        ExceptionHandling exceptionHandling = new ExceptionHandling();
        exceptionHandling
                .methodNotSupportedException(new HttpRequestMethodNotSupportedException("https://example.org/example"));
    }

    /**
     * Method under test: {@link ExceptionHandling#methodNotSupportedException(HttpRequestMethodNotSupportedException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMethodNotSupportedException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.HttpRequestMethodNotSupportedException.getSupportedHttpMethods()" because "exception" is null
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.methodNotSupportedException(ExceptionHandling.java:88)
        //   In order to prevent methodNotSupportedException(HttpRequestMethodNotSupportedException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   methodNotSupportedException(HttpRequestMethodNotSupportedException).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ExceptionHandling()).methodNotSupportedException(null);
    }

    /**
     * Method under test: {@link ExceptionHandling#methodNotSupportedException(HttpRequestMethodNotSupportedException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMethodNotSupportedException3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.HashMap$HashIterator.nextNode(HashMap.java:1599)
        //       at java.util.HashMap$KeyIterator.next(HashMap.java:1620)
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.methodNotSupportedException(ExceptionHandling.java:88)
        //   In order to prevent methodNotSupportedException(HttpRequestMethodNotSupportedException)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   methodNotSupportedException(HttpRequestMethodNotSupportedException).
        //   See https://diff.blue/R013 to resolve this issue.

        ExceptionHandling exceptionHandling = new ExceptionHandling();
        HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException = mock(
                HttpRequestMethodNotSupportedException.class);
        when(httpRequestMethodNotSupportedException.getSupportedHttpMethods()).thenReturn(new HashSet<>());
        exceptionHandling.methodNotSupportedException(httpRequestMethodNotSupportedException);
    }

    /**
     * Method under test: {@link ExceptionHandling#methodNotSupportedException(HttpRequestMethodNotSupportedException)}
     */
    @Test
    void testMethodNotSupportedException4() {
        ExceptionHandling exceptionHandling = new ExceptionHandling();

        HashSet<HttpMethod> httpMethodSet = new HashSet<>();
        httpMethodSet.add(HttpMethod.GET);
        HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException = mock(
                HttpRequestMethodNotSupportedException.class);
        when(httpRequestMethodNotSupportedException.getSupportedHttpMethods()).thenReturn(httpMethodSet);
        ResponseEntity<HttpErrorResponse> actualMethodNotSupportedExceptionResult = exceptionHandling
                .methodNotSupportedException(httpRequestMethodNotSupportedException);
        assertTrue(actualMethodNotSupportedExceptionResult.hasBody());
        assertTrue(actualMethodNotSupportedExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, actualMethodNotSupportedExceptionResult.getStatusCode());
        HttpErrorResponse body = actualMethodNotSupportedExceptionResult.getBody();
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, body.getHttpStatus());
        assertEquals(405, body.getStatus());
        assertEquals("THIS REQUEST METHOD IS NOT ALLOWED ON THIS ENDPOINT. PLEASE SEND A 'GET' REQUEST", body.getMessage());
        assertEquals("METHOD NOT ALLOWED", body.getReason());
        verify(httpRequestMethodNotSupportedException).getSupportedHttpMethods();
    }

    /**
     * Method under test: {@link ExceptionHandling#internalServerErrorException(Exception)}
     */
    @Test
    void testInternalServerErrorException() {
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        ResponseEntity<HttpErrorResponse> actualInternalServerErrorExceptionResult = exceptionHandling
                .internalServerErrorException(new Exception("An error occurred"));
        assertTrue(actualInternalServerErrorExceptionResult.hasBody());
        assertTrue(actualInternalServerErrorExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualInternalServerErrorExceptionResult.getStatusCode());
        HttpErrorResponse body = actualInternalServerErrorExceptionResult.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, body.getHttpStatus());
        assertEquals(500, body.getStatus());
        assertEquals("AN ERROR OCCURRED WHILE PROCESSING THE REQUEST", body.getMessage());
        assertEquals("INTERNAL SERVER ERROR", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#internalServerErrorException(Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInternalServerErrorException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Exception.getMessage()" because "exception" is null
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.internalServerErrorException(ExceptionHandling.java:94)
        //   In order to prevent internalServerErrorException(Exception)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   internalServerErrorException(Exception).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ExceptionHandling()).internalServerErrorException(null);
    }

    /**
     * Method under test: {@link ExceptionHandling#notFoundException(NoResultException)}
     */
    @Test
    void testNotFoundException() {
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        ResponseEntity<HttpErrorResponse> actualNotFoundExceptionResult = exceptionHandling
                .notFoundException(new NoResultException("An error occurred"));
        assertTrue(actualNotFoundExceptionResult.hasBody());
        assertTrue(actualNotFoundExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.NOT_FOUND, actualNotFoundExceptionResult.getStatusCode());
        HttpErrorResponse body = actualNotFoundExceptionResult.getBody();
        assertEquals(HttpStatus.NOT_FOUND, body.getHttpStatus());
        assertEquals(404, body.getStatus());
        assertEquals("AN ERROR OCCURRED", body.getMessage());
        assertEquals("NOT FOUND", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#notFoundException(NoResultException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNotFoundException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.toUpperCase()" because "message" is null
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.createHttpResponse(ExceptionHandling.java:114)
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.notFoundException(ExceptionHandling.java:101)
        //   In order to prevent notFoundException(NoResultException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   notFoundException(NoResultException).
        //   See https://diff.blue/R013 to resolve this issue.

        ExceptionHandling exceptionHandling = new ExceptionHandling();
        exceptionHandling.notFoundException(new NoResultException());
    }

    /**
     * Method under test: {@link ExceptionHandling#notFoundException(NoResultException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNotFoundException3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javax.persistence.NoResultException.getMessage()" because "exception" is null
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.notFoundException(ExceptionHandling.java:100)
        //   In order to prevent notFoundException(NoResultException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   notFoundException(NoResultException).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ExceptionHandling()).notFoundException(null);
    }

    /**
     * Method under test: {@link ExceptionHandling#iOException(IOException)}
     */
    @Test
    void testIOException() {
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        ResponseEntity<HttpErrorResponse> actualIOExceptionResult = exceptionHandling
                .iOException(new IOException("An error occurred"));
        assertTrue(actualIOExceptionResult.hasBody());
        assertTrue(actualIOExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualIOExceptionResult.getStatusCode());
        HttpErrorResponse body = actualIOExceptionResult.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, body.getHttpStatus());
        assertEquals(500, body.getStatus());
        assertEquals("ERROR OCCURRED WHILE PROCESSING FILE", body.getMessage());
        assertEquals("INTERNAL SERVER ERROR", body.getReason());
    }

    /**
     * Method under test: {@link ExceptionHandling#iOException(IOException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIOException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.io.IOException.getMessage()" because "exception" is null
        //       at me.saransh13.authorizationserver.exception.ExceptionHandling.iOException(ExceptionHandling.java:106)
        //   In order to prevent iOException(IOException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   iOException(IOException).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ExceptionHandling()).iOException(null);
    }

    /**
     * Method under test: {@link ExceptionHandling#notFound404()}
     */
    @Test
    void testNotFound404() {
        ResponseEntity<HttpErrorResponse> actualNotFound404Result = (new ExceptionHandling()).notFound404();
        assertTrue(actualNotFound404Result.hasBody());
        assertTrue(actualNotFound404Result.getHeaders().isEmpty());
        assertEquals(HttpStatus.NOT_FOUND, actualNotFound404Result.getStatusCode());
        HttpErrorResponse body = actualNotFound404Result.getBody();
        assertEquals(HttpStatus.NOT_FOUND, body.getHttpStatus());
        assertEquals(404, body.getStatus());
        assertEquals("THERE IS NO MAPPING FOR THIS URL", body.getMessage());
        assertEquals("NOT FOUND", body.getReason());
    }
}

