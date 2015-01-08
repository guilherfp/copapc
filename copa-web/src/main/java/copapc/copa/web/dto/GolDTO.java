package copapc.copa.web.dto;

public class GolDTO {

  private String jogador;
  private int jogo;
  private boolean contra;

  GolDTO() {}

  public String getJogador() {
    return jogador;
  }

  public int getJogo() {
    return jogo;
  }

  public boolean isContra() {
    return contra;
  }

}
