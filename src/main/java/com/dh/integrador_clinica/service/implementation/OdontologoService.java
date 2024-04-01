package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Odontologo;
import com.dh.integrador_clinica.exception.BadRequestException;
import com.dh.integrador_clinica.exception.InternalServerErrorException;
import com.dh.integrador_clinica.exception.ResourceNotFoundException;
import com.dh.integrador_clinica.repository.IOdontologoRepository;
import com.dh.integrador_clinica.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {

        String nombre = odontologo.getNombre();
        String apellido = odontologo.getApellido();
        String matricula = odontologo.getMatricula();

        //Si no existe alguno de los campos para crear el odontólogo
        if (nombre == null || apellido == null || matricula == null ||
                nombre.equals("") || apellido.equals("") || matricula.equals("") ||
                nombre.trim().isEmpty() || apellido.trim().isEmpty() || matricula.trim().isEmpty()){

            //Lanzamos una excepción y loggeamos
            logger.error("No se completaron todos los datos requeridos para guardar el odontólogo");
            throw new BadRequestException("No se completaron todos los datos requeridos para guardar el odontólogo");

        } else {
            logger.info("Se agregó un odontólogo correctamente");
            return odontologoRepository.save(odontologo);
        }
    }

    @Override
    public void modificar(Odontologo odontologo) {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(odontologo.getId());

        //Si existe el odontólogo, guardar los datos del nuevo
        if (odontologoBuscado.isPresent()) {
            odontologoRepository.save(odontologo);
            logger.info("Se modificó el odontólogo con id " + odontologo.getId());

            //Si no existe, loggeamos el error y lanzamos excepción
        } else {
            logger.error("No se puede modificar el odontólogo con id " + odontologo.getId() + ", porque no existe");
            throw new ResourceNotFoundException("No se puede modificar el odontólogo con id " + odontologo.getId() + ", porque no existe");
        }
    }

    @Override
    public void eliminar(Long id) {

        //Buscamos el odontologo a eliminar por su id
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(id);

        //Si está, lo eliminamos
        if (odontologoBuscado.isPresent()) {
            try {
                odontologoRepository.deleteById(id);
                logger.info("Se eliminó el odontólogo con id " + id);
            } catch (DataAccessException e) {
                throw new InternalServerErrorException("Error interno del servidor al intentar eliminar el odontólogo", e);
            }
        } else {
            // Si no está, lanzamos una excepción
            logger.error("No se puede eliminar el odontólogo con id " + id + " porque no existe");
            throw new ResourceNotFoundException("No se puede eliminar el odontólogo con id " + id+ " porque no existe");
        }
    }

    @Override
    public Optional<Odontologo> buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);

        //Si existe el odontólogo lo retorna
        if(odontologoOptional.isPresent()) {
            return odontologoOptional;
        } else {

            //Si no existe, lanzamos excepción y loggeamos el error
            logger.error("No se encontró el odontólogo con  id " + id);
            throw new ResourceNotFoundException("No se encontró el odontólogo con  id " + id);
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }
}
