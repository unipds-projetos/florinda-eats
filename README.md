# florinda-eats

Florinda Eats é uma aplicação de entrega de comida para o restaurante da Dona Florinda.

É uma Arquitetura de Microservices composta pelos seguintes serviços:

- **Pedidos**, que tem informações do cardápio e dos pedidos realizados. Roda por default na porta `8080`
- **Pagamentos**, que permite a confirmação de um pagamento. Roda por default na porta `8081`
- **Nota Fiscal**, que gera XMLs de notas fiscais na saída padrão. Roda por default na porta `8082`
- **Signer**, que deve gerar um hash MD5 dos dados de um pagamento. Roda por default na porta `8083`

# Como executar a aplicação?

## Executar o serviço de Pagamento

Abra a código do serviço de `pagamentos` no IntelliJ.

Execute o serviço através do IntelliJ, utilizando _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Abra o Postman e importe a coleção `florinda-eats-pagamento.postman_collection`.

Explore o serviço e seus endpoints. Por exemplo, confirme um pagamento realizando um `PUT http://localhost:8081/pagamentos/1`.

## Executar o serviço de Pedidos

Abra a código do serviço de `pedidos` no IntelliJ.

Execute o serviço através do IntelliJ, utilizando _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Abra o Postman e importe a coleção `florinda-eats-pedidos.postman_collection`.

Verifique a listagem de pedidos na URL `http://localhost:8080/pedidos`.

Explore o serviço e seus endpoints. Por exemplo, detalhe um pedido (p. ex., o de id 1) com a URL `http://localhost:8080/pedidos/1`

## Executar o serviço de Notas Fiscal

Abra a código do serviço de `notas-fiscais` no IntelliJ.

Execute o serviço através do IntelliJ, utilizando _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Abra o Postman e importe a coleção `florinda-eats-notas-fiscais.postman_collection`.

Obtenha uma nota fiscal para um pedido (p. ex., o de id 1) com a URL `http://localhost:8082/nota-fiscal/pedido/1`

## Executar o serviço Signer

Abra a código do serviço `signer` no IntelliJ.

Execute o serviço através do IntelliJ, utilizando _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Já há um código para a classe `Hash`, que gerar um hash MD5 a partir de uma `String`.
Há também um código para a classe `PagamentoConfirmadoEvent`, que representado o evento de pagamento confirmado.

## Executar o Kafka

O arquivo `docker-compose.yml` contém uma configuração de Kafka cuja porta para conexões externas ao container é `9094`.

Para executar o Kafka, abra um Terminal e rode na raiz do projeto:

```sh
docker compose up
```

Espera um tiquinho que o Kafka deve subir. Os serviços já estão quase todos integrados. Só falta o Signer!

# O que você deve fazer?

Consuma o tópico `pagamentosConfirmados` e gere use a classe `Hash` para gerar o MD5 do `toString` de `PagamentoConfirmadoEvent`. Imprima o resultado no `System.out`.

Você não deve modificar o código da classe `Hash` nem da classe `PagamentoConfirmadoEvent`.

Responsa a pergunta: qual é o hash MD5 do `PagamentoConfirmadoEvent` de Godinez? Envie a resposta no canal `#02-backend-spring-quarkus`!
