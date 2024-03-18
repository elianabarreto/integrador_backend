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

    //busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    //guarda un paciente
    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.agregar(paciente));
    }

    //Listar todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    //Modifica/actualiza los pacientes
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {
        ResponseEntity<String> response;
        try {
            Optional<Odontologo> odontologoBuscado = pacienteService.buscarPorId(paciente.getId());
            if (odontologoBuscado.isPresent()) {
                pacienteService.modificar(paciente);
                response = ResponseEntity.ok("Se actualizó el paciente con ID " + paciente.getId());
            } else {
                response = ResponseEntity.ok().body("No se puede actualizar el paciente");
            }
        } catch (EmptyResultDataAccessException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontólogo con ID " + paciente.getId() + " no existe");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede actualizar el paciente");
        }
        return response;
    }

    //BORRAR POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Long id) {
        ResponseEntity<String> response;
        try {
            Optional<Odontologo> odontologoBuscado = pacienteService.buscarPorId(id);
            if (odontologoBuscado.isPresent()) {
                pacienteService.eliminar(id);
                response = ResponseEntity.ok("Se borro el paciente con ID " + id);
            } else {
                response = ResponseEntity.ok().body("No se puede borrar el paciente porque no existe");
            }

        } catch (EmptyResultDataAccessException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("El paciente con ID " +  id + " no existe");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede borrar el paciente");
        }
        return response;
    }
}
