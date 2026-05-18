# Sistema de Pedidos (Java)

    Projeto de um sistema de gestão de pedidos desenvolvido em Java com foco em Programação Orientada a Objetos e persistência
    utilizando JPA. O projeto simula o fluxo completo de vendas de um comércio, permitindo o gerenciamento de clientes, produtos e 
    a automação de pedidos complexos através de relacionamentos mapeados em banco de dados relacional.

## O que tem no projeto:

*Gerenciamento de Clientes:

    Registro de novos clientes via JPA

    Busca e gerenciamento de contas por ID

*Gerenciamento de Produtos:

    Cadastro de produtos e listagem de estoque disponível no banco de dados

*Operações de Pedido:

    Criação e vinculação de pedidos dinâmicos ao cliente

    Adição de itens com controle rigoroso de quantidade e preço

    Fechamento e consolidação de valores totais

*Canais de Atendimento (Estratégias de Caixa):

    Caixa convencional

    Caixa rápido (com validação e trava de segurança por limite de itens)

*Tratamento de erros

    Limite de itens excedido (Caixa Rápido)
    Erros de persistência e banco de dados personalizados
    Falhas de controle transacional

*Persistência de dados com MySQL usando JPA / Hibernate

    Controle de transações e mapeamento avançado com:
    Gerenciamento de contexto com EntityManager
    Controle manual de transações (begin, commit, rollback)
    Consultas otimizadas com JPQL e LEFT JOIN FETCH (Prevenção do problema N+1)

## Estrutura

    *entities → mapeamento objeto-relacional das entidades (Cliente, Pedido, Produto, ItensPedido)
    *enums → estados do pedido e catálogos de códigos de erro
    *dao → interfaces dos contratos de acesso a dados
    *dao.impl → implementação dos repositórios utilizando JPA/EntityManager
    *factory.dao → fábricas para injeção e instanciação dos componentes DAO
    *services → contratos para as regras dos canais de atendimento (Caixa)
    *services.impl → implementações lógicas do Caixa Convencional e Caixa Rápido
    *application → orchestradores de negócio (Cálculo, Validação e Registro)
    *exceptions → hierarquia de exceções customizadas baseadas em RuntimeException
    *main → ponto de entrada e execução do fluxo via console

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
    *JPA ORM Mappings (@OneToMany, @ManyToOne, @Id)
    *JPQL Queries (Join Fetch)

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
- JPA/HIBERNATE

## Observações

Fiz esse projeto para aprofundar meus conhecimentos em backend, focando em como o código Java lida com relacionamentos complexos no banco de dados.
Esse projeto começou com persistencia via Txt e acabou evoluindo para persistencia em database, utilizando SQL e JDBC. Atualmente o projeto segue a mesma lógica, porem,
realizei a migração de toda a minha camada de dados de queries SQL manuais no JDBC para o ecossistema do JPA/Hibernate.

## Ainda existem melhorias futuras possíveis, como:

    *Migração para Spring Boot
    *Uso de Spring Data JPA
    *Substituição do Double por BigDecimal para precisão financeira
    *Criação de uma API RESTful
    *Implementação de testes unitários com JUnit