import application.*;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import entities.Cliente;
import entities.ItensPedido;
import entities.Pedido;
import entities.Produto;
import exceptions.TransacaoException;
import factory.dao.DaoFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import services.impl.ServicoCaixaDefault;
import services.impl.ServicoCaixaRapido;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema-pedido-jpa");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        ServicoCliente servicoCliente = new ServicoCliente(em);
        ServicoProduto servicoProduto = new ServicoProduto(em);
        ServicoPedido servicoPedido = new ServicoPedido(em);
        ServiceItemPedido serviceItemPedido = new ServiceItemPedido();

        ClienteDAO clienteDAO = DaoFactory.criarClienteDao(em);
        PedidoDAO pedidoDAO = DaoFactory.criarPedidoDao(em);
        ProdutoDAO produtoDAO = DaoFactory.criarProdutoDao(em);

        int criandoCliente = 0;
        while (criandoCliente != 2) {
            criandoCliente = ServicoValidacao.lerInteiros(sc, "[1]-Registrar Cliente|[2]-Cliente ja registrado\n");
            switch (criandoCliente) {
                case 1:
                    String nome = ServicoValidacao.lerString(sc, "Nome do cliente: ");
                    String cpf = ServicoValidacao.lerString(sc, "Digite o cpf do cliente: ");
                    String email = ServicoValidacao.lerString(sc, "E-mail do cliente: ");
                    LocalDate dataNasc = ServicoValidacao.lerData(sc, "Data de nascimento do cliente: ");
                    Cliente novoCliente = servicoCliente.registrarCliente(nome, email, dataNasc, cpf);
                    System.out.println("iD do cliente: " + novoCliente.getiD());
                    break;
                case 2:
                    System.out.print("Saindo do cadastro...\n");
                    break;
                default:
                    System.out.print("Escolha invalida!");
            }
        }
        int criandoPedido = 0;
        while (criandoPedido != 2) {
            criandoPedido = ServicoValidacao.lerInteiros(sc, "[1]-Registrar Produto|[2]-Seguir para pedido\n");
            switch (criandoPedido) {
                case 1:
                    String produto = ServicoValidacao.lerString(sc, "Nome do produto: ");
                    double precoProduto = ServicoValidacao.lerDouble(sc, "Preco do produto: ");
                    Produto prod = servicoProduto.criarProduto(produto, precoProduto);
                    break;
                case 2:
                    System.out.print("Saindo...\n");
                    break;
                default:
                    System.out.print("Escolha invalida!");
            }
        }
        while (true) {
            int resposta4 = ServicoValidacao.lerInteiros(sc, "[1-PROSSEGUIR COM PEDIDO|2-FINALIZAR OPERACAO\n");

            if (resposta4 != 1 && resposta4 != 2) {
                System.out.println("Entrada invalida");
                continue;
            }

            if (resposta4 == 2) {
                break;
            }
            List<Produto> produtos = produtoDAO.todosProdutos();
            for (Produto p : produtos) {
                System.out.println("Nome: " + p.getNome() + " [iD: " + p.getiD() + "]"
                        + "\nPreco: " + p.getPreco());
            }
            Long idClient = ServicoValidacao.lerLong(sc, "Digite o iD da conta que voce deseja realizar a operacao: ");
            Cliente clienteBusca = clienteDAO.buscarContaPorId(idClient);
            int validarPedido = ServicoValidacao.lerInteiros(sc, "Tem um pedido em andamento? [1]SIM[2]NAO");
            Pedido pedidoAtual = null;
            if (validarPedido == 2) {
                try {
                    em.getTransaction().begin();

                    pedidoAtual = new Pedido();
                    clienteBusca.addPedido(pedidoAtual);
                    em.persist(pedidoAtual);

                    em.getTransaction().commit();
                } catch (Exception e) {
                    em.getTransaction().rollback();
                    throw new TransacaoException(e.getMessage());
                }
                System.out.println("Pedido criado: " + pedidoAtual.getIdPedido());
            } else {
                long pedidoIdent = ServicoValidacao.lerLong(sc, "Digite o id do seu pedido: ");
                pedidoAtual = pedidoDAO.buscarPedidoPorId(pedidoIdent);
            }

            int iniciandoPedido = 0;
            while (iniciandoPedido != 3) {
                iniciandoPedido = ServicoValidacao.lerInteiros(sc, "[1-ADICIONAR ITENS|2-FECHAR O PEDIDO|3-SAIR\n");
                switch (iniciandoPedido) {
                    case 1:
                        Long produtoiD = ServicoValidacao.lerLong(sc, "Insira o iD do produto desejado:");
                        Produto produto = produtoDAO.buscarProdutoPorId(produtoiD);

                        double precoVenda = ServicoValidacao.lerDouble(sc, "Preco de venda: ");
                        int quantidade = ServicoValidacao.lerInteiros(sc, "Quantidade desse produto: ");

                        ItensPedido item = serviceItemPedido.criarItem(quantidade, precoVenda);
                        item.setProduto(produto);
                        item.setPedido(pedidoAtual);
                        pedidoAtual.addItem(item);

                        break;
                    case 2:

                        servicoPedido.fecharPedido(pedidoAtual);
                        System.out.println("Pedido finalizado!");
                        System.out.print(pedidoAtual);
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.print("Opcao invalida!");
                }
            }
        }
        Long idClienteCaixa = ServicoValidacao.lerLong(sc, "Identifique o cliente pelo [iD]: ");
        Cliente clienteCaixa = clienteDAO.buscarContaPorId(idClienteCaixa);

        Long idPedidoCaixa = ServicoValidacao.lerLong(sc, "Identifique o pedido pelo [iD]: ");
        Pedido pedidoBusca = pedidoDAO.buscarPedidoPorId(idPedidoCaixa);

        int escolhaCaixa = ServicoValidacao.lerInteiros(sc, "Qual caixa deseja usar? [1-CAIXA | 2-CAIXA RAPIDO]\n");
        switch (escolhaCaixa) {
            case 1:
                ServicoCaixaDefault caixaP = new ServicoCaixaDefault();

                caixaP.processarPedido(pedidoBusca,em);
                break;
            case 2:
                ServicoCaixaRapido caixaR = new ServicoCaixaRapido();

                caixaR.processarPedido(pedidoBusca,em);
                break;
        }
        System.out.println("\n--- OPERAÇÃO FINALIZADA ---");
        System.out.println("Gerando extrato consolidado via JOIN...");

        Cliente clienteFinal = clienteDAO.buscarContaPorId(idClienteCaixa);
        List<Pedido> pedidos = pedidoDAO.buscarPedidosComItens(idClienteCaixa);

        clienteFinal.setPedidos(pedidos);

            System.out.println("\n========================================");
            System.out.println("EXTRATO COMPLETO - CLIENTE: " + clienteFinal.getNome().toUpperCase());
            System.out.println("E-MAIL: " + clienteFinal.getEmail());
            System.out.println("========================================");

            for (Pedido p : clienteFinal.getPedidos()) {
                System.out.println("\n----------------------------------------");
                System.out.printf("PEDIDO ID: %d | DATA: %s | STATUS: %s%n",
                        p.getIdPedido(), p.getDataHora(), p.getStatus());
                System.out.println("ITENS:");

                for (ItensPedido item : p.getItensPedidos()) {
                    System.out.printf("  - Qtd: %d | Preço Un: R$ %.2f | Subtotal: R$ %.2f%n",
                            item.getQuantidade(), item.getPreco(), (item.getQuantidade() * item.getPreco()));
                }
                System.out.printf("TOTAL DO PEDIDO: R$ %.2f%n", p.getPrecoPedido());

            System.out.println("========================================\n");
        }
        emf.close();
    }
}

