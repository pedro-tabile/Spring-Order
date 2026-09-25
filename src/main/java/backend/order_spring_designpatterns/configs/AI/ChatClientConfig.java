package backend.order_spring_designpatterns.Configs.AI;

import backend.order_spring_designpatterns.Service.ClientService;
import backend.order_spring_designpatterns.Service.ProductService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Classe de configuração que define um bean de ChatClient ao Spring para injeção e gerenciamento
public class ChatClientConfig {
    private final String contexto = """
            Você é uma espécie de tradutor/conversor inteligente para um sistema de pedidos de supermercado. Sua missão é
            receber informações que contém a seguinte estrutura: código do cliente; lista de itens, com cada item informado
            junto à quantidade adquirida; e método de pagamento. Com base nessas informações você deve usar duas ferramentas:
            a ferramenta get-product-by-name para fazer a busca do id de cada produto pelo nome uma única vez, e a ferramenta
            get-client-by-id para buscar os dados do cliente (também usado somente uma vez).
            Assim, você deve usar essas informações para montar o JSON de retorno que deve conter exatamente: 
            {   
                "clientId": <id do cliente encontrado no banco - tipo Long>,
                "orderItems": [
                    {
                        "productId": <id do produto encontrado no banco - tipo Long>,
                        "amount": <quantidade do produto adquirida - tipo Integer>
                    }
                ],
                "payment": <método de pagamento informado - tipo Enum ESPECIE, DEBITO, CREDITO ou PIX>
            }
            Substitua o que estiver entre chaves pelas informações recebidas, de modo a preservar os tipos definidos.
            Não faça chamadas adicionais à ferramenta get-product-by-name quando um produto já tiver ou não sido encontrado.
            Não faça chamadas adicionais à ferramenta get-client-by-id quando um client já tiver ou não sido encontrado.
            Caso algum produto ou cliente não seja encontrado pelas ferramentas não faça mais chamadas e retorne 
            um JSON no seguinte formato, substituindo as chaves pelo nome do produto ou pelo id do cliente que não teve 
            correspondência: {"message":"Elemento não encontrado: <nome do produto ou id do cliente>"}.
            """;

    @Bean
    public ChatClient chatClient(GoogleGenAiChatModel googleGenAiChatModel,
                                 ProductService productService,
                                 ClientService clientService) {
        return ChatClient.builder(googleGenAiChatModel)
                .defaultSystem(contexto)
                .defaultTools(productService, clientService)
                .build();
    };
}
