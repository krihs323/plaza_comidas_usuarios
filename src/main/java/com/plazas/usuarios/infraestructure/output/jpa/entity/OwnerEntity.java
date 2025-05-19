package com.plazas.usuarios.infraestructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;
    @Column(name = "apellidos")
    private String lastName;
    @Column(name = "numero_documento")
    private Long numberId;
    @Column(name = "telefono")
    private String phoneNumber;
    @Column(name = "fecha_nacimiento")
    private LocalDate birthDate;
    @Column(name = "correo")
    private String email;
    @Column(name = "clave")
    private String password;

    @Column(name = "tipo_rol")
    private Integer rol;


}
