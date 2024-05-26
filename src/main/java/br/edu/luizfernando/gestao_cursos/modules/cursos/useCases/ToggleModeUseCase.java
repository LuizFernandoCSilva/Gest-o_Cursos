package br.edu.luizfernando.gestao_cursos.modules.cursos.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.luizfernando.gestao_cursos.expections.ListCursoNotFound;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosEntity;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosRepository;

@Service

public class ToggleModeUseCase {

  @Autowired
  private CursosRepository cursosRepository;

  public CursosEntity execute(UUID id) {
    var curso = this.cursosRepository.findById(id).orElseThrow(() -> {
      throw new ListCursoNotFound();
    });

    curso.setActive(!curso.getActive());
    this.cursosRepository.save(curso);
    return curso;
  }

}
