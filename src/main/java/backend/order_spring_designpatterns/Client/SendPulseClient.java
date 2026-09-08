package backend.order_spring_designpatterns.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "send-email-smtp", url = "https://api.sendpulse.com/")
// Interface que representa um Client para consumo da api externa
public interface SendPulseClient {
    @PostMapping("smtp/emails")

}
