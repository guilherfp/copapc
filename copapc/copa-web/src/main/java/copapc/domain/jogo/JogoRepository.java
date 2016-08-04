package copapc.domain.jogo;

import java.util.List;

import copapc.domain.time.Time;

/**
 * @author Guilherme Pacheco
 */
public interface JogoRepository {

  List<Jogo> jogos();

  List<Jogo> jogos(Time time);

  Jogo jogo(int numero);

  void atualizar(Jogo jogo);

  List<Jogo> jogosEmAberto();

  void salvarCartao(CartaoDoJogo cartaoDoJogo);

  List<Jogo> jogosPorFase(int fase);

  List<Jogo> ultimosEncerrados();

}
