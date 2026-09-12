package backend.order_spring_designpatterns.DTO.Request;

// Record responsável por definir as informações personalizadas dos emails enviados
public record PersonalizationEmailRequestDTO(String email, PersonalizationDataRequestDTO data) {
    
}
