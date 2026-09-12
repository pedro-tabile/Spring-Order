package backend.order_spring_designpatterns.DTO.Request;

import java.util.List;

// Record responsável por definir as informações padrão dos emails enviados
public record EmailRequestDTO(
        FromToRequestDTO from,
        List<FromToRequestDTO> to,
        String subject,
        String templateId,
        List<PersonalizationEmailRequestDTO> personalization
) {
    private static final FromToRequestDTO fromEmail = new FromToRequestDTO(
            "Teste_Orders_SMTP",
            "MS_ojT0Od@test-68zxl27d3em4j905.mlsender.net" //TODO env
    );

    private static final String subjectMessage = "Novo pedido registrado vinculado ao seu email - Spring Orders";

    private static final String template = "pr9084znqjj4w63d"; //TODO env

    public EmailRequestDTO(FromToRequestDTO to, PersonalizationEmailRequestDTO personalization) {
        this(
                fromEmail,
                List.of(to),
                subjectMessage,
                template,
                List.of(personalization)
        );
    }
}