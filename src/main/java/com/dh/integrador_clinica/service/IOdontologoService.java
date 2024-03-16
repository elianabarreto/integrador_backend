package com.dh.integrador_clinica.service;

import com.dh.integrador_clinica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo agregar(Odontologo odontologo);
    void modificar (Odontologo odontologo);
    void eliminar (Long id);
    Optional buscarPorId (Long id);
    List<Odontologo> listarTodos();
}
