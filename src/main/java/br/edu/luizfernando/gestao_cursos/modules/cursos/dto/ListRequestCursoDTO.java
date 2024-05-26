package br.edu.luizfernando.gestao_cursos.modules.cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRequestCursoDTO {
  private String name;
  private String category;
}
