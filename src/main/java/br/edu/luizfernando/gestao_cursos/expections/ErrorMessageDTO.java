package br.edu.luizfernando.gestao_cursos.expections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
  
  private String message;
  private String field;
}
