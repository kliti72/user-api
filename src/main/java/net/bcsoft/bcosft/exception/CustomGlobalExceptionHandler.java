package net.bcsoft.bcosft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URISyntaxException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> captureBadRequestException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFound.class})
    public ResponseEntity<String> captureNotFoundException(Exception exception){
       return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoContentException.class})
    public ResponseEntity<String> captureNoContentException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({InternalException.class})
    public ResponseEntity<String> captureInternalException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<String>captureConflictException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

}
