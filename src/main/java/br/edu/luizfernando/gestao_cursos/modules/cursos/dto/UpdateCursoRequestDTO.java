package br.edu.luizfernando.gestao_cursos.modules.cursos.dto;

import lombok.Data;

@Data
public class UpdateCursoRequestDTO {
  private String name;
  private String category;
}
