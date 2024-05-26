package br.edu.luizfernando.gestao_cursos.modules.cursos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<CursosEntity, UUID> {
  Optional<CursosEntity> findByNameAndEmail(String name, String email);

  Optional<CursosEntity> findByName(String name);

  List<CursosEntity> findBynameAndCategory(String name, String category);

  Optional<CursosEntity> findById(UUID id);

  Optional<CursosEntity> findByNameOrCategory(String name, String category);
}
