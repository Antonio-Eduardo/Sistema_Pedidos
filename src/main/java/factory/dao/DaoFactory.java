package factory.dao;


import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import dao.impl.ClienteDaoImpl;
import dao.impl.PedidoDaoImpl;
import dao.impl.ProdutoDaoImpl;
import jakarta.persistence.EntityManager;

public interface DaoFactory {

    public static ClienteDAO criarClienteDao(EntityManager em) {
        return new ClienteDaoImpl(em);
    }

    public static ProdutoDAO criarProdutoDao(EntityManager em) {
        return new ProdutoDaoImpl(em);
    }

    public static PedidoDAO criarPedidoDao(EntityManager em) {
        return new PedidoDaoImpl(em);
    }

}
