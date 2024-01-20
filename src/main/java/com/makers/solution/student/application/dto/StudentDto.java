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
    @NotNull
    private Integer edad;
    @NotNull
    private String ciudad;
    @NotNull
    private String zonaHoraria;
}