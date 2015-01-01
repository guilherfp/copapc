package copapc.web.shared.jogador;

public class Artilheiro implements Comparable<Artilheiro> {

  private Jogador jogador;
  private int gols;

  public Artilheiro(Jogador jogador, int gols) {
    this.jogador = jogador;
    this.gols = gols;
  }

  public Jogador getJogador() {
    return jogador;
  }

  public int getGols() {
    return gols;
  }

  @Override
  public int compareTo(Artilheiro o) {
    return Integer.compare(getGols(), o.getGols());
  }

}
