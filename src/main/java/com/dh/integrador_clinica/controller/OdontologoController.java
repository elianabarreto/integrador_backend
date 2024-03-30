package com.dh.integrador_clinica.controller;
import com.dh.integrador_clinica.entity.Odontologo;
import com.dh.integrador_clinica.service.IOdontologoService;
import com.dh.integrador_clinica.service.implementation.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.Exception;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController (OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //Busca un odontólogo por id
    @GetMapping("/{id}")
    public ResponseEntity<Optional> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    //Guarda un odontólogo
    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.agregar(odontologo));
    }

    //Lista todos los odontólogos
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    //Modifica/Actualiza los odontólogos
   @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> response;
        try {
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
            if (odontologoBuscado.isPresent()) {
                odontologoService.modificar(odontologo);
                response = ResponseEntity.ok("Se actualizó el odontólogo con ID " + odontologo.getId());
            } else {
                response = ResponseEntity.ok().body("No se puede actualizar el odontólogo");
            }
        } catch (EmptyResultDataAccessException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontólogo con ID " + odontologo.getId() + " no existe");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede actualizar el odontologo");
        }
        return response;
    }

    //Borra odontólogo por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Long id) {
        ResponseEntity<String> response;
        try {
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
            if (odontologoBuscado.isPresent()) {
                odontologoService.eliminar(id);
                response = ResponseEntity.ok("Se borro el odontólogo con ID " + id);
            } else {
                response = ResponseEntity.ok().body("No se puede borrar el odontólogo porque no existe");
            }

    } catch (EmptyResultDataAccessException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontólogo con ID " +  id + " no existe");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede borrar el odontologo");
        }
        return response;
    }
}

