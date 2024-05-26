package br.edu.luizfernando.gestao_cursos.modules.cursos.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.edu.luizfernando.gestao_cursos.modules.cursos.CursosRepository;
import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.AuthRequestCursoDTO;
import br.edu.luizfernando.gestao_cursos.modules.cursos.dto.AuthResponseCursoDTO;

@Service
public class AuthCursoUseCase {

  @Value("${security.token.secret-key}")
  private String secretKey;

  @Autowired
  private CursosRepository cursosRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthResponseCursoDTO execute(AuthRequestCursoDTO authCursoDTO) throws AuthenticationException {
    var curso = this.cursosRepository.findByName(authCursoDTO.getName()).orElseThrow(
        () -> {
          throw new UsernameNotFoundException("Username/password incorrect!");
        });

    var passwordMatches = this.passwordEncoder.matches(authCursoDTO.getPassword(), curso.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException("Username/password incorrect!");
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expiresIn = Instant.now().plus(Duration.ofHours(1));
    var token = JWT.create().withIssuer("javacurso")
        .withExpiresAt(expiresIn)
        .withClaim("roles", Arrays.asList("CURSO"))
        .withSubject(curso.getId().toString())
        .sign(algorithm);

    var authCursoResponse = AuthResponseCursoDTO.builder()
        .acess_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

    return authCursoResponse;
  }
}
