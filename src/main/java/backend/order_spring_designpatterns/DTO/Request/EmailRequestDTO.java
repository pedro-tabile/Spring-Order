package backend.order_spring_designpatterns.DTO.Request;

import java.util.List;

// Record responsável por definir as informações padrão dos emails enviados
public record EmailRequestDTO(
        FromToRequestDTO from,
        List<FromToRequestDTO> to,
        String subject,
        String template_id,
        List<PersonalizationEmailRequestDTO> personalization
) {

    public EmailRequestDTO(FromToRequestDTO to, FromToRequestDTO sender, String subjectMessage,
                           String templateId, PersonalizationEmailRequestDTO personalization) {
        this(
                sender,
                List.of(to),
                subjectMessage,
                templateId,
                List.of(personalization)
        );
    }
}