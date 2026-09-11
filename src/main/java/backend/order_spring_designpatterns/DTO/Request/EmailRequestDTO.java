package backend.order_spring_designpatterns.DTO.Request;

import java.util.List;

// Record responsável por definir as informações padrão dos emails enviados
public record EmailRequestDTO(
        FromToRequestDTO from,
        List<FromToRequestDTO> to,
        String subject,
        String text
) {

    public EmailRequestDTO(FromToRequestDTO to, String text){
        this(
                new FromToRequestDTO("Teste_Orders_SMTP", "MS_ojT0Od@test-68zxl27d3em4j905.mlsender.net"),
                List.of(to),
                "Novo pedido registrado",
                text
        );
    }
}
