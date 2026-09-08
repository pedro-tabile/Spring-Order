package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.Client.SendPulseClient;
import backend.order_spring_designpatterns.DTO.Request.AccessTokenRequestDTO;
import backend.order_spring_designpatterns.DTO.Request.EmailRequestDTO;
import backend.order_spring_designpatterns.DTO.Response.AccessTokenResponseDTO;
import backend.order_spring_designpatterns.DTO.Response.EmailResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/* Classe que define o envio de emails */
@Service
public class SendPulseService {
    @Autowired
    private SendPulseClient sendPulseClient;

    @Value("${SENDPULSE_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${SENDPULSE_CLIENT_ID}")
    private String clientId;

    public AccessTokenResponseDTO getAccessToken() {
        AccessTokenRequestDTO dataRequest = new AccessTokenRequestDTO("client_credentials", clientId, clientSecret);
        return sendPulseClient.getAuthorization(dataRequest);
    }

    public EmailResponseDTO sendEmail(EmailRequestDTO emailRequestDTO) {
        AccessTokenResponseDTO authorization = getAccessToken();
        return sendPulseClient.sendEmail(emailRequestDTO, "Bearer " + authorization.access_token());
    }
}
