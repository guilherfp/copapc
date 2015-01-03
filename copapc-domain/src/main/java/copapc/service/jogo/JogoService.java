package copapc.service.jogo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import copapc.model.gol.Gol;
import copapc.model.gol.GolRepository;
import copapc.model.jogador.Jogador;
import copapc.model.jogo.Jogo;

public class JogoService {

  private final GolRepository golRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(JogoService.class);

  public JogoService(GolRepository golRepository) {
    this.golRepository = golRepository;
  }

  public void marcarGol(final Jogador jogador, final Jogo jogo) {
    final Gol gol = new Gol(jogador, jogo);
    jogo.adicionarGol(gol);
    jogador.adicionarGol(gol);
    golRepository.salvar(gol);
    LOGGER.info("Gol marcado: {}", gol);
  }

  public void iniciarJogo(Jogo jogo) {
    jogo.iniciar();
  }

  public void encerrarJogo(Jogo jogo) {
    jogo.encerrar();
  }
}
