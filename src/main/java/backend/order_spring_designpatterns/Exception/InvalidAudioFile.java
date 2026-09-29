package backend.order_spring_designpatterns.Exception;

// Exceção lançada quando o arquivo enviado não é compatível a um arquivo de áudio.
public class InvalidAudioFile extends RuntimeException {
    public InvalidAudioFile() {
        super("Arquivo inválido para transcrição!");
    }
}
