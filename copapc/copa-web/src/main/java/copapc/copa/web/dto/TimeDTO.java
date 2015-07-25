package copapc.copa.web.dto;

import java.io.Serializable;

import copapc.model.time.Time;

/**
 * @author Guilherme Pacheco
 */
public class TimeDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private int numero;
  private String nome;

  TimeDTO() {
    super();
  }

  public TimeDTO(Time time) {
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
