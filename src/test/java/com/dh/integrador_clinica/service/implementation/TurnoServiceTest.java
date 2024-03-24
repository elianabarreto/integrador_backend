package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Odontologo;
import com.dh.integrador_clinica.entity.Paciente;
import com.dh.integrador_clinica.entity.Turno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    void guardarTurnoTest() {
        //Creamos un odontólogo y un paciente para asociar al turno
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Dominguez");
        odontologo.setMatricula("4456");

        Paciente paciente = new Paciente();
        paciente.setNombre("Ana");
        paciente.setApellido("Garcia");
        paciente.setDni("12345678");

        //Creamos un turno, que asociamos con el odontólogo y el paciente
        Turno turno = new Turno();
        turno.setFecha(LocalDate.parse("2024-03-24"));
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        //Guardamos odontólogo, paciente y turno en la bd
        odontologoService.agregar(odontologo);
        pacienteService.agregar(paciente);
        turnoService.agregar(turno);

        //Verificamos que el turno se haya guardado correctamente
        assertNotNull(turno.getId());
    }

    @Test
    void eliminarTurnoTest() {

        //Creamos un odontólogo y un paciente para asociar al turno
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Elbio");
        odontologo.setApellido("Fernandez");
        odontologo.setMatricula("1111");

        Paciente paciente = new Paciente();
        paciente.setNombre("Liz");
        paciente.setApellido("Hernandez");
        paciente.setDni("12567844");

        //Creamos un turno, que asociamos con el odontólogo y el paciente
        Turno turno = new Turno();
        turno.setFecha(LocalDate.parse("2024-02-05"));
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        //Guardamos odontólogo, paciente y turno en la bd
        odontologoService.agregar(odontologo);
        pacienteService.agregar(paciente);
        turnoService.agregar(turno);

        //Comprobamos que efectivamente existe
        Long turnoId = turno.getId();
        assertNotNull(turnoId);

        //Para luego eliminarlo
        turnoService.eliminar(turnoId);

        //Por lo que, cuando busquemos su id, debería ser null
        assertNull(turnoService.buscarPorId(turnoId));
    }

    @Test
    void modificarTurnoTest() {

        //Creamos un odontólogo y un paciente para asociar al turno
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Emilia");
        odontologo.setApellido("Barras");
        odontologo.setMatricula("4455");

        Paciente paciente = new Paciente();
        paciente.setNombre("Ellen");
        paciente.setApellido("Rios");
        paciente.setDni("100110009");

        //Creamos un turno, que asociamos con el odontólogo y el paciente
        Turno turno = new Turno();
        turno.setFecha(LocalDate.parse("2023-08-27"));
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        //Guardamos odontólogo, paciente y turno en la bd
        odontologoService.agregar(odontologo);
        pacienteService.agregar(paciente);
        turnoService.agregar(turno);

        //Comprobamos que efectivamente existe
        Long turnoId = turno.getId();
        assertNotNull(turnoId);

        //Modificamos los datos del turno, en este caso fecha
        turno.setFecha(LocalDate.parse("2024-03-25"));
        //Actualizamos el turno
        turnoService.modificar(turno);

        //Obtenemos el turno actualizado
        Optional<Turno> turnoModificado = turnoService.buscarPorId(turnoId);

        //Verificamos que el turno exista
        assertTrue(turnoModificado.isPresent());

        //Verificamos que la fecha se haya actualizado correctamente
        assertEquals(LocalDate.parse("2024-03-25"), turnoModificado.get().getFecha());
    }

    @Test
    void buscarPorIdTest() {

        //Creamos un odontólogo y un paciente para asociar al turno
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Emilia");
        odontologo.setApellido("Barras");
        odontologo.setMatricula("4455");

        Paciente paciente = new Paciente();
        paciente.setNombre("Ellen");
        paciente.setApellido("Rios");
        paciente.setDni("100110009");

        //Creamos un turno, que asociamos con el odontólogo y el paciente
        Turno turno = new Turno();
        turno.setFecha(LocalDate.parse("2023-08-27"));
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        //Guardamos odontólogo, paciente y turno en la bd
        odontologoService.agregar(odontologo);
        pacienteService.agregar(paciente);
        turnoService.agregar(turno);

        //Comprobamos que efectivamente existe
        Long turnoId = turno.getId();
        assertNotNull(turnoId);

        //Verificamos que el turno no sea null
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(turnoId);
        assertNotNull(turnoEncontrado);

        // Verificamos que el turno exista, y que tenga el id correcto
        assertTrue(turnoEncontrado.isPresent());
        assertEquals(turnoId, turnoEncontrado.get().getId());
    }

    @Test
    void listarTodosTest() {

        // Creamos un odontologo y un paciente, luego creamos algunos turnos para probar la lista
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Ester");
        odontologo.setApellido("Aramandas");
        odontologo.setMatricula("1122");

        Paciente paciente = new Paciente();
        paciente.setNombre("Wilson");
        paciente.setApellido("Casas");
        paciente.setDni("4477799922");

        //Creamos varios turnos, que asociamos con el mismo odontólogo y paciente, pero diferentes fechas
        Turno turno1 = new Turno();
        turno1.setFecha(LocalDate.parse("2023-10-03"));
        turno1.setOdontologo(odontologo);
        turno1.setPaciente(paciente);

        Turno turno2 = new Turno();
        turno2.setFecha(LocalDate.parse("2024-02-01"));
        turno2.setOdontologo(odontologo);
        turno2.setPaciente(paciente);

        Turno turno3 = new Turno();
        turno3.setFecha(LocalDate.parse("2024-11-16"));
        turno3.setOdontologo(odontologo);
        turno3.setPaciente(paciente);

        //Guardamos odontólogo, paciente y los turnos en la bd
        odontologoService.agregar(odontologo);
        pacienteService.agregar(paciente);

        turnoService.agregar(turno1);
        turnoService.agregar(turno2);
        turnoService.agregar(turno3);

        // Obtenemos la lista de todos los turnos
        List<Turno> turnos = turnoService.listarTodos();

        //Comparamos: el numero indicado (3 en este caso) con la cantidad de turnos
        //que deberíamos tener en nuestra colección
        assertEquals(3, turnos.size());
    }
}