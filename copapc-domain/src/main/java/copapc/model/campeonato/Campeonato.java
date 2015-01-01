package copapc.model.campeonato;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import copapc.model.campeonato.modelidade.Modalidade;
import copapc.model.jogo.Jogo;
import copapc.model.time.Time;

public class Campeonato {

  private String nome;
  private Set<Time> times = new HashSet<>();
  private Map<Jogo, Fase> jogos = new HashMap<>();
  private Modalidade modalidade;

  Campeonato() {}

  public Campeonato(String nome) {
    Validate.notBlank(nome, "Nome do campeonato inválido");
    this.nome = nome;
  }

  public String nome() {
    return nome;
  }

  public Modalidade modalidade() {
    return modalidade;
  }

  private int proximoNumero() {
    return times.size() + 1;
  }

  public Time adicionarTime() {
    final Time time = new Time(proximoNumero());
    adicionarTime(time);
    return time;
  }

  public void adicionarTime(Time time) {
    Validate.notNull(time, "Time inválido");
    times.add(time);
  }

  public void iniciarCampeonato(Modalidade modalidade) {
    Validate.notNull(modalidade, "Modalidade inválida");
    this.modalidade = modalidade;
    jogos = modalidade.gerarJogos(times);
  }

  public Map<Jogo, Fase> jogos() {
    return Collections.unmodifiableMap(jogos);
  }

  public Set<Time> times() {
    return Collections.unmodifiableSet(times);
  }

}
