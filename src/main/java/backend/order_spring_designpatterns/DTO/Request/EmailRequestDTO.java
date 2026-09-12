package backend.order_spring_designpatterns.DTO.Request;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

// Record responsável por definir as informações padrão dos emails enviados
public record EmailRequestDTO(
        FromToRequestDTO from,
        List<FromToRequestDTO> to,
        String subject,
        String template_id,
        List<PersonalizationEmailRequestDTO> personalization
) {
    @Value("${ADDRESS_EMAIL}")
    private static String addressEmail;

    private static final FromToRequestDTO fromEmail = new FromToRequestDTO(
            "Teste Orders SMTP",
            addressEmail
    );

    private static final String subjectMessage = "Novo pedido registrado vinculado ao seu email - Spring Orders";

    @Value("${TEMPLATE_ID_EMAIL}")
    private static String templateId;

    public EmailRequestDTO(FromToRequestDTO to, PersonalizationEmailRequestDTO personalization) {
        this(
                fromEmail,
                List.of(to),
                subjectMessage,
                templateId,
                List.of(personalization)
        );
    }
}