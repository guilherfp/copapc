package copapc.model.jogo;

import java.io.Serializable;

import copapc.model.jogador.Cartao;
import copapc.model.jogador.Jogador;

public class CartaoDoJogo implements Serializable {
  private static final long serialVersionUID = 1L;

  private Cartao cartao;
  private Jogador jogador;

  CartaoDoJogo() {}

  public CartaoDoJogo(Cartao cartao, Jogador jogador) {
    this.cartao = cartao;
    this.jogador = jogador;
  }

  public Cartao getCartao() {
    return cartao;
  }

  public Jogador getJogador() {
    return jogador;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((cartao == null) ? 0 : cartao.hashCode());
    result = (prime * result) + ((jogador == null) ? 0 : jogador.hashCode());
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
    CartaoDoJogo other = (CartaoDoJogo) obj;
    if (cartao != other.cartao) {
      return false;
    }
    if (jogador == null) {
      if (other.jogador != null) {
        return false;
      }
    } else if (!jogador.equals(other.jogador)) {
      return false;
    }
    return true;
  }

}
