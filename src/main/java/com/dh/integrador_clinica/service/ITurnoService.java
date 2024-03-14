package com.dh.integrador_clinica.service;

import com.dh.integrador_clinica.model.Turno;

import java.util.List;

public interface ITurnoService {
    Turno agregar(Turno turno);
    void modificar (Turno turno);
    void eliminar (Integer id);
    Turno buscarPorId (Integer id);
    List<Turno> listarTodos();
}
