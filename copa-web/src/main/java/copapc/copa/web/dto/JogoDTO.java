package copapc.copa.web.dto;

import copapc.model.jogo.Jogo;

public class JogoDTO {

  private int numero;
  private TimeDTO mandante;
  private TimeDTO visitante;
  private String horario;
  private boolean iniciado;

  public JogoDTO(boolean iniciado, int numero, TimeDTO mandante, TimeDTO visitante, String horario) {
    this.iniciado = iniciado;
    this.numero = numero;
    this.mandante = mandante;
    this.visitante = visitante;
    this.horario = horario;
  }

  public boolean isIniciado() {
    return iniciado;
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
    final TimeDTO mandante = TimeDTO.fromTime(j.getMandante());
    final TimeDTO visitante = TimeDTO.fromTime(j.getVisitante());
    return new JogoDTO(j.isIniciado(), j.getNumero(), mandante, visitante, horario);
  }

}
