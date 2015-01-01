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
    numero = jogo.getTotalDeGols() + 1;
  }

  public int getMinuto() {
    return minuto;
  }

  public int getNumero() {
    return numero;
  }

  public Jogador getJogador() {
    return jogador;
  }

  public Time getTime() {
    return time;
  }

  public Jogo getJogo() {
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
