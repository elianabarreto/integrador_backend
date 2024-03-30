package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    //Método para crear y guardar en la BD una instancia de paciente
    private Paciente crearYGuardarPaciente(String nombre, String apellido, String dni){

        //Instanciamos un paciente
        Paciente paciente = new Paciente();

        //seteamos los valores al paciente
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setDni(dni);

        //Guardamos el paciente
        pacienteService.agregar(paciente);

        return paciente;
    }

    @Test
    void guardarPacienteTest() {

        //Después de crear y guardar un paciente...
        Paciente paciente = crearYGuardarPaciente("Francisco", "Ruiz", "12332456");

        //Obtenemos su id y lo guardamos en una variable
        Long pacienteId = paciente.getId();

        //Por lo tanto dicha id no debería ser null
        assertNotNull(pacienteId);
    }

    @Test
    void eliminarPacienteTest() {

        //Después de crear y guardar un paciente...
        Paciente paciente = crearYGuardarPaciente("Roberta", "Fels", "112246567");

        //Comprobamos que efectivamente existe
        Long pacienteId = paciente.getId();
        assertNotNull(pacienteId);

        //Para luego eliminarlo
        pacienteService.eliminar(pacienteId);

        //Por lo que, cuando busquemos su id, debería ser null
        assertNull(pacienteService.buscarPorId(pacienteId));
    }

    @Test
    void modificarPacienteTest() {

        //Después de crear y guardar un paciente...
        Paciente paciente = crearYGuardarPaciente("Carla", "Estevez", "44558999");

        //Comprobamos que efectivamente existe
        Long pacienteId = paciente.getId();
        assertNotNull(pacienteId);

        //Modificamos por ejemplo el nombre del paciente
        paciente.setNombre("Felix");
        pacienteService.modificar(paciente);

        //Obtenemos el paciente actualizado
        Optional<Paciente> pacienteModificado = pacienteService.buscarPorId(pacienteId);

        //Verificamos que el paciente exista
        assertTrue(pacienteModificado.isPresent());

        //Verificamos que el nombre se haya actualizado correctamente
        assertEquals("Felix", pacienteModificado.get().getNombre());
    }

    @Test
    void buscarPorIdTest() {

        //Después de crear y guardar un paciente...
        Paciente paciente = crearYGuardarPaciente("Pedrito", "Ramirez", "2398980003");

        //Comprobamos que efectivamente existe
        Long pacienteId = paciente.getId();
        assertNotNull(pacienteId);

        //Verificamos que el paciente no sea null
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(pacienteId);
        assertNotNull(pacienteEncontrado);

        //Verificamos que el paciente exista, y que el nombre recuperado sea el correcto
        assertTrue(pacienteEncontrado.isPresent());
        assertEquals("Pedrito", pacienteEncontrado.get().getNombre());
    }

    @Test
    void listarTodosTest() {

        //Creamos y guardamos algunos pacientes para probar la lista
        crearYGuardarPaciente("Luis", "Lopez", "12668899");
        crearYGuardarPaciente("Maria", "Ramirez", "6566789990");
        crearYGuardarPaciente("Juana", "Fernandez", "1233245899");;

        List<Paciente> pacientes = pacienteService.listarTodos();

        //Comparamos: el numero indicado (3 en este caso) con la cantidad de pacientes
        //que deberíamos tener en nuestra colección
        assertEquals(3, pacientes.size());
    }
}