package com.dh.integrador_clinica;

import com.dh.integrador_clinica.dao.BD;
import com.dh.integrador_clinica.model.Odontologo;
import com.dh.integrador_clinica.service.implementacion.OdontologoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegradorClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegradorClinicaApplication.class, args);


		OdontologoService odontologoService = new OdontologoService();
		BD.crearTablas();

		Odontologo odontologo1 = new Odontologo(1234, "Carlitos","Fernandez");
		odontologoService.guardar(odontologo1);

		Odontologo odontologo2 = new Odontologo(9876,"Pablo","Rodriguez");
		odontologoService.guardar(odontologo2);

		Odontologo odontologo3 = new Odontologo(4567,"Alberta","Solis");
		odontologoService.guardar(odontologo3);

		Odontologo odontologo4 = new Odontologo(11111,"Rodri","Hola");
		odontologoService.guardar(odontologo4);

		Odontologo odontologo5 = new Odontologo(0000,"Eli","BBvdsf");
		odontologoService.guardar(odontologo5);

		odontologoService.listarTodos();
	}

}
