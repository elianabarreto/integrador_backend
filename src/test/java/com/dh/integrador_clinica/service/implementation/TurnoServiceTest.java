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

    //Método para crear y guardar en la BD una instancia de turno
    private Turno crearYGuardarTurno(String nombreOdont, String apellidoOdont, String matricula,
                                     String nombrePaciente, String apellidoPaciente, String dni,
                                     String fecha){

        //Instanciamos odóntologo y seteamos sus valores
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre(nombreOdont);
        odontologo.setApellido(apellidoOdont);
        odontologo.setMatricula(matricula);

        //Instanciamos paciente y seteamos sus valores
        Paciente paciente = new Paciente();
        paciente.setNombre(nombrePaciente);
        paciente.setApellido(apellidoPaciente);
        paciente.setDni(dni);

        //Instanciamos turno y seteamos sus valores (le asociamos el odontólogo y paciente)
        Turno turno = new Turno();
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        turno.setFecha(LocalDate.parse(fecha));

        //Guardamos las instancias en la BD
        odontologoService.agregar(odontologo);
        pacienteService.agregar(paciente);
        turnoService.agregar(turno);

        return turno;
    }

    @Test
    void guardarTurnoTest() {

        //Odontólogo y paciente guardados y asociados al turno, turno guardado en la BD
        Turno turno = crearYGuardarTurno("Juan", "Dominguez", "4456",
                "Ana", "Garcia", "12345678", "2024-03-24");

        //Obtenemos el id del turno y lo guardamos en una variable
        Long turnoId = turno.getId();

        //Verificamos que el turno se haya guardado correctamente, su id no debería ser null
        assertNotNull(turnoId);
    }

    @Test
    void eliminarTurnoTest() {

        //Creamos y guardamos un turno
        Turno turno = crearYGuardarTurno("Elbio", "Fernandez", "1111",
                "Liz", "Hernandez", "12567844", "2024-02-05");

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

        //Creamos y guardamos un turno
        Turno turno = crearYGuardarTurno("Emilia", "Barras", "4455",
                "Ellen", "Rios", "100110009", "2023-08-27");

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

        //Creamos y guardamos un turno
        Turno turno = crearYGuardarTurno("Elisa", "Sans", "114422",
                "Flores", "Ramirez", "33212456", "2022-05-03");

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

        //Creamos y guardamos algunos turnos para probar la lista
        crearYGuardarTurno("Ester", "Aramandas","1122","Wilson","Casas","44777999","2023-10-03");
        crearYGuardarTurno("Maria", "Robles","3344","Heriberto","Kansas","3340986","2024-01-01");
        crearYGuardarTurno("Ester", "Aramandas","1122","Wilson","Casas","44777999","2023-12-06");

        // Obtenemos la lista de todos los turnos
        List<Turno> turnos = turnoService.listarTodos();

        //Comparamos: el numero indicado (3 en este caso) con la cantidad de turnos
        //que deberíamos tener en nuestra colección
        assertEquals(3, turnos.size());
    }
}