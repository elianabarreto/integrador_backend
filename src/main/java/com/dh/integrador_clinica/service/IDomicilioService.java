package com.dh.integrador_clinica.service;

import com.dh.integrador_clinica.entity.Domicilio;

import java.util.List;
import java.util.Optional;

public interface IDomicilioService {
    Domicilio agregar(Domicilio domicilio);
    void modificar (Domicilio domicilio);
    void eliminar (Long id);
    Optional buscarPorId (Long id);
    List<Domicilio> listarTodos();
}
