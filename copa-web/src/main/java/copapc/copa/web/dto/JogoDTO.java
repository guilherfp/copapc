package copapc.copa.web.dto;

import copapc.model.jogo.Jogo;

public class JogoDTO {

  private int numero;
  private TimeDTO mandante;
  private TimeDTO visitante;

  public JogoDTO(int numero, TimeDTO mandante, TimeDTO visitante) {
    this.numero = numero;
    this.mandante = mandante;
    this.visitante = visitante;
  }

  public int getNumero() {
    return numero;
  }

  public TimeDTO getMandante() {
    return mandante;
  }

  public TimeDTO getVisitante() {
    return visitante;
  }

  public static JogoDTO fromJogo(Jogo j) {
    return new JogoDTO(j.getNumero(), TimeDTO.fromTime(j.getMandante()), TimeDTO.fromTime(j.getVisitante()));
  }

}
