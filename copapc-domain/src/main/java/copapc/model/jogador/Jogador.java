package copapc.model.jogador;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import copapc.model.gol.Gol;
import copapc.model.time.Time;
import copapc.shared.Entity;

public class Jogador extends Entity implements Comparable<Jogador> {

  private String nome;
  private String email;
  private int pontuacao;
  private Posicao posicao;
  private Time time;
  private List<Gol> gols = new ArrayList<>();

  Jogador() {}

  public Jogador(String nome, String email) {
    setEmail(email);
    setNome(nome);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    Validate.notBlank(nome, "Nome inválido");
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    Validate.notBlank(email, "Nome inválido");
    this.email = email;
  }

  public int getPontuacao() {
    return pontuacao;
  }

  public void setPontuacao(int pontuacao) {
    Validate.isTrue(pontuacao > 0, "Pontuação inválida");
    this.pontuacao = pontuacao;
  }

  public Posicao getPosicao() {
    return posicao;
  }

  public void setPosicao(Posicao posicao) {
    Validate.notNull(posicao, "Posição inválida");
    this.posicao = posicao;
  }

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    Validate.notNull(time, "Time inválido");
    this.time = time;
  }

  public void adicionarGol(Gol gol) {
    Validate.notNull(gol);
    gols.add(gol);
  }

  public int getTotalDeGols() {
    return gols.size();
  }

  public List<Gol> getGols() {
    return gols;
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

  @Override
  public int compareTo(Jogador o) {
    return nome.compareTo(o.nome);
  }

}
