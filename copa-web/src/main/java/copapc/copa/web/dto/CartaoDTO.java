package copapc.copa.web.dto;

import copapc.model.jogador.Cartao;

public class CartaoDTO {

  private String jogador;
  private int jogo;
  private Cartao cartao;

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
}