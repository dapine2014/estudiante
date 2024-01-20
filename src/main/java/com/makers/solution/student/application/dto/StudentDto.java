package com.makers.solution.student.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentDto implements Serializable {
    private Long id;

    @NotNull
    private String nombre;

    private String apellido;

    @NotNull
    private Integer edad;

    private String email;
    @NotNull

    private String ciudad;

    @NotNull
    private String zonaHoraria;
}