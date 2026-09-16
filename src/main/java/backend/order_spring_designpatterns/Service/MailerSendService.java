package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.Client.MailerSendClient;
import backend.order_spring_designpatterns.DTO.Request.EmailRequestDTO;
import backend.order_spring_designpatterns.Exception.MailerSendMailNotValid;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/* Classe que define as regras para serviço de envio de emails */
@Service
public class MailerSendService {
    @Autowired
    private MailerSendClient mailerSendClient;

    @Value("${MAILERSEND_SMTP_TOKEN}")
    private String token;

    public void sendEmail(EmailRequestDTO emailRequestDTO) {
        try {
            mailerSendClient.sendEmail(emailRequestDTO, "Bearer " + token);
        } catch (FeignException ex) {
            // 422 = Unprocessable Entity
            if (ex.status() == 422) {
                throw new MailerSendMailNotValid();
            }

            throw ex;
        }
    }
}
