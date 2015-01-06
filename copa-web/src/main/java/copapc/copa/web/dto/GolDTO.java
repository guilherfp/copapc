package copapc.copa.web.dto;

public class GolDTO {

  private String emailDoJogador;
  private int numeroDoJogo;
  private boolean contra;

  GolDTO() {}

  public String getEmailDoJogador() {
    return emailDoJogador;
  }

  public void setEmailDoJogador(String emailDoJogador) {
    this.emailDoJogador = emailDoJogador;
  }

  public int getNumeroDoJogo() {
    return numeroDoJogo;
  }

  public void setNumeroDoJogo(int numeroDoJogo) {
    this.numeroDoJogo = numeroDoJogo;
  }

  public boolean isContra() {
    return contra;
  }

}
