package backend.order_spring_designpatterns.Client;

import backend.order_spring_designpatterns.DTO.Request.AccessTokenRequestDTO;
import backend.order_spring_designpatterns.DTO.Request.EmailRequestDTO;
import backend.order_spring_designpatterns.DTO.Response.AccessTokenResponseDTO;
import backend.order_spring_designpatterns.DTO.Response.EmailResponseDTO;
import backend.order_spring_designpatterns.Service.SendPulseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "send-email-smtp", url = "https://api.sendpulse.com/")
// Classe que representa um Client para consumo da api externa
public class SendPulseClient {
    @Autowired
    private SendPulseService sendPulseService;

    @PostMapping(value = "oauth/access_token")
    AccessTokenResponseDTO getAuthorization(@RequestBody AccessTokenRequestDTO accessTokenRequestDTO){

        return null;
    };

    @PostMapping(value = "smtp/emails")
    EmailResponseDTO sendEmail(@RequestBody EmailRequestDTO emailRequestDTO,
                               @RequestHeader("Authorization") String token){

        return null;
    };
}
