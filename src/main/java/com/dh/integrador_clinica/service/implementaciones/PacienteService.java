package com.dh.integrador_clinica.service.implementaciones;

import com.dh.integrador_clinica.dao.IDAO;
import com.dh.integrador_clinica.model.Paciente;
import com.dh.integrador_clinica.service.IPacienteService;

import java.util.List;

public class PacienteService implements IPacienteService {

    private IDAO<Paciente> pacienteIDAO;

    public PacienteService(IDAO<Paciente> pacienteIDAO) {
        this.pacienteIDAO = new PacienteDAOH2();
    }

    public IDAO<Paciente> getPacienteIDAO() {
        return pacienteIDAO;
    }

    public void setPacienteIDAO(IDAO<Paciente> pacienteIDAO) {
        this.pacienteIDAO = pacienteIDAO;
    }

    @Override
    public Paciente agregar(Paciente paciente) {
        return pacienteIDAO.agregar(paciente);
    }

    @Override
    public void modificar(Paciente paciente) {
        pacienteIDAO.modificar(paciente);
    }

    @Override
    public void eliminar(Integer id) {
        pacienteIDAO.eliminar(id);
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return pacienteIDAO.buscarPorId(id);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteIDAO.listarTodos();
    }
}
