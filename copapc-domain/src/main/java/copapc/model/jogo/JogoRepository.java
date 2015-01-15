package copapc.model.jogo;

import java.util.List;

import copapc.model.time.Time;

public interface JogoRepository {

  List<Jogo> jogos();

  List<Jogo> jogos(Time time);

  void atualizar(Jogo jogo);

  List<Jogo> jogosEmAberto();

  Jogo jogoComNumero(int numero);

  void salvarCartao(CartaoDoJogo cartaoDoJogo);

  List<Jogo> jogosPorFase(int fase);

  List<Jogo> ultimosEncerrados();

}
