package backend.order_spring_designpatterns.DTO.Request;

// Record responsável por definir as informações personalizadas dos emails enviados
public record PersonalizationEmailRequest(String email, PersonalizationDataRequest data) {
    
}
