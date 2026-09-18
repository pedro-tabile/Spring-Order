package backend.order_spring_designpatterns.Exception;

// Exceção lançada caso haja algum email inválido na requisição de envio de email
public class MailerSendMailNotValid extends RuntimeException {
    public MailerSendMailNotValid() {
        super("O email não pode ser enviado devido à presença de email de destinatário inválido!");
    }
}
