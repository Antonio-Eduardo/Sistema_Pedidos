package dao.impl;

import dao.PedidoDAO;
import entities.Pedido;
import jakarta.persistence.EntityManager;


import java.sql.*;
import java.util.List;

public class PedidoDaoImpl implements PedidoDAO {
    private EntityManager em;

    public PedidoDaoImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public Pedido buscarPedidoPorId(Long idPedido) {
        return em.find(Pedido.class,idPedido);
    }

    @Override
    public List<Pedido> buscarPedidosComItens(Long id) {
        String jpql = """
            SELECT DISTINCT p
            FROM Pedido p
            LEFT JOIN FETCH p.itensPedidos i
            LEFT JOIN FETCH i.produto
            WHERE p.cliente.iD = :id
            """;

        return em.createQuery(jpql, Pedido.class)
                .setParameter("id", id)
                .getResultList();
    }
}
