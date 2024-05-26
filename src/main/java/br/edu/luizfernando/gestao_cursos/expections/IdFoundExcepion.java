package br.edu.luizfernando.gestao_cursos.expections;

public class IdFoundExcepion extends RuntimeException {
  public IdFoundExcepion() {
    super("Curso não encontrado com o id.");
  }
}
