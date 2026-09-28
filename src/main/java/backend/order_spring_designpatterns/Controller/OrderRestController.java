package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.OrderRequest;
import backend.order_spring_designpatterns.DTO.Response.OrderResponse;
import backend.order_spring_designpatterns.DTO.Response.OrderResponseAi;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Exception.InvalidAudioFile;
import backend.order_spring_designpatterns.Service.ChatClientService;
import backend.order_spring_designpatterns.Service.OrderService;
import backend.order_spring_designpatterns.Service.SpeechToTextService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()// Usa-se para indicar o retorno de dados no corpo da resposta HTTP/web
@RequestMapping("/orders")
// Classe responsável pelo controle de requisições e respostas da API para operações com pedidos
public class OrderRestController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ChatClientService genAiService;
    @Autowired
    private SpeechToTextService speechToTextService;

    // Annotations Mapping (mapeiam para métodos Java) permitem tratar métodos HTTP como PUT, DELETE, CREATE e UPDATE.
    @GetMapping("/listAll")
    public ResponseEntity<List<OrderResponse>> findAll(){
        List<Order> orders = orderService.findAll();
        List<OrderResponse> ordersResponse = orders.stream().map(OrderResponse::new).toList();

        // A classe ResponseEntity representa a resposta HTTP inteira (status e corpo) enviada ao cliente após requisição
        return ResponseEntity.ok(ordersResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id){
        Order orderFound = orderService.findById(id);
        OrderResponse orderResponse = new OrderResponse(orderFound);

        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@RequestBody OrderRequest orderRequestDTO, @PathVariable Long id){
        Order orderUpdate = orderService.update(orderRequestDTO, id);
        OrderResponse orderResponse = new OrderResponse(orderUpdate);

        return ResponseEntity.ok(orderResponse);
    }

    // Atualiza o registro com pagamento realizado e status concluído.
    @PutMapping("/{id}/paid")
    public ResponseEntity<OrderResponse> updateOrderPaid(@PathVariable Long id){
        Order orderUpdate = orderService.updatePaid(id);
        OrderResponse orderResponse = new OrderResponse(orderUpdate);

        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna resposta sem corpo
    }

    @PostMapping
    public ResponseEntity<OrderResponse> insert(@RequestBody @Valid OrderRequest orderRequestDTO){
        Order orderSave = orderService.insert(orderRequestDTO);
        OrderResponse orderResponse = new OrderResponse(orderSave);

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/ai/text-to-orderJson")
    public ResponseEntity<OrderRequest> getOrderJsonWithAi(@RequestParam String text){
        OrderRequest jsonResponse = genAiService.generateResponse(text);
        return ResponseEntity.ok().body(jsonResponse);
    }

    @PostMapping(value = "/ai/audio-to-text", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> getOrderPrompt(@RequestParam("audio") MultipartFile file){
        if (file.isEmpty() || file.getContentType() == null || !file.getContentType().startsWith("audio/")){
            throw new InvalidAudioFile();
        }

        Map<String, String> response = new HashMap<>();

        String transcriptionPrompt = speechToTextService.transcription(file);
        response.put("transcription", transcriptionPrompt);

        return ResponseEntity.ok().body(response);
    }
}
