package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.OrderRequest;
import backend.order_spring_designpatterns.DTO.Response.OrderResponseAi;
import backend.order_spring_designpatterns.Exception.AiInvalidDataEntered;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
/* Classe que define criação e serviços do LLM (Google GenAi) */
public class ChatClientService {
    private final ChatClient chatClient;

    public ChatClientService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public OrderRequest generateResponse(String prompt){
        /* As Propriedades também podem ser configuradas aqui:
        GoogleGenAiChatOptions.Builder chatOptions = GoogleGenAiChatOptions.builder()
                ...
                .model("gemini-3.6-flash")
                .temperature(.6);
         */

        OrderResponseAi jsonResponse = this.chatClient.prompt().user(prompt).call().entity(OrderResponseAi.class);

        if (jsonResponse.orderItems() == null || jsonResponse.clientId() == null || jsonResponse.payment() == null)
            throw new AiInvalidDataEntered(jsonResponse.message());

        return new OrderRequest(
                jsonResponse.clientId(),
                jsonResponse.orderItems(),
                jsonResponse.payment()
        );
    }
}
