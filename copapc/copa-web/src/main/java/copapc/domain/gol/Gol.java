package copapc.domain.gol;

import org.apache.commons.lang3.Validate;

import copapc.domain.jogador.Jogador;
import copapc.domain.jogo.Jogo;
import copapc.domain.time.Time;
import copapc.shared.Entity;

/**
 * @author Guilherme Pacheco
 */
public class Gol extends Entity {

  private int minuto;
  private int numero;
  private Jogador jogador;
  private Time time;
  private Jogo jogo;
  private boolean contra;

  Gol() {
    super();
  }

  private Gol(Jogador jogador, Jogo jogo, boolean contra, int minuto) {
    Validate.notNull(jogo);
    Validate.notNull(jogador);
    this.jogador = jogador;
    this.jogo = jogo;
    time = jogador.getTime();
    numero = jogo.getTotalDeGols() + 1;
    this.contra = contra;
    this.minuto = minuto;
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

  public boolean isContra() {
    return contra;
  }

  public boolean isMandante() {
    return jogo.getMandante().equals(time);
  }

  public boolean isVisitante() {
    return jogo.getVisitante().equals(time);
  }

  public static Gol aFavor(Jogador jogador, Jogo jogo, int minuto) {
    return new Gol(jogador, jogo, false, minuto);
  }

  public static Gol contra(Jogador jogador, Jogo jogo, int minuto) {
    return new Gol(jogador, jogo, true, minuto);
  }

  public boolean isAFavorDe(Time time) {
    return this.time.equals(time) ? !isContra() : isContra();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (jogo == null ? 0 : jogo.hashCode());
    result = prime * result + numero;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
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
    return String.format("Gol = numero: %s, jogador: %s, time: %s, jogo: %s", numero, jogador,
      time, jogo);
  }
}
