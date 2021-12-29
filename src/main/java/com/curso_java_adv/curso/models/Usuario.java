package com.curso_java_adv.curso.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "Id")
    private Integer id;

    @Getter @Setter @Column(name = "Nombre")
    private String nombre;

    @Getter @Setter @Column(name = "Apellido")
    private String apellido;

    @Getter @Setter @Column(name = "Telefono")
    private String telefono;

    @Getter @Setter @Column(name = "Edad")
    private Integer edad;

    @Getter @Setter @Column(name = "Email")
    private String email;

    @Getter @Setter @Column(name = "Contraseña")
    private String contraseña;

    public String nombreCompleto() {
        return this.getNombre() + " " + this.getApellido();
    }
}
