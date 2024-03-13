package com.dh.integrador_clinica.dao.implementaciones;

import com.dh.integrador_clinica.dao.BD;
import com.dh.integrador_clinica.dao.IDAO;
import com.dh.integrador_clinica.model.Odontologo;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDAO<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);
    @Override
    public Odontologo agregar(Odontologo odontologo) {
        LOGGER.info("Estamos persistiendo un odontologo");

        Connection connection = null;

        try {
            connection = BD.getConnection();

            //insertar los valores
            PreparedStatement psInsert = connection.prepareStatement("INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1,odontologo.getMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());

            psInsert.execute();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return odontologo;
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
        LOGGER.info("Estamos listando odontologos");
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = BD.getConnection();

            //crear una sentencia
            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");

            //3 ejecutar la sentencia
            ResultSet result = psSelect.executeQuery();

            while (result.next()) {

                Integer id = result.getInt("id");
                Integer matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");

                Odontologo odontologo = new Odontologo();

                odontologo.setId(id);
                odontologo.setMatricula(matricula);
                odontologo.setNombre(nombre);
                odontologo.setApellido(apellido);

                odontologos.add(odontologo);
            }
            psSelect.close();

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("Esta es la lista que se está devolviendo " + odontologos);
        return odontologos;
    }
}
