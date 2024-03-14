package com.dh.integrador_clinica.service;

import com.dh.integrador_clinica.model.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente agregar(Paciente paciente);
    void modificar (Paciente paciente);
    void eliminar (Integer id);
    Paciente buscarPorId (Integer id);
    List<Paciente> listarTodos();
}
