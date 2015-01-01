package copapc.service.campeonato;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.Validate;

import copapc.model.campeonato.Campeonato;
import copapc.model.jogo.Jogo;
import copapc.model.time.Time;

public class MataMataService {

  private final int fase;
  private final Campeonato campeonato;
  private Map<Time, Integer> times = new TreeMap<>();
  private List<Jogo> jogos;

  public MataMataService(int fase, Campeonato campeonato) {
    this.fase = fase;
    this.campeonato = campeonato;
  }

  public void adicionarTime(Time time) {
    Validate.notNull(time, "Time inválido");
    Validate.isTrue(times.size() <= fase, "Fase já foi formada");
    times.put(time, times.size() + 1);
    if (times.size() == fase) {
      gerarJogos();
    }
  }

  private List<Jogo> gerarJogos() {
    for (int i = 1; i <= (fase / 2); i++) {
      Jogo jogo = new Jogo(obterColocado(i), obterColocado((fase / 2) - 1), campeonato);
      jogos.add(jogo);
    }
    return jogos;
  }

  public List<Jogo> getJogos() {
    return jogos;
  }

  public Map<Time, Integer> getTimes() {
    return times;
  }

  private Time obterColocado(int colocacao) {
    for (Entry<Time, Integer> classificado : times.entrySet()) {
      if (classificado.getValue() == colocacao) {
        return classificado.getKey();
      }
    }
    throw new RuntimeException("Colocação não existe");
  }

}
