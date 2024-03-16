package com.dh.integrador_clinica.service;

import com.dh.integrador_clinica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno agregar(Turno turno);
    void modificar (Turno turno);
    void eliminar (Long id);
    Optional buscarPorId (Long id);
    List<Turno> listarTodos();
}
