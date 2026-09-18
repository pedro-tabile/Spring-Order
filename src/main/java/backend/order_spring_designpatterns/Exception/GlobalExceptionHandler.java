package backend.order_spring_designpatterns.Exception;

import backend.order_spring_designpatterns.DTO.Response.SendEmailErrorResponseDTO;
import backend.order_spring_designpatterns.Service.Enums.PaymentMethodsEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.InvalidFormatException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe responsável por definir o tratamento global de exceções
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Define a exibição de resposta HTTP para solicitação de registro com username que já está em uso
    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public ResponseEntity<String> handleUsernameException(UsernameAlreadyInUseException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Erro gerado caso o id informado não exista na tabela
    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<String> handleIdException(IdNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Define a exibição de resposta HTTP para erros de validação, contendo o campo incorreto e a mensagem
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errorsMessage = new HashMap<>();
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        errors.forEach(error -> errorsMessage.put(((FieldError) error).getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMessage);
    }

    // Erro gerado caso o serviço de envio de email do MailerSend seja interrompido devido a um email incorreto
    @ExceptionHandler(MailerSendMailNotValid.class)
    public ResponseEntity<SendEmailErrorResponseDTO> handleMailerSendEmailException(MailerSendMailNotValid ex){
        SendEmailErrorResponseDTO responseDTO = new SendEmailErrorResponseDTO("O pedido foi criado!", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Erro gerado caso a quantidade informada de um produto (em um pedido) seja maior que o estoque do mesmo
    @ExceptionHandler(StockLimitExceeded.class)
    public ResponseEntity<String> handleStockLimitExceededException(StockLimitExceeded ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Erro gerado em tentativa de login inválida
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleInvalidAutheticationException(AuthenticationException ex){
        Map<String, String> errorsMessage = new HashMap<>();
        errorsMessage.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorsMessage);
    }

    // Erro gerado ao informar método de pagamento inválido (opção escolhida não incluída no enum)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handlePaymentMethodNotValidException(HttpMessageNotReadableException ex){
        Map<String, String> errorMessage = new HashMap<>();

        if (ex.getCause() instanceof InvalidFormatException invalidFormat
                && invalidFormat.getTargetType() == PaymentMethodsEnum.class) {
            errorMessage.put("message", "Tipo de pagamento inválido! Opções: 'ESPECIE', 'DEBITO', 'CREDITO' ou 'PIX'");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        errorMessage.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
