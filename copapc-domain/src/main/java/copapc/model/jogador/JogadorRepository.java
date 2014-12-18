package copapc.model.jogador;

import java.util.Collection;

import copapc.model.time.Time;

public interface JogadorRepository {

  Jogador jogadores();

  Collection<Jogador> jogadoresDoTime(Time time);

}
