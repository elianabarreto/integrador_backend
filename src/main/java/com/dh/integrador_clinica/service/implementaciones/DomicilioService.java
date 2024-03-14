package com.dh.integrador_clinica.service.implementaciones;

import com.dh.integrador_clinica.dao.IDAO;
import com.dh.integrador_clinica.dao.implementaciones.OdontologoDAOH2;
import com.dh.integrador_clinica.model.Domicilio;
import com.dh.integrador_clinica.model.Odontologo;
import com.dh.integrador_clinica.service.IDomicilioService;

import java.util.List;

public class DomicilioService implements IDomicilioService {

    private IDAO<Domicilio> domicilioIDAO;

    public DomicilioService(IDAO<Domicilio> domicilioIDAO) {
        this.domicilioIDAO = new DomicilioDAOH2();
    }

    public IDAO<Domicilio> getDomicilioIDAO() {
        return domicilioIDAO;
    }

    public void setDomicilioIDAO(IDAO<Domicilio> domicilioIDAO) {
        this.domicilioIDAO = domicilioIDAO;
    }

    @Override
    public Domicilio agregar(Domicilio domicilio) {
        return domicilioIDAO.agregar(domicilio);
    }

    @Override
    public void modificar(Domicilio domicilio) {
        domicilioIDAO.modificar(domicilio);
    }

    @Override
    public void eliminar(Integer id) {
        domicilioIDAO.eliminar(id);
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        return domicilioIDAO.buscarPorId(id);
    }

    @Override
    public List<Domicilio> listarTodos(){
        return domicilioIDAO.listarTodos();
    }
}
