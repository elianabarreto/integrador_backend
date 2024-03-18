package com.dh.integrador_clinica.service.implementation;

import com.dh.integrador_clinica.entity.Odontologo;
import com.dh.integrador_clinica.repository.IOdontologoRepository;
import com.dh.integrador_clinica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public void modificar(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);

        /*if (odontologoRepository.findById(id).isPresent()) {
            odontologoRepository.deleteById(id);
        } VER DE MANEJAR ESTA VALIDACION PARA LOS DEMÁS TAMBIÉN, Y MANIPULAR EN CASO DE QUE NO EXISTA*/
    }

    @Override
    public Optional<Odontologo> buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);

        if(odontologoOptional.isPresent()) {
            return odontologoOptional;
        } else {
            return null;
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }
}
