package services.impl;


import entities.Pedido;
import enums.StatusPedido;
import exceptions.DbException;
import exceptions.TransacaoException;
import jakarta.persistence.EntityManager;
import services.Caixa;

public class ServicoCaixaDefault implements Caixa {
    @Override
    public void processarPedido(Pedido pedido, EntityManager em) {
        try {
            em.getTransaction().begin();
            double total = pedido.getPrecoPedido();
            pedido.setStatus(StatusPedido.FINALIZADO);
            System.out.println("========================");
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("Total: " + total);
            System.out.println("========================");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new TransacaoException(e.getMessage());
        }
    }
}
