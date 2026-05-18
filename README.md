# Sistema de Pedidos (Java)

    Projeto de um sistema de gestão de pedidos desenvolvido em Java com foco em Programação Orientada a Objetos, simulando o fluxo de vendas de um comércio. O projeto permite o gerenciamento de clientes, produtos e a automação de pedidos complexos com múltiplos itens e integração com banco de dados.
    O que tem no projeto

## Gerenciamento de Clientes:

*Registro de novos clientes

    *Busca de conta por ID
    *Gerenciamento de Produtos:
    *Registro de produtos e preços
    *Listagem de estoque disponível

*Operações de Pedido:

    *Criação de pedidos vinculados ao cliente
    *Adição de itens com controle de quantidade e preço unitário
    *Fechamento e finalização de pedidos

*Canais de Atendimento:

    *Caixa convencional
    *Caixa rápido (com validação de limite de itens)

*Tratamento de erros

    *Limite de itens excedido (Caixa Rápido)
    *Entradas de dados inválidas
    *Exceções de banco de dados

*Persistência de dados com MySQL usando JDBC

    *Controle de mapeamento complexo com:
    *Consultas SQL com múltiplos JOINs
    *Recuperação de chaves geradas (ID)
    *Relacionamento Um-para-Muitos (Pedido -> Itens)

## Estrutura

    *main.java.factory.entities → classes principais (Cliente, Pedido, Produto, ItensPedido)
    *main.java.factory.dao → interfaces DAO
    *main.java.factory.dao.impl → implementação JDBC/MySQL
    *main.java.factory.services → interfaces e regras de negócio
    *main.java.factory.services.impl → implementação das regras de caixa
    *main.java.factory.application → serviços auxiliares (Cálculo e Gestão de Itens)
    *main.java.factory.exceptions → tratamento de erros
    *main → execução no console

## Regras de negócio

    *O valor total do pedido é calculado automaticamente via Stream API
    *O Caixa Rápido possui trava de segurança para no máximo 15 itens
    *O status do pedido é alterado para "Finalizado" apenas após o processamento no caixa
    *Relacionamentos garantem que não existam itens órfãos sem um pedido vinculado

## Conceitos aplicados

    *Herança
    *Polimorfismo
    *Encapsulamento
    *Abstração
    *Interfaces
    *DAO Pattern
    *Service Layer
    *Injeção de Dependência
    *Stream API
    *Separação de responsabilidades

## Exemplo de saída
EXTRATO COMPLETO - CLIENTE: EDUARDO
E-MAIL: eduardo@email.com

PEDIDO ID: 10 | DATA: 2024-03-20 | STATUS: FINALIZADO
ITENS:

    Qtd: 2 | Preço Un: R$ 50.00 | Subtotal: R$ 100.00
    Qtd: 1 | Preço Un: R$ 150.00 | Subtotal: R$ 150.00

TOTAL DO PEDIDO: R$ 250.00

## Como rodar

    Clonar o repositório
    Configurar o banco de dados MySQL
    Ajustar as credenciais de conexão no arquivo main.java.factory.db.properties
    Executar a classe main.java.Main.java

## Tecnologias utilizadas

- Java 17+
- JDBC
- MySQL
- Stream API
- Git/GitHub

## Observações

Fiz esse projeto para aprofundar meus conhecimentos em backend, focando em como o código Java lida com relacionamentos complexos no banco de dados.
A principal evolução deste projeto em relação ao sistema bancário anterior foi a reconstrução de objetos complexos através de consultas SQL envolvendo múltiplos JOINs.

## Ainda existem melhorias futuras possíveis, como:

    *Migração para Spring Boot
    *Uso de Spring Data JPA
    *Substituição do Double por BigDecimal para precisão financeira
    *Criação de uma API RESTful
    *Implementação de testes unitários com JUnit