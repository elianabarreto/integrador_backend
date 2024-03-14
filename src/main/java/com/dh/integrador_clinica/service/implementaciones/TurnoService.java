package com.dh.integrador_clinica.service.implementaciones;

import com.dh.integrador_clinica.dao.IDAO;
import com.dh.integrador_clinica.model.Turno;
import com.dh.integrador_clinica.service.ITurnoService;

import java.util.List;

public class TurnoService implements ITurnoService {

    private IDAO<Turno> turnoIDAO;

    public TurnoService(IDAO<Turno> turnoIDAO) {
        this.turnoIDAO = new TurnoDAOList();
    }

    public IDAO<Turno> getTurnoIDAO() {
        return turnoIDAO;
    }

    public void setTurnoIDAO(IDAO<Turno> turnoIDAO) {
        this.turnoIDAO = turnoIDAO;
    }

    @Override
    public Turno agregar(Turno turno) {
        return turnoIDAO.agregar(turno);
    }

    @Override
    public void modificar(Turno turno) {
        turnoIDAO.modificar(turno);
    }

    @Override
    public void eliminar(Integer id) {
        turnoIDAO.eliminar(id);
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return turnoIDAO.buscarPorId(id);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoIDAO.listarTodos();
    }
}
