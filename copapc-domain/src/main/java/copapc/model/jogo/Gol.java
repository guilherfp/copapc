package copapc.model.jogo;

import org.apache.commons.lang3.Validate;

import copapc.model.jogador.Jogador;
import copapc.model.time.Time;

public class Gol {

  private int minuto;
  private int numero;
  private Jogador jogador;
  private Time time;
  private Jogo jogo;

  Gol() {}

  public Gol(Jogador jogador, Time time, Jogo jogo) {
    Validate.notNull(jogador);
    Validate.notNull(time);
    Validate.notNull(jogo);
    this.jogador = jogador;
    this.time = time;
    this.jogo = jogo;
    numero = jogo.totalDeGols() + 1;
  }

  public int minuto() {
    return minuto;
  }

  public int numero() {
    return numero;
  }

  public Jogador jogador() {
    return jogador;
  }

  public Time time() {
    return time;
  }

  public Jogo jogo() {
    return jogo;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + numero;
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
    Gol other = (Gol) obj;
    if (numero != other.numero) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("Gol = numero: %s, jogador: %s, time: %s, jogo: %s", numero, jogador, time, jogo);
  }

}
