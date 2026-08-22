# Order Spring Design Patterns

Este é um projeto desenvolvido para o curso da **DIO (Digital Innovation One)** no Bootcamp de Java AI.

> **Aviso:** A ideia deste projeto, assim como o seu contexto e as ações necessárias, foram fornecidas por uma Inteligência Artificial (IA). O desenvolvimento e o código-fonte passaram por várias adaptações para focar exclusivamente na implementação dos **Design Patterns** (Singleton e Facade), sendo um desenvolvimento voltado para fins didáticos. Dessa forma, o sistema foi projetado de modo simples, não apresentando validações complexas, tratamento de exceções ou lógicas de difícil compreensão.

## 🚀 O que o projeto faz?

O projeto simula um sistema de gerenciamento de **Pedidos (Orders)** de uma loja virtual, incluindo:
*   Gerenciamento de Clientes (Clients)
*   Gerenciamento de Produtos (Products)
*   Gerenciamento de Pedidos (Orders) contendo Itens de Pedido (Order Items)
*   Processamento de Pagamentos (Payments)

## 🧩 Implementação de Design Patterns

Neste projeto, os seguintes padrões de projeto **(Design Patterns)** foram implementados:

1.  **Singleton:** No ecossistema Spring Boot, a injeção de dependências (IoC) utiliza o Singleton por padrão - neste projeto, utilizou-se a anotação `@Autowired` nas variáveis: injeção interna realizada pelo Spring, com **Singleton** como padrão para cada `@Bean`. Classes anotadas com `@Service`, `@RestController` e `@Repository` são instanciadas uma única vez e gerenciadas pelo container do Spring, garantindo uma única instância por toda a aplicação para processamento das lógicas de negócio.
2.  **Facade:** O padrão Facade foi aplicado na arquitetura através, principalmente, dos **Services**, os quais orquestram a implementação de interfaces e o processamento de informações transportadas, ocultando a complexidade da comunicação com repositórios e entidades do banco de dados ao mesmo tempo.

## 🛠 Funcionalidades Oferecidas e Endpoints

*   **API RESTful** para operações CRUD (Create, Read, Update, Delete) de:
    *   Clientes (`/clients`)
    *   Produtos (`/products`)
    *   Pedidos (`/orders`)
*   Documentação e testes da API integrados através do **Swagger/OpenAPI**.
*   Banco de dados em memória, ideal para prototipagem rápida e armazenamento simples, sem configurações de máquina.

## 🏗 Arquitetura do Projeto

O projeto foi organizado utilizando uma arquitetura baseada em camadas (Layered Architecture):
*   **Controllers (`Controller`):** Exposição das rotas REST e controle de requisições.
*   **Serviços (`Service`):** Regras de negócios principais da aplicação.
*   **Repositórios (`Repository`):** Interfaces que abstraem o acesso aos dados utilizando Spring Data JPA.
*   **Entidades (`Entity`):** Representação das tabelas no banco de dados.
*   **DTOs (`DTO`):** Transferência de dados segura e formatada entre as requisições e respostas (separando entrada/saída das entidades reais persistidas no banco).

## 💻 Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot (v4.1.0)**
    *   Spring Web MVC
    *   Spring Data JPA
    *   Spring Cloud OpenFeign
*   **H2 Database** (Banco de dados em memória)
*   **Springdoc OpenAPI** (Swagger UI para documentação)
*   **Gradle** (Ferramenta de automação de builds)

## 🧱 Estrutura das entidades - API

Estrutura das entidades na API (Produto `product`, Pedido `order`, Item do Pedido `order_item`, Pagamento `payment`, Cliente `client`):

Produto `product`:
```json
{
  "id": 0,
  "name": "string",
  "price": 0,
  "stock": 0
}
```

Pedido `order`:
```json
{
  "orderId": 0,
  "client": {
    "id": 0,
    "name": "string",
    "email": "string"
  },
  "payment": {
    "id": 0,
    "orderId": 0,
    "status": "PENDING",
    "type": "string",
    "paymentDate": "2026-08-22T19:52:23.516Z"
  },
  "totalValue": 0,
  "status": "PENDING",
  "creationDate": "2026-08-22T19:52:23.516Z",
  "orderItems": [
    {
      "id": 0,
      "product": {
        "id": 0,
        "name": "string",
        "price": 0,
        "stock": 0
      },
      "orderId": 0,
      "amount": 0,
      "totalPrice": 0
    }
  ]
}
```

Item do Pedido `order_item`:
```json
{
  "id": 0,
  "product": {
    "id": 0,
    "name": "string",
    "price": 0,
    "stock": 0
  },
  "orderId": 0,
  "amount": 0,
  "totalPrice": 0
}
```

Pagamento `payment`:
```json
{
  "id": 0,
  "orderId": 0,
  "status": "PENDING",
  "type": "string",
  "paymentDate": "2026-08-22T19:54:16.509Z"
}
```

Cliente `client`:
```json
{
  "id": 0,
  "name": "string",
  "email": "string"
}
```

## 📖 Exemplos de Uso da API

Você pode testar a API acessando o **Swagger UI**. Assim que a aplicação estiver rodando, acesse no seu navegador:

`http://localhost:8080/swagger-ui.html`

Exemplo de **Criação de Produto (POST)** via JSON:
```json
{
  "name": "Notebook Gamer",
  "price": 4500.00,
  "stock": 15
}
```

Exemplo de **Criação de Pedido (POST)** via JSON:
```json
{
  "clientId": 1,
  "orderItems": [
    {
      "productId": 1,
      "amount": 2
    }
  ],
  "paymentDTO": {
    "type": "PIX"
  }
}
```

Exemplo de **Criação de Cliente (POST)** via JSON:
```json
{
  "name": "Pedro Henrique",
  "orderItems": "pedrohenrique@gmail.com"
}
```

## ⚙️ Passo a Passo para clonar e utilizar

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd Order_Spring_DesignPatterns
   ```

2. **Certifique-se de ter o Java 21 configurado** no seu ambiente.

3. **Inicie o projeto através do Gradle Wrapper:**
    * No Windows:
      ```bash
      ./gradlew.bat bootRun
      ```
    * No Linux/Mac:
      ```bash
      ./gradlew bootRun
      ```

4. **Acesso à API:**
    * Swagger: `http://localhost:8080/swagger-ui.html`