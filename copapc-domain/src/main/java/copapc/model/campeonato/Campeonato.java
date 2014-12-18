package copapc.model.campeonato;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import copapc.model.jogo.Jogo;
import copapc.model.time.Time;

public class Campeonato {

  private int timesPorGrupo = 5;
  private Map<Integer, Time> grupo1 = new HashMap<>();
  private Map<Integer, Time> grupo2 = new HashMap<>();
  private List<Jogo> jogos = new ArrayList<>();

  private int proximoNumero() {
    int numero = 1;
    numero += grupo1.size();
    numero += grupo2.size();
    return numero;
  }

  public void adicionarTime(Time time) {
    Validate.notNull(time, "Time inválido");
    if (grupo1.size() < timesPorGrupo) {
      grupo1.put(proximoNumero(), time);
    } else {
      grupo2.put(proximoNumero(), time);
    }
  }

  public void iniciarCampeonato() {
    for (int i = 1; i <= timesPorGrupo; i++) {
      jogos.add(new Jogo(grupo1.get(i), grupo2.get(i)));
    }
  }

  public List<Jogo> jogos() {
    return jogos;
  }

  public Collection<Time> timesDoGrupo1() {
    return grupo1.values();
  }

  public Collection<Time> timesDoGrupo2() {
    return grupo2.values();
  }

}
