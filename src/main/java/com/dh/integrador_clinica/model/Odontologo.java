package com.dh.integrador_clinica.model;

public class Odontologo {
    private Integer id;
    private Integer matricula;
    private String nombre;
    private String apellido;

    public Odontologo() {
    }

    public Odontologo(Integer id, Integer matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //TODO borrar constructor (no tiene id)
    public Odontologo(Integer matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String toString() {
        return "Odontolo con " + "id: " + id + ", matrícula: " + matricula + ", nombre: " + nombre +
                ", apellido: " + apellido;
    }
}
