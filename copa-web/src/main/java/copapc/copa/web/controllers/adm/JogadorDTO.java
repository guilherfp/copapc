package copapc.copa.web.controllers.adm;

import java.io.Serializable;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.Posicao;
import copapc.model.time.Time;

public class JogadorDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private String nome;
  private String email;
  private int pontuacao;
  private Posicao posicao;
  private Time time;

  public JogadorDTO() {}

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getPontuacao() {
    return pontuacao;
  }

  public void setPontuacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }

  public Posicao getPosicao() {
    return posicao;
  }

  public void setPosicao(Posicao posicao) {
    this.posicao = posicao;
  }

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public Jogador toJogador() {
    final Jogador jogador = new Jogador(nome, email);
    if (time != null) {
      jogador.setTime(time);
    }
    jogador.setPontuacao(pontuacao);
    jogador.setPosicao(posicao);
    return jogador;
  }

}
