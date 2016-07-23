package copapc.copa.web.dto;

import copapc.model.jogador.Jogador;

/**
 * @author Guilherme Pacheco
 */
public class JogadorDto {

  private String nome;
  private String email;
  private String time;

  JogadorDto() {
    super();
  }

  public JogadorDto(Jogador jogador) {
    nome = jogador.getNome();
    email = jogador.getEmail();
    time = jogador.getTime().getNome();
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getTime() {
    return time;
  }

}
