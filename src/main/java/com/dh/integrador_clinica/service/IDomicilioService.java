package com.dh.integrador_clinica.service;

import com.dh.integrador_clinica.model.Domicilio;

import java.util.List;

public interface IDomicilioService {
    Domicilio agregar(Domicilio domicilio);
    void modificar (Domicilio domicilio);
    void eliminar (Integer id);
    Domicilio buscarPorId (Integer id);
    List<Domicilio> listarTodos();
}
