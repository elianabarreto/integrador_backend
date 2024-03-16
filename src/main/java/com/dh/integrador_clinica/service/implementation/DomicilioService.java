package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Domicilio;
import com.dh.integrador_clinica.repository.IDomicilioRepository;
import com.dh.integrador_clinica.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IDomicilioService {

    private IDomicilioRepository domicilioRepository;

    @Autowired
    public DomicilioService(IDomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    public Domicilio agregar(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    @Override
    public void modificar(Domicilio domicilio) {
        domicilioRepository.save(domicilio);
    }

    @Override
    public void eliminar(Long id) {
        domicilioRepository.deleteById(id);
    }

    @Override
    public Optional<Domicilio> buscarPorId(Long id) {
        Optional<Domicilio> domicilioOptional = domicilioRepository.findById(id);
        if(domicilioOptional.isPresent()) {
            return domicilioOptional;
        } else {
            return null;
        }
    }

    @Override
    public List<Domicilio> listarTodos() {
        return domicilioRepository.findAll();
    }
}
