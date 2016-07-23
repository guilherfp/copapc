package copapc.service.jogo;

import java.util.ArrayList;
import java.util.List;

import copapc.model.jogo.Jogo;

/**
 * @author Guilherme Pacheco
 */
public class Rodada {

  private int numero;
  private List<Jogo> jogos;

  public Rodada(int numero, List<Jogo> jogos) {
    jogos = new ArrayList<>();
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
