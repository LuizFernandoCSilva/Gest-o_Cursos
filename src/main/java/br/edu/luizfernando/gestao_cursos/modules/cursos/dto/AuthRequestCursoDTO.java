package br.edu.luizfernando.gestao_cursos.modules.cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestCursoDTO {

  private String password;
  private String name;
}
