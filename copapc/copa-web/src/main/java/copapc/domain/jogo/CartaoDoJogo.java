package copapc.domain.jogo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import copapc.domain.jogador.Jogador;
import copapc.shared.Entity;

/**
 * @author Guilherme Pacheco
 */
public class CartaoDoJogo extends Entity {

  private Jogo jogo;
  private Cartao cartao;
  private Jogador jogador;
  private int minuto;

  CartaoDoJogo() {
    super();
  }

  public CartaoDoJogo(Cartao cartao, Jogo jogo, Jogador jogador, int minuto) {
    this.jogador = jogador;
    this.cartao = cartao;
    this.jogo = jogo;
    this.minuto = minuto;
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

  public int getMinuto() {
    return minuto;
  }

  public boolean isMandante() {
    return jogo.getMandante().equals(jogador.getTime());
  }

  public boolean isVisitante() {
    return jogo.getVisitante().equals(jogador.getTime());
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    return builder.append(cartao).append(jogador).append(jogo).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    CartaoDoJogo other = (CartaoDoJogo) obj;
    EqualsBuilder builder = new EqualsBuilder();
    builder.append(cartao, other.cartao);
    builder.append(jogador, other.jogador);
    builder.append(jogo, other.jogo);
    return builder.isEquals();
  }

  @Override
  public String toString() {
    return String.format("CartaoDoJogo = jogo: %s, cartao: %s, jogador: %s", jogo, cartao, jogador);
  }

}
