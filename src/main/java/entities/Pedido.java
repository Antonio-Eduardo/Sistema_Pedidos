package entities;

import enums.StatusPedido;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private Timestamp dataHora;
    private double precoPedido;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensPedido> itensPedidos = new ArrayList<>();

    public Pedido(){
        this.status = StatusPedido.ABERTO;
        this.dataHora = new Timestamp(System.currentTimeMillis());
    }

    public Pedido(Long idPedido,double precoPedido, Timestamp dataHora, StatusPedido status) {
        this.idPedido = idPedido;

        this.precoPedido = precoPedido;
        this.dataHora = new Timestamp(System.currentTimeMillis());
        this.status = status;
    }
    public Timestamp getDataHora() {
        return dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public void addItem(ItensPedido item){
        item.setPedido(this);
        itensPedidos.add(item);
    }
    public void removeItem(ItensPedido item){
        itensPedidos.remove(item);
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public double getPrecoPedido() {
        return precoPedido;
    }

    public void setPrecoPedido(double precoPedido) {
        this.precoPedido = precoPedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();

        sb.append("\n--- RESUMO DO PEDIDO ---\n");
        if (dataHora != null) {
            sb.append("Instante do Pedido: ").append(dataHora).append("\n");
        } else {
            sb.append("SEM DATA");
        }
        sb.append("Status: ").append(status).append("\n");
        sb.append("\nItens do Pedido:\n");

        for (ItensPedido x : itensPedidos){
            sb.append(x.toString());
        }

        sb.append("\nTotal do Pedido: R$ ").append(String.format("%.2f", precoPedido)).append("\n");
        sb.append("========================\n");

        return sb.toString();
    }
}
