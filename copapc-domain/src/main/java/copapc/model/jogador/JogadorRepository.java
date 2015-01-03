package copapc.model.jogador;

import java.util.Comparator;
import java.util.List;

import copapc.model.time.Time;

public interface JogadorRepository {

  List<Jogador> jogadores();

  List<Jogador> jogadoresDoTime(Time time);

  void atualizar(Jogador jogador);

  default List<Jogador> artilharia() {
    final List<Jogador> jogadores = jogadores();
    jogadores.sort(Comparator.comparingInt(Jogador::getTotalDeGols).reversed());
    return jogadores;
  }

}
