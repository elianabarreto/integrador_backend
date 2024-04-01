package com.dh.integrador_clinica.controller;

import com.dh.integrador_clinica.entity.Turno;
import com.dh.integrador_clinica.service.IOdontologoService;
import com.dh.integrador_clinica.service.IPacienteService;
import com.dh.integrador_clinica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    @Autowired
    public TurnoController(ITurnoService turnoService, IOdontologoService odontologoService, IPacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    //Busca turno por id
    @GetMapping("/{id}")
    public ResponseEntity<Optional> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    //Guarda un turno
    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.agregar(turno));
    }

    //Lista todos los turnos
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    //Modifica/actualiza un turno
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Turno turno) {
        turnoService.modificar(turno);
        return ResponseEntity.ok("El turno con id " + turno.getId() + " se ha actualizado correctamente");
    }

    //Borra un turno por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.ok("El turno con id " + id + " se ha eliminado correctamente");
    }
}