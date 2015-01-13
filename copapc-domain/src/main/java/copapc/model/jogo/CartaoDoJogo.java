package copapc.model.jogo;

import java.io.Serializable;

import copapc.model.jogador.Cartao;
import copapc.model.jogador.Jogador;
import copapc.shared.Entity;

public class CartaoDoJogo extends Entity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Jogo jogo;
  private Cartao cartao;
  private Jogador jogador;

  CartaoDoJogo() {}

  public CartaoDoJogo(Cartao cartao, Jogo jogo, Jogador jogador) {
    this.jogador = jogador;
    this.cartao = cartao;
    this.jogo = jogo;
  }

  public Jogo getJogo() {
    return jogo;
  }

  public Cartao getCartao() {
    return cartao;
  }

  public Jogador getJogador() {
    return jogador;
  }

  public boolean isMandante() {
    return jogo.getMandante().equals(jogador.getTime());
  }

  public boolean isVisitante() {
    return jogo.getVisitante().equals(jogador.getTime());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((cartao == null) ? 0 : cartao.hashCode());
    result = (prime * result) + ((jogador == null) ? 0 : jogador.hashCode());
    result = (prime * result) + ((jogo == null) ? 0 : jogo.hashCode());
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
    if (jogo == null) {
      if (other.jogo != null) {
        return false;
      }
    } else if (!jogo.equals(other.jogo)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("CartaoDoJogo = jogo: %s, cartao: %s, jogador: %s", jogo, cartao, jogador);
  }

}
