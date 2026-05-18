package services;

import java.util.List;

public interface PersistenciaDados<T> {
    void salvar(T obj);

    List<T> listar();
}
