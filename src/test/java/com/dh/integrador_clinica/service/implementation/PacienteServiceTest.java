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

    @Test
    void guardarPacienteTest() {

        //Instanciamos un paciente
        Paciente paciente = new Paciente();

        //seteamos los valores al paciente
        paciente.setNombre("Francisco");
        paciente.setApellido("Ruiz");
        paciente.setDni("12332456");

        //Guardamos el paciente
        pacienteService.agregar(paciente);

        //Obtenemos su id y lo guardamos en una variable
        Long pacienteId = paciente.getId();

        //Por lo tanto dicha id no debería ser null
        assertNotNull(pacienteId);
    }

    @Test
    void eliminarPacienteTest() {

        //Después de crear y guardar un paciente...
        Paciente paciente = new Paciente();
        paciente.setNombre("Roberta");
        paciente.setApellido("Fels");
        paciente.setDni("112246567");
        pacienteService.agregar(paciente);

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
        Paciente paciente = new Paciente();
        paciente.setNombre("Carla");
        paciente.setApellido("Estevez");
        paciente.setDni("44558999");
        pacienteService.agregar(paciente);

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
        Paciente paciente = new Paciente();
        paciente.setNombre("Pedrito");
        paciente.setApellido("Ramirez");
        paciente.setDni("2398980003");
        pacienteService.agregar(paciente);

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
        Paciente paciente1 = new Paciente();
        paciente1.setNombre("Luis");
        paciente1.setApellido("Lopez");
        paciente1.setDni("12668899");
        pacienteService.agregar(paciente1);

        Paciente paciente2 = new Paciente();
        paciente2.setNombre("Maria");
        paciente2.setApellido("Ramirez");
        paciente2.setDni("6566789990");
        pacienteService.agregar(paciente2);

        Paciente paciente3 = new Paciente();
        paciente3.setNombre("Juana");
        paciente3.setApellido("Fernandez");
        paciente3.setDni("1233245899");
        pacienteService.agregar(paciente3);

        List<Paciente> pacientes = pacienteService.listarTodos();

        //Comparamos: el numero indicado (3 en este caso) con la cantidad de pacientes
        //que deberíamos tener en nuestra colección
        assertEquals(3, pacientes.size());
    }
}