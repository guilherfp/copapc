package copapc.copa.web.dto;

import java.time.LocalDateTime;

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
    if (minuto == 0) {
      minuto = LocalDateTime.now().getMinute();
    }
    return minuto;
  }

  public boolean isContra() {
    return contra;
  }

}
