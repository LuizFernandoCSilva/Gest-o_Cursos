package br.edu.luizfernando.gestao_cursos.modules.cursos.useCases;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.luizfernando.gestao_cursos.expections.IdFoundExcepion;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosEntity;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosRepository;
import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.UpdateCursoRequestDTO;
import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.UpdateResponseDTO;

@Service
public class UpdateCursoUseCase {

  @Autowired
  private CursosRepository cursosRepository;

  public UpdateResponseDTO execute(UUID id, UpdateCursoRequestDTO request) {
    Optional<CursosEntity> curso = this.cursosRepository.findById(id);
    try {
      if (request.getName() != null)
        curso.get().setName(request.getName());
      if (request.getCategory() != null)
        curso.get().setCategory(request.getCategory());

      // Atualiza o updatedAt com o horário atual
      curso.get().setUpdatedAt(LocalDateTime.now());

    } catch (Exception e) {
      throw new IdFoundExcepion();
    }
    cursosRepository.save(curso.get());

    var updateResponse = UpdateResponseDTO.builder()
        .name(curso.get().getName())
        .category(curso.get().getCategory())
        .email(curso.get().getEmail())
        .active(curso.get().getActive())
        .createdAt(UpdateResponseDTO.formatDateTime(curso.get().getCreatedAt())) // Chama o método estático
                                                                                 // formatDateTime
        .updatedAt(UpdateResponseDTO.formatDateTime(curso.get().getUpdatedAt())) // Chama o método estático
        .build();
    return updateResponse;
  }
}
