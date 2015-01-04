package copapc.model.jogador;

import java.io.Serializable;

import copapc.model.jogo.Jogo;

public class GolPorJogo implements Serializable {
  private static final long serialVersionUID = 1L;

  private final int quantidade;
  private final Jogo jogo;

  public GolPorJogo(int quantidade, Jogo jogo) {
    this.quantidade = quantidade;
    this.jogo = jogo;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public Jogo getJogo() {
    return jogo;
  }

}
