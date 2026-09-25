package backend.order_spring_designpatterns.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
/* Classe que define criação e serviços do LLM (Google GenAi) */
public class ChatClientService {
    private final ChatClient chatClient;

    public ChatClientService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateResponse(String prompt){
        return this.chatClient.prompt().user(prompt).call().content();
    }
}
