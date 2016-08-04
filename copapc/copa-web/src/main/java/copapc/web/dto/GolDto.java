package copapc.web.dto;

/**
 * @author Guilherme Pacheco
 */
public class GolDto {

  private String jogador;
  private int jogo;
  private boolean contra;
  private int minuto;

  GolDto() {
    super();
  }

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

  @Override
  public String toString() {
    return String.format("GolDTO = jogador: %s, jogo: %s, contra: %s, minuto: %s", jogador, jogo,
      contra, minuto);
  }

}
