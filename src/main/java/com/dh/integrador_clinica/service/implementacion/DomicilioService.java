package com.dh.integrador_clinica.service.implementacion;

import com.dh.integrador_clinica.dao.IDAO;
import com.dh.integrador_clinica.dao.implementaciones.OdontologoDAOH2;
import com.dh.integrador_clinica.model.Domicilio;
import com.dh.integrador_clinica.model.Odontologo;
import com.dh.integrador_clinica.service.IDomicilioService;

import java.util.List;

public class DomicilioService implements IDomicilioService {
    private IDAO<Domicilio> domicilioIDAO;

    public DomicilioService() {
        //Implementación en BD H2
        this.domicilioIDAO = new DomicilioDAOH2();
    }

    @Override
    public Domicilio agregar(Domicilio domicilio) {
        return null;
    }

    @Override
    public void modificar(Domicilio domicilio) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Domicilio> listarTodos() {
        return null;
    }
}
