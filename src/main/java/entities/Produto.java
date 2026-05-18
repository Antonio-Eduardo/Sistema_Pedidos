package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;
    private String nome;
    private double precoProduto;

    public Produto() {
    }
    public Produto( String nome, double precoProduto) {
        this.nome = nome;
        this.precoProduto = precoProduto;
    }

    public Produto(Long iD, String nome, double precoProduto) {
        this.iD = iD;
        this.nome = nome;
        this.precoProduto = precoProduto;
    }

    public double getPreco() {
        return precoProduto;
    }

    public void setPreco(double preco) {
        this.precoProduto = preco;
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
