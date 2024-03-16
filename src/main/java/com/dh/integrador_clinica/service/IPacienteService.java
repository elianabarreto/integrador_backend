package com.dh.integrador_clinica.service;

import com.dh.integrador_clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente agregar(Paciente paciente);
    void modificar (Paciente paciente);
    void eliminar (Long id);
    Optional buscarPorId (Long id);
    List<Paciente> listarTodos();
}
