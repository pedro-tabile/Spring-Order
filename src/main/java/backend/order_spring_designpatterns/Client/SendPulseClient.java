package backend.order_spring_designpatterns.Client;

import backend.order_spring_designpatterns.DTO.Request.AccessTokenRequestDTO;
import backend.order_spring_designpatterns.DTO.Request.EmailRequestDTO;
import backend.order_spring_designpatterns.DTO.Response.AccessTokenResponseDTO;
import backend.order_spring_designpatterns.DTO.Response.EmailResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "send-email-smtp", url = "https://api.sendpulse.com/")
// Interface que representa um Client para consumo da api externa - a implementação é realizada pelo Spring
public interface SendPulseClient {
    @PostMapping(value = "oauth/access_token")
    AccessTokenResponseDTO getAuthorization(@RequestBody AccessTokenRequestDTO accessTokenRequestDTO);

    @PostMapping(value = "smtp/emails")
    EmailResponseDTO sendEmail(@RequestBody EmailRequestDTO emailRequestDTO, @RequestHeader("Authorization") String token);
}
