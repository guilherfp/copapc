package copapc.model.campeonato.modelidade;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.Validate;

import copapc.model.campeonato.Campeonato;
import copapc.model.campeonato.Fase;
import copapc.model.jogo.Jogo;
import copapc.model.time.Time;

public class PorGrupoEMataMata extends Modalidade {

  private int timesPorGrupo = 5;
  private Map<Time, Character> grupos = new TreeMap<>();

  public PorGrupoEMataMata(int timesPorGrupo, Campeonato campeonato) {
    super("Por Grupos e Mata-Mata", campeonato);
    Validate.isTrue(timesPorGrupo >= 1, "Quantidade de times por grupo inválida");
    this.timesPorGrupo = timesPorGrupo;
  }

  public int getTimesPorGrupo() {
    return timesPorGrupo;
  }

  private void validarTimes(Collection<Time> times) {
    Validate.notEmpty(times, "Times inválidos");
    Validate.isTrue((times.size() % timesPorGrupo) == 0, "Quantidade de times deve ser múltiplo de %s", timesPorGrupo);
  }

  @Override
  public Map<Jogo, Fase> gerarJogos(Set<Time> times) {
    validarTimes(times);
    gerarGrupos(times);
    Map<Jogo, Fase> jogos = new HashMap<>();
    for (int fase = 1; fase <= timesPorGrupo; fase++) {
      for (int nrMandante = 1; nrMandante <= timesPorGrupo; nrMandante++) {
        int nrVisitante = timesPorGrupo + nrMandante + (fase - 1);
        if (nrVisitante > grupos.size()) {
          nrVisitante = timesPorGrupo + (nrVisitante - grupos.size());
        }
        final Jogo jogo = new Jogo(getTimePorNumero(nrMandante), getTimePorNumero(nrVisitante), getCampeonato());
        jogos.put(jogo, Fase.CLASSIFICATORIA);
      }
    }
    return jogos;
  }

  public int getQuantidadeDeGrupos() {
    return grupos.size() / timesPorGrupo;
  }

  private Time getTimePorNumero(final int numero) {
    final Optional<Time> find = grupos.keySet().stream().filter(t -> t.numero() == numero).findFirst();
    return find.orElseThrow(() -> new RuntimeException("Jogos não pode ser gerado"));
  }

  private void gerarGrupos(Set<Time> times) {
    times = new TreeSet<>(times);
    for (Time time : times) {
      grupos.put(time, getLetraDoGrupo());
    }
  }

  public Map<Time, Character> getGrupos() {
    return Collections.unmodifiableMap(grupos);
  }

  private char getLetraDoGrupo() {
    return getLetraDoGrupo((char) 65);
  }

  private char getLetraDoGrupo(char letra) {
    if (quantidadeAtingida(letra)) {
      return getLetraDoGrupo(++letra);
    }
    return letra;
  }

  private boolean quantidadeAtingida(char letra) {
    return grupos.values().stream().filter(l -> l.equals(letra)).count() >= timesPorGrupo;
  }
}
