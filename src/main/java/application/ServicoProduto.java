package application;


import dao.ProdutoDAO;
import entities.Produto;
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
        Produto p = new Produto(nome,preco);
        em.getTransaction().begin();
        produtoDAO.salvar(p);
        em.getTransaction().commit();
        return p;
    }
}
