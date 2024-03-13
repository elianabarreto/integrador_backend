package com.dh.integrador_clinica.dao;

import java.util.List;

public interface IDAO<T> {
    T agregar(T t);
    void modificar (T t);
    void eliminar (Integer id);
    T buscarPorId (Integer id);
    List<T> listarTodos();
}
