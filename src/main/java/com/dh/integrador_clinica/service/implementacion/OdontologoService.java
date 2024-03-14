package com.dh.integrador_clinica.service.implementacion;

import com.dh.integrador_clinica.dao.IDAO;
import com.dh.integrador_clinica.dao.implementaciones.OdontologoDAOH2;
import com.dh.integrador_clinica.model.Odontologo;
import com.dh.integrador_clinica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDAO<Odontologo> odontologoIDAO;

    public OdontologoService() {
        //Implementación en BD H2
        this.odontologoIDAO = new OdontologoDAOH2();

        //Implementación en memoria
        //this.odontologoIDAO = new OdontologoDAOEnMemoria();
    }

    public IDAO<Odontologo> getOdontologoIDAO() {
        return odontologoIDAO;
    }

    public void setOdontologoIDAO(IDAO<Odontologo> odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        return odontologoIDAO.agregar(odontologo);
    }

    @Override
    public void modificar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoIDAO.listarTodos();
    }




}
