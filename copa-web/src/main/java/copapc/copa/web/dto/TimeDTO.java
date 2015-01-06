package copapc.copa.web.dto;

import java.io.Serializable;

import copapc.model.time.Time;

public class TimeDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private int numero;
  private String nome;

  TimeDTO() {}

  public TimeDTO(int numero, String nome) {
    this.numero = numero;
    this.nome = nome;
  }

  public int getNumero() {
    return numero;
  }

  public String getNome() {
    return nome;
  }

  public static TimeDTO fromTime(Time t) {
    return new TimeDTO(t.getNumero(), t.getNome());
  }
}
