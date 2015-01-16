package copapc.copa.web.dto;

public class GolDTO {

  private String jogador;
  private int jogo;
  private boolean contra;
  private int minuto;

  GolDTO() {}

  public String getJogador() {
    return jogador;
  }

  public int getJogo() {
    return jogo;
  }

  public int getMinuto() {
    return minuto;
  }

  public boolean isContra() {
    return contra;
  }

}
