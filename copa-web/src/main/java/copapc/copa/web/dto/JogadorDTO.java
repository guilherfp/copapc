package copapc.copa.web.dto;

import java.io.Serializable;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.Posicao;

public class JogadorDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private String nome;
  private String email;
  private Posicao posicao;

  JogadorDTO() {}

  public JogadorDTO(String nome, String email, Posicao posicao) {
    this.nome = nome;
    this.email = email;
    this.posicao = posicao;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public Posicao getPosicao() {
    return posicao;
  }

  public static JogadorDTO fromJogador(Jogador j) {
    return new JogadorDTO(j.getNome(), j.getEmail(), j.getPosicao());
  }

}
