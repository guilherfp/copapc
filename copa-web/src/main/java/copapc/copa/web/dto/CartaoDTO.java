package copapc.copa.web.dto;

import java.time.LocalDateTime;

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
    if (minuto == 0) {
      minuto = LocalDateTime.now().getMinute();
    }
    return minuto;
  }
}
