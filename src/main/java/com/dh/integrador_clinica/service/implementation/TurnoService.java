package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Odontologo;
import com.dh.integrador_clinica.entity.Paciente;
import com.dh.integrador_clinica.entity.Turno;
import com.dh.integrador_clinica.exception.BadRequestException;
import com.dh.integrador_clinica.exception.InternalServerErrorException;
import com.dh.integrador_clinica.exception.ResourceNotFoundException;
import com.dh.integrador_clinica.repository.ITurnoRepository;
import com.dh.integrador_clinica.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger logger = Logger.getLogger(TurnoService.class);

    private ITurnoRepository turnoRepository;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno agregar(Turno turno) {
        Odontologo odontologo = turno.getOdontologo();
        Paciente paciente = turno.getPaciente();
        String fecha = turno.getFecha().toString();

        //Si no existe alguno de los campos para crear el turno
        if (odontologo.getId() == null || paciente.getId() == null || fecha == null ||
                odontologo.getId().equals("") || paciente.getId().equals("") || fecha.equals("")){

            //Lanzamos una excepción y loggeamos
            logger.error("No se completaron todos los datos requeridos para guardar el turno");
            throw new BadRequestException("No se completaron todos los datos requeridos para guardar el turno");

        } else {
            logger.info("Se agregó un turno correctamente");
            return turnoRepository.save(turno);
        }
    }

    @Override
    public void modificar(Turno turno) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(turno.getId());

        //Si existe el turno, guardar los datos del nuevo
        if (turnoBuscado.isPresent()) {
            turnoRepository.save(turno);
            logger.info("Se modificó el turno con id " + turno.getId());

            //Si no existe, loggeamos el error y lanzamos excepción
        } else {
            logger.error("No se puede modificar el turno con id " + turno.getId() + ", porque no existe");
            throw new ResourceNotFoundException("No se puede modificar el turno con id " + turno.getId() + ", porque no existe");
        }
        turnoRepository.save(turno);
    }

    @Override
    public void eliminar(Long id) {

        //Buscamos el turno a eliminar por su id
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);

        //Si está, lo eliminamos
        if (turnoBuscado.isPresent()) {
            try {
                turnoRepository.deleteById(id);
                logger.info("Se eliminó el turno con id " + id);
            } catch (DataAccessException e) {
                throw new InternalServerErrorException("Error interno del servidor al intentar eliminar el turno", e);
            }
        } else {
            // Si no está, lanzamos una excepción
            logger.error("No se puede eliminar el turno con id " + id + " porque no existe");
            throw new ResourceNotFoundException("No se puede eliminar el turno con id " + id+ " porque no existe");
        }
        turnoRepository.deleteById(id);
    }

    @Override
    public Optional<Turno> buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);

        if(turnoOptional.isPresent()) {
            return turnoOptional;
        } else {
            logger.info("No se encontró el turno con  id " + id);
            throw new ResourceNotFoundException("No se encontró el turno con  id " + id);
        }
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }
}
