# Sistema de Processamento de Pedidos (Java)

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
[![Static Badge](https://img.shields.io/badge/licenca-MIT-green)](https://github.com/Antonio-Eduardo/Sistema_Pedidos/blob/master/LICENSE)

> Sistema de gestão de pedidos com fluxo completo de vendas, focado em ORM e regras de negócio complexas.

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Regras de Negócio](#regras-de-negócio)
- [Conceitos Aplicados](#conceitos-aplicados)
- [Exemplo de Saída](#exemplo-de-saída)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Melhorias Futuras](#melhorias-futuras)

---

## Sobre o Projeto

O **Sistema de Processamento de Pedidos** é uma aplicação Java que simula o fluxo completo de vendas de um comércio, permitindo o gerenciamento de clientes, produtos e a automação de pedidos através de relacionamentos mapeados em banco de dados relacional.

> *Nota de desenvolvimento:* O projeto nasceu com persistência via arquivos de texto e evoluiu progressivamente — passando por JDBC com queries SQL manuais — até a migração completa para JPA/Hibernate. O foco foi entender como o Java lida com relacionamentos complexos no banco de dados, aplicando consultas otimizadas com JPQL e LEFT JOIN FETCH para prevenção do problema N+1, além de cálculos via Stream API e controle de estados com enums.

---

## Funcionalidades

- [x] **Gerenciamento de Clientes:** Registro e busca de clientes por ID via JPA
- [x] **Gerenciamento de Produtos:** Cadastro e listagem de estoque disponível no banco de dados
- [x] **Criação de Pedidos:** Vinculação dinâmica de pedidos ao cliente com adição de itens, controle de quantidade e preço
- [x] **Cálculo Automatizado:** Fechamento e consolidação de valores totais via Stream API
- [x] **Caixa Convencional:** Atendimento padrão sem restrições de itens
- [x] **Caixa Rápido:** Atendimento com trava de segurança de até 15 itens por pedido
- [x] **Tratamento de Erros:** Limite de itens excedido, erros de persistência e falhas de controle transacional

---

## Tecnologias Utilizadas

- **Java** (JDK 17+)
- **JPA / Hibernate**
- **JDBC**
- **MySQL**
- **Stream API**
- **Maven**
- **Git / GitHub**

---

## Estrutura do Projeto

```
src/
├── entities/           → Mapeamento objeto-relacional (Cliente, Pedido, Produto, ItensPedido)
├── enums/              → Estados do pedido e catálogos de códigos de erro
├── dao/                → Interfaces dos contratos de acesso a dados
├── dao/impl/           → Implementação dos repositórios com JPA/EntityManager
├── factory/dao/        → Fábricas para injeção e instanciação dos componentes DAO
├── services/           → Contratos para as regras dos canais de atendimento (Caixa)
├── services/impl/      → Implementações do Caixa Convencional e Caixa Rápido
├── application/        → Orquestradores de negócio (Cálculo, Validação e Registro)
├── exceptions/         → Hierarquia de exceções customizadas baseadas em RuntimeException
└── main/               → Ponto de entrada e execução do fluxo via console
```

---

## Regras de Negócio

- O valor total do pedido é calculado automaticamente via **Stream API**
- O **Caixa Rápido** possui trava de segurança para no máximo **15 itens**
- O status do pedido é alterado para **"Finalizado"** apenas após o processamento no caixa
- Relacionamentos garantem que não existam itens órfãos sem um pedido vinculado

---

## Conceitos Aplicados

- Herança e Polimorfismo
- Encapsulamento e Abstração
- Interfaces e DAO Pattern
- Service Layer
- Injeção de Dependência
- Stream API
- Separação de Responsabilidades
- JPA ORM Mappings (`@OneToMany`, `@ManyToOne`, `@Id`)
- JPQL com `LEFT JOIN FETCH` (prevenção N+1)
- Controle transacional com `EntityManager` (`begin`, `commit`, `rollback`)
- Máquina de estados com enums

---

## Exemplo de Saída

```text
EXTRATO COMPLETO - CLIENTE: EDUARDO
E-MAIL: eduardo@email.com

PEDIDO ID: 10 | DATA: 2024-03-20 | STATUS: FINALIZADO
ITENS:
  Qtd: 2 | Preço Un: R$ 50.00  | Subtotal: R$ 100.00
  Qtd: 1 | Preço Un: R$ 150.00 | Subtotal: R$ 150.00

TOTAL DO PEDIDO: R$ 250.00
```

---

## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Antonio-Eduardo/Sistema_Pedidos.git
   ```

2. **Acesse a pasta do projeto:**
   ```bash
   cd Sistema_Pedidos
   ```

3. **Configure o banco de dados MySQL** e ajuste as credenciais no arquivo `main/factory/db.properties`

4. **Execute a aplicação:**
   ```bash
   mvn exec:java -Dexec.mainClass="main.Main"
   ```

---

## Melhorias Futuras

- [ ] Migração para Spring Boot e Spring Data JPA
- [ ] Criação de uma API RESTful
- [ ] Substituição de `Double` por `BigDecimal` para precisão financeira
- [ ] Implementação de testes unitários com JUnit
