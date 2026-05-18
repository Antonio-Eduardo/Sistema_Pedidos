package application;


import dao.ProdutoDAO;
import entities.Produto;
import exceptions.TransacaoException;
import factory.dao.DaoFactory;
import jakarta.persistence.EntityManager;

public class ServicoProduto {
    EntityManager em;
    ProdutoDAO produtoDAO;
    public ServicoProduto(EntityManager em) {
        this.em = em;
        this.produtoDAO = DaoFactory.criarProdutoDao(em);
    }

    public Produto criarProduto(String nome, double preco){
        try {
            Produto p = new Produto(nome, preco);
            em.getTransaction().begin();
            produtoDAO.salvar(p);
            em.getTransaction().commit();
            return p;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new TransacaoException(e.getMessage());
        }
    }
}
