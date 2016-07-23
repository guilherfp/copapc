package copapc.service.jogador;

import org.apache.commons.lang3.builder.EqualsBuilder;

import copapc.model.jogador.Jogador;
import copapc.model.jogo.JogoRepository;
import copapc.shared.NumUtils;

/**
 * @author Guilherme Pacheco
 */
public class Artilheiro {

  private int posicao;
  private final Jogador jogador;
  private final int totalDeGols;
  private final double aproveitamento;

  public Artilheiro(Jogador jogador, JogoRepository jogoRepository) {
    this.jogador = jogador;
    totalDeGols = jogador.getTotalDeGols();
    aproveitamento = jogador.getAproveitamento(jogoRepository);
  }

  public int getPosicao() {
    return posicao;
  }

  void setPosicao(int posicao) {
    this.posicao = posicao;
  }

  public Jogador getJogador() {
    return jogador;
  }

  public int getTotalDeGols() {
    return totalDeGols;
  }

  public double getAproveitamento() {
    return aproveitamento;
  }

  public String getGolsPorJogo() {
    return NumUtils.format(aproveitamento);
  }

  public boolean mesmaPosicao(Artilheiro artilheiro) {
    return new EqualsBuilder().append(totalDeGols, artilheiro.totalDeGols).append(aproveitamento,
      artilheiro.aproveitamento).isEquals();
  }

}
