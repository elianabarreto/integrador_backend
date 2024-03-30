package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Odontologo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    //Método para crear y guardar en la BD una instancia de odontólogo
    private Odontologo crearYGuardarOdontologo(String nombre, String apellido, String matricula){

        //Instanciamos un odóntologo
        Odontologo odontologo = new Odontologo();

        //seteamos los valores al odontólogo
        odontologo.setNombre(nombre);
        odontologo.setApellido(apellido);
        odontologo.setMatricula(matricula);

        //Guardamos el odontólogo
        odontologoService.agregar(odontologo);

        return odontologo;
    }

    @Test
    void guardarOdontologoTest() {

        //Después de crear y guardar un odontólogo...
        Odontologo odontologo = crearYGuardarOdontologo("Olivia", "Ola", "444");

        //Obtenemos su id y lo guardamos en una variable
        Long odontologoId = odontologo.getId();

        //Por lo tanto dicha id no debería ser null
        assertNotNull(odontologoId);
    }

    @Test
    void eliminarOdontologoTest() {

        //Después de crear y guardar un odontólogo...
        Odontologo odontologo = crearYGuardarOdontologo("Juan", "Pérez", "1234");

        //Comprobamos que efectivamente existe
        Long odontologoId = odontologo.getId();
        assertNotNull(odontologoId);

        //Para luego eliminarlo
        odontologoService.eliminar(odontologoId);

        //Por lo que, cuando busquemos su id, debería ser null
        assertNull(odontologoService.buscarPorId(odontologoId));
    }

    @Test
    void modificarOdontologoTest() {

        //Después de crear y guardar un odontólogo...
        Odontologo odontologo = crearYGuardarOdontologo("Carlos", "Gomez", "789");

        //Comprobamos que efectivamente existe
        Long odontologoId = odontologo.getId();
        assertNotNull(odontologoId);

        //Modificamos por ejemplo el nombre del odontólogo
        odontologo.setNombre("Roberto");
        odontologoService.modificar(odontologo);

        //Obtenemos el odontólogo actualizado
        Optional<Odontologo> odontologoModificado = odontologoService.buscarPorId(odontologoId);

        //Verificamos que el odontólogo exista
        assertTrue(odontologoModificado.isPresent());

        //Verificamos que el nombre se haya actualizado correctamente
        assertEquals("Roberto", odontologoModificado.get().getNombre());
    }

    @Test
    void buscarPorIdTest() {

        // Después de crear y guardar un odontólogo...
        Odontologo odontologo = crearYGuardarOdontologo("Pedro", "Ramirez", "1010");

        // Comprobamos que efectivamente existe
        Long odontologoId = odontologo.getId();
        assertNotNull(odontologoId);

        // Verificamos que el odontólogo no sea null
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologoId);
        assertNotNull(odontologoEncontrado);

        // Verificamos que el odontólogo exista, y que el nombre recuperado sea el correcto
        assertTrue(odontologoEncontrado.isPresent());
        assertEquals("Pedro", odontologoEncontrado.get().getNombre());
    }

    @Test
    void listarTodosTest() {

        // Creamos y guardamos algunos odontólogos para probar la lista
        crearYGuardarOdontologo("Ana", "Lopez", "111");
        crearYGuardarOdontologo("Maria", "Garcia", "222");
        crearYGuardarOdontologo("Jose", "Martinez", "333");

        //Comparamos: el numero indicado (3 en este caso) con la cantidad de odontólogos
        //que deberíamos tener en nuestra colección
        List<Odontologo> odontologos = odontologoService.listarTodos();
        assertEquals(3, odontologos.size());
    }
}