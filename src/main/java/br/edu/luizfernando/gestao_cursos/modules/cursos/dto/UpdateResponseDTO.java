package br.edu.luizfernando.gestao_cursos.modules.cursos.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResponseDTO {

  private String name;
  private String category;
  private String email;
  private Boolean active;
  private String createdAt; // Alterar para String para suportar a data formatada
  private String updatedAt; // Alterar para String para suportar a data formatada

  public static String formatDateTime(LocalDateTime dateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return dateTime.format(formatter);
  }
}
