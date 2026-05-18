package application;


import dao.ClienteDAO;
import entities.Cliente;
import factory.dao.DaoFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class ServicoCliente {
    private EntityManager em;
    ClienteDAO clienteDAO;

    public ServicoCliente(EntityManager em) {
        this.clienteDAO = DaoFactory.criarClienteDao(em);
        this.em = em;
    }
    public Cliente registrarCliente(String nome, String email, LocalDate dataDeNascimento, String cpf) {
        Cliente c = new Cliente(nome, email, dataDeNascimento, cpf);
        em.getTransaction().begin();
        clienteDAO.salvar(c);
        em.getTransaction().commit();
        return c;
    }
}
