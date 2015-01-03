package copapc.model.jogador;

public enum Posicao {

  GOLEIRO("Goleiro"),
  ALA("Ala"),
  FIXO("Fixo"),
  PIVO("Pivô");

  private Posicao(String nome) {
    this.nome = nome;
  }

  private String nome;

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    return nome;
  }

}
