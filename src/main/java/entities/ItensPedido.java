package entities;

import jakarta.persistence.*;

@Entity
public class ItensPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;
    private Integer quantidade;
    private double preco;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public ItensPedido() {
    }
    public ItensPedido(Integer quantidade, double preco) {
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItensPedido(Long iD, Integer quantidade, double preco) {
        this.iD = iD;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n%.2f", preco)).append(" R$ ");
        sb.append("Quantidade: ").append(quantidade).append("\n");

        return sb.toString();
    }
}
