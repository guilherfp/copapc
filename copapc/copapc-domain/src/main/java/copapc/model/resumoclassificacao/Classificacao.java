package copapc.model.resumoclassificacao;

import java.io.Serializable;
import java.text.NumberFormat;

import copapc.model.time.Time;

/**
 * @author Guilherme Pacheco
 */
public class Classificacao implements Serializable {
  private static final long serialVersionUID = 1L;

  private int posicao = 0;
  private final Time time;
  private final int vitorias;
  private final int empates;
  private final int derrotas;
  private final int golsPros;
  private final int golsContra;
  private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

  static {
    NUMBER_FORMAT.setMaximumFractionDigits(1);
    NUMBER_FORMAT.setMinimumFractionDigits(0);
  }

  public Classificacao(Time time, int vitorias, int empates, int derrotas, int golsPros,
                       int golsContra)
  {
    this.time = time;
    this.vitorias = vitorias;
    this.empates = empates;
    this.derrotas = derrotas;
    this.golsPros = golsPros;
    this.golsContra = golsContra;
  }

  public void setPosicao(int posicao) {
    this.posicao = posicao;
  }

  public int getPosicao() {
    return posicao;
  }

  public Time getTime() {
    return time;
  }

  public String getNomeDoTime() {
    return time.getNome();
  }

  public int getPontos() {
    int pontos = 0;
    pontos += (vitorias * 3);
    pontos += empates;
    return pontos;
  }

  public int getJogos() {
    return vitorias + empates + derrotas;
  }

  public int getVitorias() {
    return vitorias;
  }

  public int getEmpates() {
    return empates;
  }

  public int getDerrotas() {
    return derrotas;
  }

  public int getGolsContra() {
    return golsContra;
  }

  public int getGolsPros() {
    return golsPros;
  }

  public int getSaldoDeGols() {
    return golsPros - golsContra;
  }

  public String getAproveitamento() {
    double aproveitamento = 0;
    if (getJogos() > 0) {
      aproveitamento = ((double) getPontos() / (getJogos() * 3)) * 100;
    }
    return NUMBER_FORMAT.format(aproveitamento) + " %";
  }

  @Override
  public String toString() {
    return String
      .format(
        "Classificacao = posicao: %s, time: %s, vitorias: %s, empates: %s, derrotas: %s, golsPros: %s, golsContra: %s",
        posicao, getNomeDoTime(), vitorias, empates, derrotas, golsPros, golsContra);
  }
}
