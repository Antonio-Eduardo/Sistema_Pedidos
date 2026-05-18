package dao;

import entities.Produto;

import java.util.List;

public interface ProdutoDAO {
    void salvar(Produto p);
    Produto buscarProdutoPorId(Long id);
    void deletarProdutoPorId(Long id);
    List<Produto> todosProdutos();
}
