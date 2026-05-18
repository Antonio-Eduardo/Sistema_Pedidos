package dao.impl;

import dao.ClienteDAO;
import entities.Cliente;
import exceptions.DbException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.sql.*;


public class ClienteDaoImpl implements ClienteDAO {
    private EntityManager em;

    public ClienteDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Cliente buscarContaPorId(Long id) {
        try {
            return em.find(Cliente.class, id);
        }catch (PersistenceException e){
            throw new DbException();
        }
    }
    @Override
    public void salvar(Cliente c) {
        em.persist(c);
    }
    @Override
    public void deletarCliente(Long id) {
        Cliente cliente = em.find(Cliente.class,id);
        em.remove(cliente);
    }
    @Override
    public Cliente ClientesPedidos(Long id) {
        String jpql = """
            SELECT DISTINCT c
            FROM Cliente c
            LEFT JOIN FETCH c.pedidos
            WHERE c.iD = :id
            """;

        return em.createQuery(jpql, Cliente.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
