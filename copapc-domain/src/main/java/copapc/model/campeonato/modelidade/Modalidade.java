package copapc.model.campeonato.modelidade;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import copapc.model.campeonato.Campeonato;
import copapc.model.campeonato.Fase;
import copapc.model.jogo.Jogo;
import copapc.model.time.Time;

public abstract class Modalidade {

  private String nome;
  private final Campeonato campeonato;

  Modalidade(String nome, Campeonato campeonato) {
    setNome(nome);
    this.campeonato = campeonato;
  }

  public final String getNome() {
    return nome;
  }

  public final void setNome(String nome) {
    Validate.notBlank(nome, "Nome inválido");
    this.nome = nome;
  }

  public final Campeonato getCampeonato() {
    return campeonato;
  }

  public abstract Map<Jogo, Fase> gerarJogos(Set<Time> times);

}
