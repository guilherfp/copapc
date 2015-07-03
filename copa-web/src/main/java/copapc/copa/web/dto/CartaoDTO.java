package copapc.copa.web.dto;

import copapc.model.jogo.Cartao;

/**
 * @author Guilherme Pacheco
 */
public class CartaoDTO {

  private String jogador;
  private int jogo;
  private Cartao cartao;
  private int minuto;

  CartaoDTO() {
    super();
  }

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

  @Override
  public String toString() {
    return String.format("CartaoDTO = jogador: %s, jogo: %s, cartao: %s, minuto: %s", jogador,
      jogo, cartao, minuto);
  }

}
