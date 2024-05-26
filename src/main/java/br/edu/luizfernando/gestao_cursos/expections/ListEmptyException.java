package br.edu.luizfernando.gestao_cursos.expections;

public class ListEmptyException extends RuntimeException {
  public ListEmptyException() {
    super("Lista vazia.");
  }
}
