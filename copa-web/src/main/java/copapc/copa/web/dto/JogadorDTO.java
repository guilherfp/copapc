package copapc.copa.web.dto;

import java.io.Serializable;

import copapc.model.jogador.Jogador;

/**
 * @author Guilherme Pacheco
 */
public class JogadorDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private String nome;
  private String email;
  private String time;

  JogadorDTO() {
    super();
  }

  public JogadorDTO(Jogador jogador) {
    nome = jogador.getNome();
    email = jogador.getEmail();
    time = jogador.getTime().getNome();
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getTime() {
    return time;
  }

}
