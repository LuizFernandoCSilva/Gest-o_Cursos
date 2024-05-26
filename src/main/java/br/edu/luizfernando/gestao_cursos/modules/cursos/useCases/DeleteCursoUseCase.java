package br.edu.luizfernando.gestao_cursos.modules.cursos.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.luizfernando.gestao_cursos.expections.IdFoundExcepion;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosRepository;

@Service
public class DeleteCursoUseCase {

  @Autowired
  private CursosRepository cursosRepository;

  public void execute(UUID id) {
      // Verifica se o curso com o ID fornecido existe antes de excluir
      if (this.cursosRepository.existsById(id)) {
        this.cursosRepository.deleteById(id);
      }
      else {
        throw new IdFoundExcepion();
      }
  }
}
