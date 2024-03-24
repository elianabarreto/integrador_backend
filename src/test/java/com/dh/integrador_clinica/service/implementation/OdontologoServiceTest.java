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

    @Test
    void guardarOdontologoTest() {

        //Instanciamos un odóntologo
        Odontologo odontologo = new Odontologo();

        //seteamos los valores al odontólogo
        odontologo.setNombre("Olivia");
        odontologo.setApellido("Ola");
        odontologo.setMatricula("444");

        //Guardamos el odontólogo
        odontologoService.agregar(odontologo);

        //Obtenemos su id y lo guardamos en una variable
        Long odontologoId = odontologo.getId();

        //Por lo tanto dicha id no debería ser null
        assertNotNull(odontologoId);
    }

    @Test
    void eliminarOdontologoTest() {

        //Después de crear y guardar un odontólogo...
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
        odontologo.setMatricula("123");
        odontologoService.agregar(odontologo);

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
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Carlos");
        odontologo.setApellido("Gomez");
        odontologo.setMatricula("789");
        odontologoService.agregar(odontologo);

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
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Pedro");
        odontologo.setApellido("Ramirez");
        odontologo.setMatricula("101");
        odontologoService.agregar(odontologo);

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
        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("Ana");
        odontologo1.setApellido("Lopez");
        odontologo1.setMatricula("111");
        odontologoService.agregar(odontologo1);

        Odontologo odontologo2 = new Odontologo();
        odontologo2.setNombre("Maria");
        odontologo2.setApellido("Garcia");
        odontologo2.setMatricula("222");
        odontologoService.agregar(odontologo2);

        Odontologo odontologo3 = new Odontologo();
        odontologo3.setNombre("Jose");
        odontologo3.setApellido("Martinez");
        odontologo3.setMatricula("333");
        odontologoService.agregar(odontologo3);

        //Comparamos: el numero indicado (3 en este caso) con la cantidad de odontólogos
        //que deberíamos tener en nuestra colección
        List<Odontologo> odontologos = odontologoService.listarTodos();
        assertEquals(3, odontologos.size());
    }
}