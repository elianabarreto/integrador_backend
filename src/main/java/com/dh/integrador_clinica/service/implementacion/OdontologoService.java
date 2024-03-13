package com.dh.integrador_clinica.service.implementacion;

import com.dh.integrador_clinica.dao.IDAO;
import com.dh.integrador_clinica.dao.implementaciones.OdontologoDAOH2;
import com.dh.integrador_clinica.model.Odontologo;

import java.util.List;

public class OdontologoService {

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

    public Odontologo guardar(Odontologo odontologo){
        return odontologoIDAO.agregar(odontologo);
    }
    public List<Odontologo> listarTodos(){
        return odontologoIDAO.listarTodos();
    }
}
