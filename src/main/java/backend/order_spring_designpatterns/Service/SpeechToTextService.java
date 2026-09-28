package backend.order_spring_designpatterns.Service;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
/* Classe que define criação e serviços da IA de Speech-To-Text (STT) - Groq */
public class SpeechToTextService {
    @Autowired
    private OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    public String transcription(MultipartFile file) {
        // Propriedades também podem ser configuradas aqui:
         /*OpenAiAudioTranscriptionOptions transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .language("pt")
                .baseUrl("https://api.groq.com/openai/v1")
                ...
                .build();
        */

        Resource audioFile = file.getResource();
        AudioTranscriptionPrompt audioTranscriptionPrompt = new AudioTranscriptionPrompt(audioFile);
        AudioTranscriptionResponse transcriptionResponse = openAiAudioTranscriptionModel.call(audioTranscriptionPrompt);

        return transcriptionResponse.getResult().getOutput();
    }
}

// Definição de propriedades de IA de Transcription; Criação de service para transcription; Criação de endpoint para serviço de transcription (SST); Validação/Exceção para tipo de arquivo diferente de audio