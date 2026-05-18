package application;


import entities.ItensPedido;
import factory.dao.DaoFactory;

public class ServiceItemPedido {

    public ItensPedido criarItem(int quantidade, double preco){
       return new ItensPedido(quantidade,preco);
    }
}
