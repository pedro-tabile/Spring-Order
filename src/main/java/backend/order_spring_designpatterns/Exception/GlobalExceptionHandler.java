package backend.order_spring_designpatterns.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;

// Classe responsável por definir o tratamento global de exceções
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Define a exibição de resposta HTTP para solicitação de registro com username que já está em uso
    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public ResponseEntity handleUsernameException(UsernameAlreadyInUseException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Erro gerado caso o id informado não exista na tabela
    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity handleIdException(IdNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Define a exibição de resposta HTTP para erros de validação, contendo o campo incorreto e a mensagem
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException ex){
        HashMap<String, String> errorsMessage = new HashMap<>();
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        errors.forEach(error -> errorsMessage.put(((FieldError) error).getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMessage);
    }
}
