package copapc.model.jogador;

import org.apache.commons.lang3.Validate;

import copapc.model.time.Time;

public class Jogador {

  private int id;
  private String nome;
  private String email;
  private int pontuacao;
  private Posicao posicao;
  private Time time;

  Jogador() {}

  public Jogador(String nome, String email) {
    email(email);
    nome(nome);
  }

  int id() {
    return id;
  }

  public String nome() {
    return nome;
  }

  public void nome(String nome) {
    Validate.notBlank(nome, "Nome inválido");
    this.nome = nome;
  }

  public String email() {
    return email;
  }

  public void email(String email) {
    Validate.notBlank(email, "Nome inválido");
    this.email = email;
  }

  public int pontuacao() {
    return pontuacao;
  }

  public void pontuacao(int pontuacao) {
    Validate.isTrue(pontuacao > 0, "Pontuação inválida");
    this.pontuacao = pontuacao;
  }

  public Posicao posicao() {
    return posicao;
  }

  public void posicao(Posicao posicao) {
    Validate.notNull(posicao, "Posição inválida");
    this.posicao = posicao;
  }

  public Time time() {
    return time;
  }

  public void time(Time time) {
    Validate.notNull(time, "Time inválido");
    this.time = time;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((email == null) ? 0 : email.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Jogador other = (Jogador) obj;
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return nome;
  }

}
