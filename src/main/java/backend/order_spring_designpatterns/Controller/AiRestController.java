package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.OrderRequest;
import backend.order_spring_designpatterns.DTO.Response.OrderResponse;
import backend.order_spring_designpatterns.Exception.InvalidAudioFile;
import backend.order_spring_designpatterns.Service.ChatClientService;
import backend.order_spring_designpatterns.Service.SpeechToTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController()// Usa-se para indicar o retorno de dados no corpo da resposta HTTP/web
@RequestMapping("/ai")
// Classe responsável pelo controle de requisições e respostas da API para operações diretas com uso de IA
public class AiRestController {
    @Autowired
    private ChatClientService genAiService;
    @Autowired
    private SpeechToTextService speechToTextService;

    @GetMapping("/text-to-orderJson")
    public ResponseEntity<OrderRequest> getOrderJson(@RequestParam String text){
        OrderRequest jsonResponse = genAiService.generateResponse(text);
        return ResponseEntity.ok().body(jsonResponse);
    }

    @PostMapping(value = "/audio-to-orderJson", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<OrderRequest> getOrderJsonByAudio(@RequestParam("audio") MultipartFile file){
        if (file.isEmpty() || file.getContentType() == null || !file.getContentType().startsWith("audio/")){
            throw new InvalidAudioFile();
        }

        String transcription = speechToTextService.transcription(file);
        OrderRequest jsonResponse = genAiService.generateResponse(transcription);

        return ResponseEntity.ok().body(jsonResponse);
    }

    @PostMapping(value = "/audio-to-text", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> getOrderPrompt(@RequestParam("audio") MultipartFile file){
        if (file.isEmpty() || file.getContentType() == null || !file.getContentType().startsWith("audio/")){
            throw new InvalidAudioFile();
        }

        Map<String, String> response = new HashMap<>();

        String transcriptionPrompt = speechToTextService.transcription(file);
        response.put("transcription", transcriptionPrompt);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/save-order")
    public ResponseEntity<OrderResponse> saveOrder(@RequestParam String text){
        OrderResponse orderSavedJsonResponse = genAiService.saveOrder(text);
        return ResponseEntity.ok().body(orderSavedJsonResponse);
    }
}
