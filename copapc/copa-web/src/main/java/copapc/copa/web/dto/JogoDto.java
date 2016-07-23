package copapc.copa.web.dto;

import copapc.model.jogo.Jogo;

/**
 * @author Guilherme Pacheco
 */
public class JogoDto {

  private int numero;
  private TimeDto mandante;
  private TimeDto visitante;
  private String horario;
  private boolean iniciado;

  JogoDto() {
    super();
  }

  public JogoDto(Jogo jogo) {
    numero = jogo.getNumero();
    iniciado = jogo.isIniciado();
    mandante = new TimeDto(jogo.getMandante());
    visitante = new TimeDto(jogo.getVisitante());
    horario = jogo.getHorario().toString("dd/MM HH:mm");
    ;
  }

  public boolean isIniciado() {
    return iniciado;
  }

  public int getNumero() {
    return numero;
  }

  public TimeDto getMandante() {
    return mandante;
  }

  public TimeDto getVisitante() {
    return visitante;
  }

  public String getHorario() {
    return horario;
  }
}
