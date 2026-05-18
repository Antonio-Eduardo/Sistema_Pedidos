package services.impl;


import entities.Pedido;
import enums.StatusPedido;
import exceptions.CaixaLimiteExcedido;
import exceptions.TransacaoException;
import jakarta.persistence.EntityManager;
import services.Caixa;

public class ServicoCaixaRapido implements Caixa {
    @Override
    public void processarPedido(Pedido pedido, EntityManager em) {
        if (pedido.getItensPedidos().size() > 15){
            throw new CaixaLimiteExcedido();
        }
        try {
            em.getTransaction().begin();
            double total = pedido.getPrecoPedido();
            System.out.println("========================");
            System.out.println("Status: " + StatusPedido.FINALIZADO);
            System.out.println("Total: " + total);
            System.out.println("========================");
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            throw new TransacaoException(e.getMessage());
        }
    }
}
