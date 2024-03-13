package com.dh.integrador_clinica.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {
    private static final Logger LOGGER = Logger.getLogger(BD.class);

    private static final String SQL_DROP_CREATE_DOMICILIOS =
            "DROP TABLE IF EXISTS DOMICILIOS;" +
            " CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " CALLE VARCHAR(100) NOT NULL," +
            " NUMERO INT NOT NULL," +
            " LOCALIDAD VARCHAR(100) NOT NULL," +
            " PROVINCIA VARCHAR(100) NOT NULL)";

    private static final String SQL_DROP_CREATE_PACIENTES =
            "DROP TABLE IF EXISTS PACIENTES;" +
            " CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100)  NOT NULL," +
            " DNI VARCHAR(100) NOT NULL ," +
            " FECHA_INGRESO DATE NOT NULL," +
            " DOMICILIO_ID INT NOT NULL)";
    private static final String SQL_DROP_CREATE_ODONTOLOGOS =
            "DROP TABLE IF EXISTS ODONTOLOGOS;" +
            " CREATE TABLE ODONTOLOGOS" +
            " (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " MATRICULA BIGINT NOT NULL," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100) NOT NULL)";

    private static final String SQL_INSERT_ODONTOLOGOS =
            "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) " +
            "VALUES ('Eliana', 'Barreto', '567')";

    private static final String SQL_INSERT_PACIENTES =
            "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID) " +
            "VALUES ('Diana', 'Delgado', '54568769','2024-03-12', 1)";
    private static final String SQL_INSERT_DOMICILIOS =
            "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) " +
            "VALUES ('Av. 18 de Julio', 1286, 'Centro','Montevideo')";

    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/db_clinica","sa","");
    }

    public static void crearTablas() {
        Connection connection = null;

        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQL_DROP_CREATE_DOMICILIOS);
            statement.execute(SQL_DROP_CREATE_PACIENTES);
            statement.execute(SQL_DROP_CREATE_ODONTOLOGOS);

            statement.execute(SQL_INSERT_DOMICILIOS);
            statement.execute(SQL_INSERT_PACIENTES);
            statement.execute(SQL_INSERT_ODONTOLOGOS);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}