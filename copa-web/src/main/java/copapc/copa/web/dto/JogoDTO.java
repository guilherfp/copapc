package copapc.copa.web.dto;

import copapc.model.jogo.Jogo;

public class JogoDTO {

  private int numero;
  private TimeDTO mandante;
  private TimeDTO visitante;
  private String horario;

  public JogoDTO(int numero, TimeDTO mandante, TimeDTO visitante, String horario) {
    this.numero = numero;
    this.mandante = mandante;
    this.visitante = visitante;
    this.horario = horario;
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

  public String getHorario() {
    return horario;
  }

  public static JogoDTO fromJogo(Jogo j) {
    final String horario = j.getHorario().toString("dd/MM HH:mm");
    return new JogoDTO(j.getNumero(), TimeDTO.fromTime(j.getMandante()), TimeDTO.fromTime(j.getVisitante()), horario);
  }

}
