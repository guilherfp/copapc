package copapc.model.jogador;

import java.util.List;

import copapc.model.time.Time;

/**
 * @author Guilherme Pacheco
 */
public interface JogadorRepository {

  List<Jogador> jogadores();

  List<Jogador> jogadoresDoTime(Time time);

  void atualizar(Jogador jogador);

  void salvar(Jogador jogador);

  Jogador comUrl(String url);

  Jogador comEmail(String email);

}
