package copapc.web.shared.time;

public class Time {

  private int numero;
  private String nome;
  private String cor;

  Time() {}

  public Time(int numero, String nome, String cor) {
    this.numero = numero;
    this.nome = nome;
    this.cor = cor;
  }

  public int getNumero() {
    return numero;
  }

  public String getNome() {
    return nome;
  }

  public String getCor() {
    return cor;
  }

}
