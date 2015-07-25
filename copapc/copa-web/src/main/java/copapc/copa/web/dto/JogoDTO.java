package copapc.copa.web.dto;

import copapc.model.jogo.Jogo;

/**
 * @author Guilherme Pacheco
 */
public class JogoDTO {

  private int numero;
  private TimeDTO mandante;
  private TimeDTO visitante;
  private String horario;
  private boolean iniciado;

  JogoDTO() {
    super();
  }

  public JogoDTO(Jogo jogo) {
    numero = jogo.getNumero();
    iniciado = jogo.isIniciado();
    mandante = new TimeDTO(jogo.getMandante());
    visitante = new TimeDTO(jogo.getVisitante());
    horario = jogo.getHorario().toString("dd/MM HH:mm");
    ;
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
}
