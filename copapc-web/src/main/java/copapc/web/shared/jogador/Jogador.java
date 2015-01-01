package copapc.web.shared.jogador;

import copapc.web.shared.time.Time;

public class Jogador {

  private String nome;
  private String email;
  private int pontuacao;
  private String posicao;
  private Time time;

  public Jogador(String nome, String email, int pontuacao, String posicao, Time time) {
    this.nome = nome;
    this.email = email;
    this.pontuacao = pontuacao;
    this.posicao = posicao;
    this.time = time;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public int getPontuacao() {
    return pontuacao;
  }

  public String getPosicao() {
    return posicao;
  }

  public Time getTime() {
    return time;
  }

}
