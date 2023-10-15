package com.a1st.banking.handlers;

import com.a1st.banking.CustomException.ObjectValidationException;
import com.a1st.banking.CustomException.OperationNonPermittedException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMsg("Object not valid exception has occured!")
                .errorSource(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMsg(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(representation);
    }

    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMsg(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMsg("A user already exists with the provided Email")
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionRepresentation> handleDisabledException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMsg("You cannot access your account because it is not yet activated")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionRepresentation> handleBadCredentialsException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMsg("Your email and / or password is incorrect")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }

//    todo this is not working, I need to create  a way to handle exception for unauthorized access to certain pages that require authentication
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionRepresentation> AccessDeniedException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMsg("ACCESS DENIED!")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }


}
