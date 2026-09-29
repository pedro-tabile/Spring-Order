package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.OrderRequest;
import backend.order_spring_designpatterns.DTO.Response.ModelOrderResponseAi;
import backend.order_spring_designpatterns.DTO.Response.OrderItemResponse;
import backend.order_spring_designpatterns.DTO.Response.OrderResponse;
import backend.order_spring_designpatterns.DTO.Response.ModelOrderRequestResponseAi;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Exception.AiInvalidDataEntered;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/* Classe que define criação e serviços do LLM (Google GenAi) */
public class ChatClientService {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private OrderService orderService;

    @Tool(
            name = "insert-order",
            description = "Salva um order no banco de dados a partir das informações recebidas no json e retorna um " +
                    "order atualizado com as informações armazenadas"
    )
    public ModelOrderResponseAi insertOrderWithAi(OrderRequest orderRequest) {
        Order orderSaved = orderService.insert(orderRequest);
        ModelOrderResponseAi orderResponse = new ModelOrderResponseAi(orderSaved);
        return orderResponse;
    }

    public OrderRequest generateResponse(String prompt){
        /* As Propriedades também podem ser configuradas aqui:
        GoogleGenAiChatOptions.Builder chatOptions = GoogleGenAiChatOptions.builder()
                ...
                .model("gemini-3.6-flash")
                .temperature(.6);
         */

        ModelOrderRequestResponseAi jsonResponse = this.chatClient
                .prompt().user(prompt).call().entity(ModelOrderRequestResponseAi.class);

        if (jsonResponse.orderItems() == null || jsonResponse.clientId() == null || jsonResponse.payment() == null)
            throw new AiInvalidDataEntered(jsonResponse.message());

        return new OrderRequest(
                jsonResponse.clientId(),
                jsonResponse.orderItems(),
                jsonResponse.payment()
        );
    }

    public OrderResponse saveOrderInBd(String prompt){
        ModelOrderResponseAi orderSavedJson = this.chatClient
                .prompt()
                .user(prompt)
                .tools(this)
                .call()
                .entity(ModelOrderResponseAi.class);

        if (orderSavedJson.orderId() == null)
            throw new AiInvalidDataEntered(orderSavedJson.message());

        OrderResponse jsonResponse = new OrderResponse(
                orderSavedJson.orderId(),
                orderSavedJson.client(),
                orderSavedJson.payment(),
                orderSavedJson.totalValue(),
                orderSavedJson.status(),
                orderSavedJson.creationDate(),
                orderSavedJson.orderItems()
        );

        return jsonResponse;
    }
}
