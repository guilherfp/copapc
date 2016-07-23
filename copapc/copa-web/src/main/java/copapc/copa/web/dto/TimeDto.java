package copapc.copa.web.dto;

import copapc.model.time.Time;

/**
 * @author Guilherme Pacheco
 */
public class TimeDto {

  private int numero;
  private String nome;

  TimeDto() {
    super();
  }

  public TimeDto(Time time) {
    numero = time.getNumero();
    nome = time.getNome();
  }

  public int getNumero() {
    return numero;
  }

  public String getNome() {
    return nome;
  }
}
