package services;


import entities.Pedido;
import jakarta.persistence.EntityManager;

public interface Caixa {
    public void processarPedido(Pedido pedido, EntityManager em);
}
