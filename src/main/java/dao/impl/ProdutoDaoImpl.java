package dao.impl;

import dao.ProdutoDAO;
import db.DB;
import entities.Produto;
import exceptions.DbException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDAO {
    private EntityManager em;

    public ProdutoDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
       return em.find(Produto.class,id);
    }
    @Override
    public void salvar(Produto p) {
        em.persist(p);
    }
    @Override
    public void deletarProdutoPorId(Long id) {
       Produto p = em.find(Produto.class,id);
       em.remove(p);
    }

    @Override
    public List<Produto> todosProdutos() {
       String jpql = "SELECT p FROM Produto p";
        TypedQuery<Produto> query = em.createQuery(jpql,Produto.class);
        return query.getResultList();
    }
}
