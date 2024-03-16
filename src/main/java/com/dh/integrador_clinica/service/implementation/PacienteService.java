package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Paciente;
import com.dh.integrador_clinica.repository.IPacienteRepository;
import com.dh.integrador_clinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente agregar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void modificar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()) {
            return pacienteOptional;
        } else {
            return null;
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}
