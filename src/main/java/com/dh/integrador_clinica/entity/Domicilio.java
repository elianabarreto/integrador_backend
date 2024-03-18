package com.dh.integrador_clinica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "domicilios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String calle;

    private Integer numero;

    private String localidad;

    private String provincia;

    public String toString() {
        return "Domicilio con " + "id: " + id + ", calle: " + calle + ", número: " + numero +
                ", localidad: " + localidad + ", provincia: " + provincia;
    }
}
