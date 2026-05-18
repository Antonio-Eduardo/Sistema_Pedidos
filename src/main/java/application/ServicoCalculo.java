package application;

import entities.Pedido;

public class ServicoCalculo {

    public double calcularPedido(Pedido pedido) {
        return pedido.getItensPedidos().stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
    }
}
