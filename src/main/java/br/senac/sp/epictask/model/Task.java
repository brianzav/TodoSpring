package br.senac.sp.epictask.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String title;

    // not null
    //         not empty

    @Size(min = 10, message = "digite uma descrição com pelo menos 10 caracteres")
    String description;

    @Min(value = 1, message = "não pode ser negativo")
    Integer score;

}
