package copapc.model.gol;

import org.apache.commons.lang3.Validate;

import copapc.model.jogador.Jogador;
import copapc.model.jogo.Jogo;
import copapc.model.time.Time;
import copapc.shared.Entity;

public class Gol extends Entity {

  private int minuto;
  private int numero;
  private Jogador jogador;
  private Time time;
  private Jogo jogo;

  Gol() {}

  public Gol(final Jogador jogador, final Jogo jogo) {
    Validate.notNull(jogador);
    Validate.notNull(jogo);
    this.jogador = jogador;
    this.jogo = jogo;
    time = jogador.getTime();
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
    result = (prime * result) + ((jogo == null) ? 0 : jogo.hashCode());
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
    if (jogo == null) {
      if (other.jogo != null) {
        return false;
      }
    } else if (!jogo.equals(other.jogo)) {
      return false;
    }
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
