package br.edu.luizfernando.gestao_cursos.modules.cursos.controlers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosEntity;
import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.ListRequestCursoDTO;
import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.UpdateCursoRequestDTO;
import br.edu.luizfernando.gestao_cursos.modules.cursos.useCases.CreateCursoUseCase;
import br.edu.luizfernando.gestao_cursos.modules.cursos.useCases.DeleteCursoUseCase;
import br.edu.luizfernando.gestao_cursos.modules.cursos.useCases.FindCursoUseCase;
import br.edu.luizfernando.gestao_cursos.modules.cursos.useCases.ToggleModeUseCase;
import br.edu.luizfernando.gestao_cursos.modules.cursos.useCases.UpdateCursoUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

  @Autowired
  private CreateCursoUseCase createCursoUseCase;

  @Autowired
  private FindCursoUseCase findCursoUseCase;

  @Autowired
  private UpdateCursoUseCase UpdateCursoUseCase;

  @Autowired
  private DeleteCursoUseCase deleteCursoUseCase;

  @Autowired
  private ToggleModeUseCase toggleModeCursoUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CursosEntity curso) {

    try {
      var result = this.createCursoUseCase.execute(curso);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CURSO')")
  public ResponseEntity<Object> list() {
    try {
      var result = this.findCursoUseCase.findAll();
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/list")
  @PreAuthorize("hasRole('CURSO')")
  public ResponseEntity<Object> findBy(@RequestBody ListRequestCursoDTO curso) {
    try {
      var result = findCursoUseCase.findBy(curso);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/att/{id}")
  @PreAuthorize("hasRole('CURSO')")
  public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody UpdateCursoRequestDTO curso) {
    if (curso.getName() == null || curso.getName().isEmpty() || curso.getCategory() == null
        || curso.getCategory().isEmpty()) {
      return ResponseEntity.badRequest().body("As propriedades 'name' e 'category' são obrigatórias.");
    }
    try {
      var result = this.UpdateCursoUseCase.execute(id, curso);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasRole('CURSO')")
  public ResponseEntity<Object> delete(@PathVariable UUID id) {
    try {
      this.deleteCursoUseCase.execute(id);
      return ResponseEntity.ok().body("Curso deletado com sucesso");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/patch/{id}/active")
  @PreAuthorize("hasRole('CURSO')")
  public ResponseEntity<Object> toggleMode(@PathVariable UUID id) {
    try {
      var result = this.toggleModeCursoUseCase.execute(id);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
