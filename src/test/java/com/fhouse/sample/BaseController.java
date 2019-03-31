package com.fhouse.sample;

import com.fhouse.sample.settings.exceptions.DefaultExceptionAttributes;
import com.fhouse.sample.settings.exceptions.ExceptionAttributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Base of all controllers
 */
@Slf4j
public class BaseController {


    /**
     * Handles JPA NoResultExceptions thrown from web service controller
     * methods. Creates a response with Exception Attributes as JSON and HTTP
     * status code 404, not found.
     *
     * @param noResultException A NoResultException instance.
     * @param request The HttpServletRequest in which the NoResultException was
     *        raised.
     * @return A ResponseEntity containing the Exception Attributes in the body
     *         and HTTP status code 404.
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String, Object>> handleNoResultException(
            NoResultException noResultException, HttpServletRequest request) {

        log.info("> handleNoResultException");

        ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

        Map<String, Object> responseBody = exceptionAttributes
                .getExceptionAttributes(noResultException, request,
                        HttpStatus.NOT_FOUND);

        log.info("< handleNoResultException");
        return new ResponseEntity<Map<String, Object>>(responseBody,
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all Exceptions not addressed by more specific
     * <code>@ExceptionHandler</code> methods. Creates a response with the
     * Exception Attributes in the response body as JSON and a HTTP status code
     * of 500, internal server error.
     *
     * @param exception An Exception instance.
     * @param request The HttpServletRequest in which the Exception was raised.
     * @return A ResponseEntity containing the Exception Attributes in the body
     *         and a HTTP status code 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(
            Exception exception, HttpServletRequest request) {

        log.error("> handleException");
        log.error("- Exception: ", exception);

        ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

        Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);

        log.error("< handleException");
        return new ResponseEntity<Map<String, Object>>(responseBody,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
