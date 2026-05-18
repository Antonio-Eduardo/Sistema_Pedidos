package services.impl;


import entities.Pedido;
import enums.StatusPedido;
import jakarta.persistence.EntityManager;
import services.Caixa;

public class ServicoCaixaDefault implements Caixa {
    @Override
    public void processarPedido(Pedido pedido, EntityManager em) {
        em.getTransaction().begin();
        double total = pedido.getPrecoPedido();
        System.out.println("========================");
        System.out.println("Status: " + StatusPedido.FINALIZADO);
        System.out.println("Total: " + total);
        System.out.println("========================");
        em.getTransaction().commit();
    }
}
