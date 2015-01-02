package copapc.model.jogo;

import java.util.List;

import copapc.model.time.Time;

public interface JogoRepository {

  List<Jogo> jogos();

  List<Jogo> jogos(Time time);

}
