package com.dh.integrador_clinica.service;

import com.dh.integrador_clinica.model.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo agregar(Odontologo odontologo);
    void modificar (Odontologo odontologo);
    void eliminar (Integer id);
    Odontologo buscarPorId (Integer id);
    List<Odontologo> listarTodos();
}
