package br.edu.luizfernando.gestao_cursos.modules.cursos;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "cursos")
public class CursosEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank
  private String name;

  @NotBlank
  private String category;

  @NotBlank
  @Email(message = "O campo(email) deve conter um email válido.")
  private String email;

  @NotBlank
  @Length(min = 6, message = "O campo(password) deve conter no mínimo 6 caracteres e não ser nulo.")
  private String password;
  private Boolean active;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @CreationTimestamp
  private LocalDateTime updatedAt;
}
