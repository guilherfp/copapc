package copapc.service.jogo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import copapc.model.jogo.Jogo;

/**
 * @author Guilherme Pacheco
 */
public class Rodada implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<Jogo> jogos = new ArrayList<>();
  private int numero;

  public Rodada(int numero, List<Jogo> jogos) {
    this.numero = numero;
    this.jogos = jogos;
  }

  public int getNumero() {
    return numero;
  }

  public void adicionarJogo(Jogo jogo) {
    jogos.add(jogo);
  }

  public List<Jogo> getJogos() {
    return jogos;
  }

}
