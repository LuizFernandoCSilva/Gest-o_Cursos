package br.edu.luizfernando.gestao_cursos.expections;

public class ListCursoNotFound extends RuntimeException {
  public ListCursoNotFound() {
    super("Curso não foi encontrado.");
  }
}
