package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long iD;
    private String nome;
    private String email;
    private LocalDate DataDeNascimento;
    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome, String email, LocalDate dataDeNascimento, String cpf) {
        this.nome = nome;
        this.email = email;
        this.DataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
    }

    public Cliente(Long iD, String nome, String email, LocalDate dataDeNascimento, String cpf) {
        this.iD = iD;
        this.nome = nome;
        this.email = email;
        DataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
    }

    public LocalDate getDataDeNascimento() {
        return DataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        DataDeNascimento = dataDeNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addPedido(Pedido pedido) {
        pedido.setCliente(this);
        pedidos.add(pedido);
    }

    public void removePedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder("Nome = ").append(nome);
        sb.append("\nDataDeNascimento = ").append(fmt.format(DataDeNascimento)).append("\n");
        sb.append("\nEmail = ").append(email);
        sb.append("\nCPF= ").append(cpf);
        for (Pedido x : pedidos) {
            sb.append(x).append("\n");
        }
        return sb.toString();
    }
}
