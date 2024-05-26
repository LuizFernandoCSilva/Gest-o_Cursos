package br.edu.luizfernando.gestao_cursos.modules.cursos.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.AuthRequestCursoDTO;
import br.edu.luizfernando.gestao_cursos.modules.cursos.useCases.AuthCursoUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCursoController {

  @Autowired
  private AuthCursoUseCase authCursoUseCase;

  @PostMapping("/curso")
  public ResponseEntity<Object> create(@RequestBody AuthRequestCursoDTO authCursoDTO) {
    try {
      var result = this.authCursoUseCase.execute(authCursoDTO);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      // o e.message pega a exception que foi lançada no useCase
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

  }
}
