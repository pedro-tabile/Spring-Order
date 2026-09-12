package backend.order_spring_designpatterns.Client;

import backend.order_spring_designpatterns.DTO.Request.EmailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "mailersend-email-smtp", url = "https://api.mailersend.com/")
// Interface que representa um Client para consumo da api externa - a implementação é realizada pelo Spring
public interface MailerSendClient {
    @PostMapping(value = "v1/email")
    ResponseEntity<Void> sendEmail(@RequestBody EmailRequestDTO email, @RequestHeader("Authorization") String token);
}