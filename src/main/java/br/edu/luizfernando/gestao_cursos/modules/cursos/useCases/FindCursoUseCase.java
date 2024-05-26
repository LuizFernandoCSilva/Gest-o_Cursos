package br.edu.luizfernando.gestao_cursos.modules.cursos.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.luizfernando.gestao_cursos.expections.ListCursoNotFound;
import br.edu.luizfernando.gestao_cursos.expections.ListEmptyException;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosEntity;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosRepository;
import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.ListRequestCursoDTO;
import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.ListResponseCursoDTO;

@Service
public class FindCursoUseCase {
  @Autowired
  private CursosRepository cursosRepository;

  public List<ListResponseCursoDTO> findAll() {
    // Buscar todos os cursos
    List<CursosEntity> listCursos = this.cursosRepository.findAll();
    if (listCursos.isEmpty()) {
      throw new ListEmptyException();
    }
    // Mapear cada entidade CursosEntity para ListResponseCursoDTO
    List<ListResponseCursoDTO> listCursosDTO = listCursos.stream()
        .map(curso -> ListResponseCursoDTO.builder()
            .id(curso.getId())
            .name(curso.getName())
            .category(curso.getCategory())
            .email(curso.getEmail())
            .active(curso.getActive())
            .createdAt(ListResponseCursoDTO.formatDateTime(curso.getCreatedAt())) // Formatar a data
            .updatedAt(ListResponseCursoDTO.formatDateTime(curso.getUpdatedAt())) // Formatar a data
            .build())
        .collect(Collectors.toList());

    return listCursosDTO;
  }

  public List<ListResponseCursoDTO> findBy(ListRequestCursoDTO curso) {

    // Buscar todos os cursos
    List<CursosEntity> listCursos = this.cursosRepository
        .findBynameAndCategory(
            curso.getName(), curso.getCategory());

    if (listCursos.isEmpty()) {
      throw new ListCursoNotFound();
    }
    // Mapear cada entidade CursosEntity para ListResponseCursoDTO
    List<ListResponseCursoDTO> listCursosDTO = listCursos.stream()
        .map(c -> ListResponseCursoDTO.builder()
            .id(c.getId())
            .name(c.getName())
            .category(c.getCategory())
            .email(c.getEmail())
            .active(c.getActive())
            .createdAt(ListResponseCursoDTO.formatDateTime(c.getCreatedAt())) // Formatar a data
            .updatedAt(ListResponseCursoDTO.formatDateTime(c.getUpdatedAt())) // Formatar a data
            .build())
        .collect(Collectors.toList());

    return listCursosDTO;

  }
}
