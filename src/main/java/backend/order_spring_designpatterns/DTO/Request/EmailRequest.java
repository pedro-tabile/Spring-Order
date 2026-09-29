package backend.order_spring_designpatterns.DTO.Request;

import java.util.List;

// Record responsável por definir as informações padrão dos emails enviados
public record EmailRequest(
        FromToRequest from,
        List<FromToRequest> to,
        String subject,
        String template_id,
        List<PersonalizationEmailRequest> personalization
) {

    public EmailRequest(FromToRequest to, FromToRequest sender, String subjectMessage,
                        String templateId, PersonalizationEmailRequest personalization) {
        this(
                sender,
                List.of(to),
                subjectMessage,
                templateId,
                List.of(personalization)
        );
    }
}