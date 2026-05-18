package dao;
import entities.Pedido;

import java.util.List;

public interface PedidoDAO {
    Pedido buscarPedidoPorId(Long id);
    List<Pedido> buscarPedidosComItens(Long id);
}
