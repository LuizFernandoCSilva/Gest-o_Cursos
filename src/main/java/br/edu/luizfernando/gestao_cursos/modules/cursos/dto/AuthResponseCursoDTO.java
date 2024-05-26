package br.edu.luizfernando.gestao_cursos.modules.cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseCursoDTO {
  private String acess_token;
  private Long expires_in;
}
