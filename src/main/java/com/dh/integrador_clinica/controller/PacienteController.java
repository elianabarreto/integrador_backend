package com.dh.integrador_clinica.controller;

import com.dh.integrador_clinica.entity.Odontologo;
import com.dh.integrador_clinica.entity.Paciente;
import com.dh.integrador_clinica.service.IPacienteService;
import com.dh.integrador_clinica.service.implementation.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    @Autowired
    public PacienteController (PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //Busca paciente por id
    @GetMapping("/{id}")
    public ResponseEntity<Optional> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    //Guarda un paciente
    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.agregar(paciente));
    }

    //Lista todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    //Modifica/actualiza un paciente
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {
        pacienteService.modificar(paciente);
        return ResponseEntity.ok("El paciente con id " + paciente.getId() + " se ha actualizado correctamente");
    }

    //Borra un paciente por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.ok("El paciente con id " + id + " se ha eliminado correctamente");
    }
}