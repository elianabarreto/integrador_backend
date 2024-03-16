package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Turno;
import com.dh.integrador_clinica.repository.ITurnoRepository;
import com.dh.integrador_clinica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno agregar(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public void modificar(Turno turno) {
        turnoRepository.save(turno);
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Optional<Turno> buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isPresent()) {
            return turnoOptional;
        } else {
            return null;
        }
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }
}
