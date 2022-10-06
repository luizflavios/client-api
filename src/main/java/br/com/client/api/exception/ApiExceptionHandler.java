package br.com.client.api.exception;

import br.com.client.api.enums.ErrorType;
import br.com.client.api.generic.GenericObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = GenericObjectError.builder().title(status.getReasonPhrase()).statusCode(status.value()).build();
        } else if (body instanceof String) {
            body = GenericObjectError.builder().title((String) body).build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorType errorType = ErrorType.BUSINESS_INTEGRITY;
        String detail = ex.getMessage();
        GenericObjectError error = createErrorBuilder(status, errorType, detail).userMessage(detail).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityException.class)
    public ResponseEntity<Object> handleEntityException(EntityException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorType errorType = ErrorType.DATABASE_INTEGRITY;
        String detail = ex.getMessage();
        GenericObjectError error = createErrorBuilder(status, errorType, detail).userMessage(detail).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotAllowedException.class)
    public ResponseEntity<Object> handleEntityNotAllowedException(EntityNotAllowedException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorType errorType = ErrorType.DATABASE_INTEGRITY;
        String detail = ex.getMessage();
        GenericObjectError error = createErrorBuilder(status, errorType, detail).userMessage(detail).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorType errorType = ErrorType.DATABASE_INTEGRITY;
        String detail = ex.getMessage();
        GenericObjectError error = createErrorBuilder(status, errorType, detail).userMessage(detail).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorType errorType = ErrorType.DATABASE_INTEGRITY;
        String detail = ex.getMessage();
        GenericObjectError error = createErrorBuilder(status, errorType, detail).userMessage(detail).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }


    private GenericObjectError.GenericObjectErrorBuilder createErrorBuilder(HttpStatus status, ErrorType errorType, String detail) {
        return GenericObjectError.builder().statusCode(status.value()).title(errorType.getTitle()).detail(detail)
                .dataHora(OffsetDateTime.now());
    }


}
