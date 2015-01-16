package copapc.copa.web.dto;

import copapc.model.jogador.Cartao;

public class CartaoDTO {

  private String jogador;
  private int jogo;
  private Cartao cartao;
  private int minuto;

  CartaoDTO() {}

  public String getJogador() {
    return jogador;
  }

  public int getJogo() {
    return jogo;
  }

  public Cartao getCartao() {
    return cartao;
  }

  public int getMinuto() {
    return minuto;
  }
}
