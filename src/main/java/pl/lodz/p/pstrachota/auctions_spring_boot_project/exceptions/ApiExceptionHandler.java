package pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiException apiException =
                new ApiException("INVALID ARGUMENT PASSED", HttpStatus.BAD_REQUEST,
                        errors, LocalDateTime.now());

        return handleExceptionInternal(ex, apiException, headers, apiException.getHttpStatus(),
                request);
    }
    @ExceptionHandler(IncorrectDateException.class)
    public final ResponseEntity<Object> handleIncorrectDateException(IncorrectDateException ex,
                                                                     WebRequest request) {
        ApiException apiException =
                new ApiException(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,
                        List.of(request.getDescription(false)), LocalDateTime.now());

        return new ResponseEntity<>(apiException, new HttpHeaders(), apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return new ResponseEntity<>(new ApiException(ex.getLocalizedMessage(), HttpStatus.METHOD_NOT_ALLOWED,
                List.of(request.getDescription(false)), LocalDateTime.now()), new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NumberFormatException.class)
    public final ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex,
                                                                    WebRequest request) {
        ApiException apiException =
                new ApiException(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,
                        List.of(request.getDescription(false)), LocalDateTime.now());

        return new ResponseEntity<>(apiException, new HttpHeaders(), apiException.getHttpStatus());
    }

    @ExceptionHandler(IncorrectPriceException.class)
    public final ResponseEntity<Object> handleIncorrectPriceException(IncorrectPriceException ex,
                                                                      WebRequest request) {
        ApiException apiException =
                new ApiException(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,
                        List.of(request.getDescription(false)), LocalDateTime.now());

        return new ResponseEntity<>(apiException, new HttpHeaders(), apiException.getHttpStatus());
    }

    @ExceptionHandler(IncorrectOperationException.class)
    public final ResponseEntity<Object> handleIncorrectOperationException(IncorrectOperationException ex,
                                                                          WebRequest request) {
        ApiException apiException =
                new ApiException(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,
                        List.of(request.getDescription(false)), LocalDateTime.now());

        return new ResponseEntity<>(apiException, new HttpHeaders(), apiException.getHttpStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex,
                                                                WebRequest request) {
        ApiException apiException =
                new ApiException(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND,
                        List.of(request.getDescription(false)), LocalDateTime.now());

        return new ResponseEntity<>(apiException, new HttpHeaders(), apiException.getHttpStatus());
    }

    @ExceptionHandler(IncorrectAuctionTypeException.class)
    public final ResponseEntity<Object> handleIncorrectAuctionTypeException(IncorrectAuctionTypeException ex,
                                                                            WebRequest request) {
        ApiException apiException =
                new ApiException(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,
                        List.of(request.getDescription(false)), LocalDateTime.now());

        return new ResponseEntity<>(apiException, new HttpHeaders(), apiException.getHttpStatus());
    }

}
