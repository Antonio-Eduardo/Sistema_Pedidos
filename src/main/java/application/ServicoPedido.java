package application;

import dao.PedidoDAO;
import entities.Cliente;
import entities.ItensPedido;
import entities.Pedido;
import enums.StatusPedido;
import factory.dao.DaoFactory;
import jakarta.persistence.EntityManager;


public class ServicoPedido {
    PedidoDAO pedidoDAO;
    EntityManager em;

    public ServicoPedido(EntityManager em) {
        this.em = em;
        this.pedidoDAO = DaoFactory.criarPedidoDao(em);
    }

    public void adicionarItem(Pedido pedido, ItensPedido item) {
        pedido.addItem(item);
    }
    private final ServicoCalculo calculo = new ServicoCalculo();

    public void fecharPedido(Pedido pedido) {
        em.getTransaction().begin();

        double total =calculo.calcularPedido(pedido);
        pedido.setStatus(StatusPedido.PAGAMENTO_PENDENTE);
        pedido.setPrecoPedido(total);

        em.getTransaction().commit();
    }
}
