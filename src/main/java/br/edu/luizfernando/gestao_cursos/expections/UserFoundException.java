package br.edu.luizfernando.gestao_cursos.expections;

public class UserFoundException extends RuntimeException{
  public UserFoundException() {
    super("Curso já cadastrado.");
  }
}
