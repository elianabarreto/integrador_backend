package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Domicilio;
import com.dh.integrador_clinica.entity.Paciente;
import com.dh.integrador_clinica.exception.BadRequestException;
import com.dh.integrador_clinica.exception.InternalServerErrorException;
import com.dh.integrador_clinica.exception.ResourceNotFoundException;
import com.dh.integrador_clinica.repository.IPacienteRepository;
import com.dh.integrador_clinica.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger logger = Logger.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente agregar(Paciente paciente) {

        String nombre = paciente.getNombre();
        String apellido = paciente.getApellido();
        String dni = paciente.getDni();
        Domicilio domicilio = paciente.getDomicilio();
        String fecha_ingreso = String.valueOf(paciente.getFecha_ingreso());

        //Si no existe alguno de los campos para crear el paciente
        if (nombre == null || apellido == null || dni == null || domicilio == null || fecha_ingreso == null ||
                nombre.equals("") || apellido.equals("") || dni.equals("") || fecha_ingreso.equals("") ||
                nombre.trim().isEmpty() || apellido.trim().isEmpty() || dni.trim().isEmpty() || fecha_ingreso.trim().isEmpty()){

            //Lanzamos una excepción y loggeamos
            logger.error("No se completaron todos los datos requeridos para guardar el paciente");
            throw new BadRequestException("No se completaron todos los datos requeridos para guardar el paciente");

        } else {
            logger.info("Se agregó un paciente correctamente");
            return pacienteRepository.save(paciente);
        }
    }

    @Override
    public void modificar(Paciente paciente) {

        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(paciente.getId());

        //Si existe el paciente, guardar los datos del nuevo
        if (pacienteBuscado.isPresent()) {
            pacienteRepository.save(paciente);
            logger.info("Se modificó el paciente con id " + paciente.getId());

            //Si no existe, loggeamos el error y lanzamos excepción
        } else {
            logger.error("No se puede modificar el paciente con id " + paciente.getId() + ", porque no existe");
            throw new ResourceNotFoundException("No se puede modificar el paciente con id " + paciente.getId() + ", porque no existe");
        }
    }

    @Override
    public void eliminar(Long id) {

        //Buscamos el paciente a eliminar por su id
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);

        //Si está, lo eliminamos
        if (pacienteBuscado.isPresent()) {
            try {
                pacienteRepository.deleteById(id);
                logger.info("Se eliminó el paciente con id " + id);
            } catch (DataAccessException e) {
                throw new InternalServerErrorException("Error interno del servidor al intentar eliminar el paciente", e);
            }
        } else {
            // Si no está, lanzamos una excepción
            logger.error("No se puede eliminar el paciente con id " + id + " porque no existe");
            throw new ResourceNotFoundException("No se puede eliminar el paciente con id " + id+ " porque no existe");
        }
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {

        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        //Si existe el paciente lo retorna
        if(pacienteOptional.isPresent()) {
            return pacienteOptional;
        } else {

            //Si no existe, lanzamos excepción y loggeamos el error
            logger.error("No se encontró el paciente con  id " + id);
            throw new ResourceNotFoundException("No se encontró el paciente con id " + id);
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}
