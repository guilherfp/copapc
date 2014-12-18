package copapc.model.time;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import copapc.model.jogador.Jogador;

public class Time {

  private int numero;
  private String nome;
  private Set<Jogador> jogadores = new HashSet<>();
  private Jogador responsavel;
  private String cor;

  Time() {}

  public Time(int numero, String nome) {
    nome(nome);
    Validate.isTrue(numero > 0, "Número do time inválido");
    this.numero = numero;
  }

  public Time(int numero) {
    this(numero, "Time " + numero);
  }

  public int id() {
    return numero;
  }

  public String nome() {
    return nome;
  }

  public String cor() {
    return cor;
  }

  public void cor(String cor) {
    Validate.notBlank(cor, "Cor inválida");
    this.cor = cor;
  }

  public void nome(String nome) {
    Validate.notBlank(nome, "Nome inválido");
    this.nome = nome;
  }

  public Set<Jogador> jogadores() {
    return Collections.unmodifiableSet(jogadores);
  }

  public void adicionarJogador(Jogador jogador) {
    Validate.notNull(jogador, "Jogador inválido");
    jogadores.add(jogador);
    jogador.time(this);
  }

  public Jogador responsavel() {
    return responsavel;
  }

  public void responsavel(Jogador responsavel) {
    Validate.notNull(responsavel, "Responsável inválido");
    this.responsavel = responsavel;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((nome == null) ? 0 : nome.hashCode());
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
    Time other = (Time) obj;
    if (nome == null) {
      if (other.nome != null) {
        return false;
      }
    } else if (!nome.equals(other.nome)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return nome;
  }

}
