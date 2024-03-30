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
    public ResponseEntity<Optional> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    //Guarda un turno
    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;

//        vamos a chequear que exista el odontologo y la paciente
        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {

            //setear una respuesta en 200 y vamos a hacer que devuelva el turno
            response = ResponseEntity.ok(turnoService.agregar(turno));

        } else {
            //si no existe el odontologo o el paciente
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return response;
    }

    //Lista todos los turnos
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    //Modifica/actualiza un turno
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Turno turno) {
        ResponseEntity<String> response;
        try {
            Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno.getId());
            if (turnoBuscado.isPresent()) {
                turnoService.modificar(turno);
                response = ResponseEntity.ok("Se actualizó el turno con ID " + turno.getId());
            } else {
                response = ResponseEntity.ok().body("No se puede actualizar el turno");
            }
        } catch (EmptyResultDataAccessException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("El turno con ID " + turno.getId() + " no existe");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede actualizar el turno");
        }
        return response;
    }

    //Borra un turno por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Long id) {
        ResponseEntity<String> response;
        try {
            Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
            if (turnoBuscado.isPresent()) {
                turnoService.eliminar(id);
                response = ResponseEntity.ok("Se borro el turno con ID " + id);
            } else {
                response = ResponseEntity.ok().body("No se puede borrar el turno porque no existe");
            }

        } catch (EmptyResultDataAccessException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("El turno con ID " +  id + " no existe");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede borrar el turno");
        }
        return response;
    }
}