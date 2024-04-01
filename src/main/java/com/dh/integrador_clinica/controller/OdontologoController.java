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
       odontologoService.modificar(odontologo);
       return ResponseEntity.ok("El odontologo con id " + odontologo.getId() + " se ha actualizado correctamente");

    }

    //Borra odontólogo por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.ok("El odontologo con id " + id + " se ha eliminado correctamente");
    }
}