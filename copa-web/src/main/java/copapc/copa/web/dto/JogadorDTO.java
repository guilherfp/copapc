package copapc.copa.web.dto;

import java.io.Serializable;

import copapc.model.jogador.Jogador;

public class JogadorDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private String nome;
  private String email;
  private String time;

  JogadorDTO() {}

  public JogadorDTO(String nome, String email, String time) {
    this.nome = nome;
    this.email = email;
    this.time = time;
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

  public static JogadorDTO fromJogador(Jogador j) {
    return new JogadorDTO(j.getNome(), j.getEmail(), j.getTime().getNome());
  }

}
