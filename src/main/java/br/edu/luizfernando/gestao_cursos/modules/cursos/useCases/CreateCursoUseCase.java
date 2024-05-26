package br.edu.luizfernando.gestao_cursos.modules.cursos.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.luizfernando.gestao_cursos.expections.UserFoundException;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosEntity;
import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosRepository;

@Service
public class CreateCursoUseCase {

  @Autowired
  private CursosRepository cursosRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CursosEntity execute(CursosEntity cursosEntity) {
    this.cursosRepository.findByNameAndEmail(cursosEntity.getName(), cursosEntity.getEmail())
        .ifPresent(c -> {
          throw new UserFoundException();
        });

    var password = this.passwordEncoder.encode(cursosEntity.getPassword());
    cursosEntity.setPassword(password);
    return this.cursosRepository.save(cursosEntity);
  }

}
