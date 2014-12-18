package copapc.model.jogador;

public enum Posicao {

  GOLEIRO("Goleiro"),
  ALA("Ala"),
  FIXO("Fixo"),
  PIVO("Pivo");

  private Posicao(String nome) {
    this.nome = nome;
  }

  private String nome;

  public String nome() {
    return nome;
  }

  @Override
  public String toString() {
    return nome;
  }

}
