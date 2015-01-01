package copapc.service.jogo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import copapc.model.campeonato.CampeonatoRepository;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.jogo.Gol;
import copapc.model.jogo.Jogo;

public class JogoService {

  private JogadorRepository jogadorRepository;
  private CampeonatoRepository campeonatoRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(JogoService.class);

  public void marcarGol(Jogador jogador, Jogo jogo) {
    final Gol gol = jogo.adicionarGol(jogador);
    jogador.adicionarGol(gol);
    jogadorRepository.atualizar(jogador);
    LOGGER.info("Gol marcado: {}", gol);
  }

  public void iniciarJogo(Jogo jogo) {
    jogo.iniciar();
    // jogoRepository.atualizar(jogo);
  }

  public void encerrarJogo(Jogo jogo) {
    jogo.encerrar();
    // jogoRepository.atualizar(jogo);
  }

  public void setJogadorRepository(JogadorRepository jogadorRepository) {
    this.jogadorRepository = jogadorRepository;
  }

  // public void setJogoRepository(JogoRepository jogoRepository) {
  // this.jogoRepository = jogoRepository;
  // }

}
