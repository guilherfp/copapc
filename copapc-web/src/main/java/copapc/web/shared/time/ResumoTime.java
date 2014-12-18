package copapc.web.shared.time;

import com.google.gwt.i18n.client.NumberFormat;

public class ResumoTime {

  private int posicao;
  private String nomeDoTime;
  private int vitorias;
  private int empates;
  private int derrotas;
  private int golsPros;
  private int golsContra;

  ResumoTime() {}

  public ResumoTime(int posicao, String nome, int vitorias, int empates, int derrotas, int golsPros, int golsContra) {
    this.posicao = posicao;
    nomeDoTime = nome;
    this.vitorias = vitorias;
    this.empates = empates;
    this.derrotas = derrotas;
    this.golsPros = golsPros;
    this.golsContra = golsContra;
  }

  public int getPosicao() {
    return posicao;
  }

  public String getNomeDoTime() {
    return nomeDoTime;
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
    NumberFormat fmt = NumberFormat.getDecimalFormat();
    fmt.overrideFractionDigits(0, 1);
    return fmt.format(aproveitamento) + " %";
  }

}
