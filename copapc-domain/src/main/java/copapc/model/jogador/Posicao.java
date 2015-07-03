package copapc.model.jogador;

/**
 * @author Guilherme Pacheco
 */
public enum Posicao {

  GOLEIRO("Goleiro"),
  ALA("Ala"),
  FIXO("Fixo"),
  PIVO("Pivô"),
  LINHA("Linha");

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
